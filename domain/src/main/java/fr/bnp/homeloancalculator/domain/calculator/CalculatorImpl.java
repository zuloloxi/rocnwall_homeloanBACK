package fr.bnp.homeloancalculator.domain.calculator;

import fr.bnp.homeloancalculator.domain.math.EIRCalculator;
import fr.bnp.homeloancalculator.domain.mortgage.CalculationMode;
import fr.bnp.homeloancalculator.domain.mortgage.Calculator;

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
        // Length of the term in periods
        durationInPeriods = getNumberOfPeriods(loanDuration, periodDurationInMonths);

        // Calculate loan amount or periodic payment depending on the input parameters filled by the user
        loanAmount = loanAmount != 0 ?
                loanAmount : calculateLoanAmount(loanInterestRate, loanPayment, loanDuration, periodDurationInMonths);
        System.out.printf("Montant crédit = %s\n", loanAmount);

        loanPayment = loanPayment != 0 ?
                loanPayment : calculateLoanPayment(loanInterestRate, loanAmount, loanDuration, periodDurationInMonths);
        System.out.printf("Echéance = %s\n", loanPayment);

        // Calcul of the cost credit
        System.out.printf("Frais de dossier = %s\n", applicationFee);

        // Total insurance periodic fees = % of loan amount
        insuranceCost = loanAmount * loanInsuranceRate;
        System.out.printf("Coût assurance = %s\n", insuranceCost);

        // Initial guarantee fee = % of loan amount
        loanGuaranty = loanAmount * loanGuarantyRate * periodDurationInMonths;
        System.out.printf("Coût garantie = %s\n", loanGuaranty);

        // Global payment (periodic payment including insurance)
        double periodicInsuranceFee = insuranceCost / durationInPeriods;
        globalLoanPayment = loanPayment + periodicInsuranceFee;
        System.out.printf("Echéance globale = %s\n", globalLoanPayment);

        // Interest cost
        interestCost = (loanPayment * durationInPeriods) - loanAmount;
        System.out.printf("Coût intérêts = %s\n", interestCost);

        // Loan cost
        loanCost = interestCost + insuranceCost + applicationFee + loanGuaranty;
        System.out.printf("Coût du crédit = %s\n", loanCost);

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
        System.out.printf("Nominal EIR = %s\n", nominalInterestRate);

        // EIR including insurance fees
        for (int i = 0; i < cashFlow.length; i++) {
            cashFlow[i] += periodicInsuranceFee;
        }
        double eirWithInsurance =
                eirCalculator.calculateEffectiveInterestRate(presentValue, cashFlow)
                        * 1200 / periodDurationInMonths;
        System.out.printf("EIR with insurance = %s\n", eirWithInsurance);

        // Insurance impact on interest rate
        insuranceImpactOnInterestRate = eirWithInsurance - nominalInterestRate;
        System.out.printf("TAEA = %s\n", insuranceImpactOnInterestRate);

        // EIR including insurance, guarantee & application fees
        presentValue -= loanGuaranty;
        cashFlow[0] += applicationFee;
        globalEffectiveInterestRate =
                eirCalculator.calculateEffectiveInterestRate(presentValue, cashFlow)
                        * 1200 / periodDurationInMonths;
        System.out.printf("TAEG = %s\n", globalEffectiveInterestRate);

        // Fee impact on interest rate
        feesImpactOnInterestRate = globalEffectiveInterestRate - eirWithInsurance;
        System.out.printf("TAEF = %s\n", feesImpactOnInterestRate);
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

