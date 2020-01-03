package fr.bnp.homeloancalculator.domain.calculator;

import fr.bnp.homeloancalculator.domain.math.EIRCalculator;
import fr.bnp.homeloancalculator.domain.mortgage.Calculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculatorImpl implements Calculator {

    EIRCalculator eirCalculator = new EIRCalculator();

    private double loanInterestRate;
    private double loanInsuranceRate;
    private double loanGuarantyRate;
    private int loanDuration;
    private int periodDurationInMonths;
    private int durationInPeriods;     // length of the term in periods
    private double loanAmount;
    private double loanPayment;
    private double globalLoanPayment;  // Loan payment including insurance
    private double loanCost;
    private double interestCost;
    private double insuranceCost;
    private double loanGuaranty;
    private double applicationFee;
    private double globalEffectiveInterestRate;
    private double insuranceImpactOnInterestRate;
    private double feesImpactOnInterestRate;

    Logger logger = LoggerFactory.getLogger(CalculatorImpl.class);

    public CalculatorImpl(double loanInterestRate,
                          double loanInsuranceRate,
                          double loanGuarantyRate,
                          double applicationFee,
                          int loanDuration,
                          int periodDurationInMonths,
                          double loanAmount,
                          double loanPayment) {
        this.loanInterestRate = loanInterestRate / 100.0;   //Convert interest rate into a decimal; eg. 0,85% = 0.0085
        this.loanInsuranceRate = loanInsuranceRate / 100.0; // Convert insurance rate into a decimal; eg. 0,85% = 0.0085
        this.loanGuarantyRate = loanGuarantyRate / 100.0;   // Convert guaranty rate into a decimal; eg. 0,85% = 0.0085
        this.applicationFee = applicationFee;
        this.loanDuration = loanDuration;
        this.periodDurationInMonths = periodDurationInMonths;
        this.loanAmount = loanAmount;
        this.loanPayment = loanPayment;
    }

    public void calculateCost() {
        logger.info("Démarrage du calcul du coût du crédit");

        // Length of the term in periods
        durationInPeriods = getNumberOfPeriods(loanDuration, periodDurationInMonths);

        // Calculate loan amount or periodic payment depending on the input parameters filled by the user
        loanAmount = loanAmount != 0 ?
                loanAmount : calculateLoanAmount(loanInterestRate, loanPayment, loanDuration, periodDurationInMonths);
        logger.info("Montant crédit = {}", loanAmount);

        loanPayment = loanPayment != 0 ?
                loanPayment : calculateLoanPayment(loanInterestRate, loanAmount, loanDuration, periodDurationInMonths);
        logger.info("Echéance = {}", loanPayment);

        // Calcul of the cost credit
        logger.info("Frais de dossier = {}", applicationFee);

        // Total insurance periodic fees = % of loan amount
        insuranceCost = loanAmount * loanInsuranceRate;
        logger.info("Coût assurance = {}", insuranceCost);

        // Initial guarantee fee = % of loan amount
        loanGuaranty = loanAmount * loanGuarantyRate * periodDurationInMonths;
        logger.info("Coût garantie = {}", loanGuaranty);

        // Global payment (periodic payment including insurance)
        double periodicInsuranceFee = insuranceCost / durationInPeriods;
        globalLoanPayment = loanPayment + periodicInsuranceFee;
        logger.info("Echéance globale = {}", globalLoanPayment);

        // Interest cost
        interestCost = (loanPayment * durationInPeriods) - loanAmount;
        logger.info("Coût intérêts = {}", interestCost);

        // Loan cost
        loanCost = interestCost + insuranceCost + applicationFee + loanGuaranty;
        logger.info("Coût du crédit = {}", loanCost);

        calculateEffectiveInterestRates();

    }

    private void calculateEffectiveInterestRates() {
        double periodicInsuranceFee = insuranceCost / durationInPeriods;

        // --> Calculation of the nominal effective interest rates (EIR)
        double presentValue = loanAmount;
        double[] cashFlow = new double[durationInPeriods];
        for (int i = 0; i < cashFlow.length; i++) {
            cashFlow[i] = loanPayment;
        }
        double nominalInterestRate =
                eirCalculator.calculateEffectiveInterestRate(presentValue, cashFlow)
                        * 1200 / periodDurationInMonths;
        logger.info("Nominal EIR = {}", nominalInterestRate);

        // EIR including insurance fees
        for (int i = 0; i < cashFlow.length; i++) {
            cashFlow[i] += periodicInsuranceFee;
        }
        double eirWithInsurance =
                eirCalculator.calculateEffectiveInterestRate(presentValue, cashFlow)
                        * 1200 / periodDurationInMonths;
        logger.info("EIR with insurance = {}", eirWithInsurance);

        // Insurance impact on interest rate
        insuranceImpactOnInterestRate = eirWithInsurance - nominalInterestRate;
        logger.info("TAEA = {}", insuranceImpactOnInterestRate);

        // EIR including insurance, guarantee & application fees
        presentValue -= loanGuaranty;
        cashFlow[0] += applicationFee;
        globalEffectiveInterestRate =
                eirCalculator.calculateEffectiveInterestRate(presentValue, cashFlow)
                        * 1200 / periodDurationInMonths;
        logger.info("TAEG = {}", globalEffectiveInterestRate);

        // Fee impact on interest rate
        feesImpactOnInterestRate = globalEffectiveInterestRate - eirWithInsurance;
        logger.info("TAEF = {}", feesImpactOnInterestRate);
    }

    private double calculateLoanAmount(double annualInterestRate, double loanPayment, int loanDuration, int periodDurationInMonths) {

        // Periodic interest rate
        double periodicInterestRate = getPeriodicRate(annualInterestRate, periodDurationInMonths);

        // length of the term in periods
        int numberOfPeriods = getNumberOfPeriods(loanDuration, periodDurationInMonths);

        // Calculate the loan amount
        double loanAmount;
        loanAmount = (loanPayment / periodicInterestRate)
                * ((1 - Math.pow(1 + periodicInterestRate, -numberOfPeriods)));

        return loanAmount;
    }

    private double calculateLoanPayment(double annualInterestRate, double loanAmount, int loanDuration, int periodDurationInMonths) {

        // Periodic interest rate
        double periodicRate = getPeriodicRate(annualInterestRate, periodDurationInMonths);

        // length of the term in periods
        int durationInPeriods = getNumberOfPeriods(loanDuration, periodDurationInMonths);

        // Calculate the periodic payment
        return loanAmount * periodicRate
                / (1 - Math.pow(1 + periodicRate, -durationInPeriods));
    }

    private double getPeriodicRate(double annualInterestRate, int numberOfMonths) {
        return (annualInterestRate * numberOfMonths / 12.0);
    }

    private int getNumberOfPeriods(int loanDuration, int numberOfMonths) {
        return (loanDuration * 12) / numberOfMonths;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public double getLoanPayment() {
        return loanPayment;
    }

    public double getGlobalLoanPayment() {
        return globalLoanPayment;
    }

    public double getInsuranceCost() {
        return insuranceCost;
    }

    public double getLoanGuaranty() {
        return loanGuaranty;
    }

    public double getApplicationFee() {
        return applicationFee;
    }

    public double getInterestCost() {
        return interestCost;
    }

    public double getLoanCost() {
        return loanCost;
    }

    public double getInsuranceImpactOnInterestRate() {
        return insuranceImpactOnInterestRate;
    }

    public double getGlobalEffectiveInterestRate() {
        return globalEffectiveInterestRate;
    }

    public double getFeesImpactOnInterestRate() {
        return feesImpactOnInterestRate;
    }
}

