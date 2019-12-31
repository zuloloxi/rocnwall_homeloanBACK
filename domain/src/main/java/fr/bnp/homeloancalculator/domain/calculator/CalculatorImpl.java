package fr.bnp.homeloancalculator.domain.calculator;

import fr.bnp.homeloancalculator.domain.math.EIRCalculator;
import fr.bnp.homeloancalculator.domain.mortgage.CalculationMode;

public class CalculatorImpl implements Calculator {

    private final static double INSURANCE_INTEREST_RATE = 3.30;   // Total insurance fees = 3,300% of loan amount
    private final static double GUARANTEE_INTEREST_RATE = 1.305;  // Initial guarantee fee = 1,305% of loan amount
    private final static double APPLICATION_FEE = 1000.00;        // Application fee = 1000 €

    EIRCalculator eirCalculator = new EIRCalculator();

    private CalculationMode calculationMode;
    private int loanDuration;
    private int periodDurationInMonths;
    private int durationInPeriods;     // length of the term in periods
    private double loanAmount;
    private double loanPayment;
    private double insuranceFee;
    private double loanGuarantee;
    private double applicationFee;
    private double interestCost;
    private double loanCost;
    private double nominalInterestRate;
    private double insuranceImpactOnInterestRate;
    private double globalEffectiveInterestRate;

    public CalculatorImpl(CalculationMode calculationMode,
                          double nominalInterestRate,
                          int loanDuration,
                          int periodDurationInMonths,
                          double loanAmount,
                          double loanPayment) {
        this.calculationMode = calculationMode;
        this.loanDuration = loanDuration;
        this.periodDurationInMonths = periodDurationInMonths;
        this.loanAmount = loanAmount;
        this.loanPayment = loanPayment;
        this.nominalInterestRate = nominalInterestRate;
    }

    public void calculateCost() {
        durationInPeriods = getNumberOfPeriods(loanDuration, periodDurationInMonths);

        // Calculate loan amount or periodic payment depending on the input parameters filled by the user
        loanAmount = calculationMode == CalculationMode.CAPITAL_TARGET ?
                loanAmount : calculateLoanAmount(nominalInterestRate, loanPayment, loanDuration, periodDurationInMonths);
        loanPayment = calculationMode == CalculationMode.PAYMENT_TARGET ?
                loanPayment : calculateLoanPayment(nominalInterestRate, loanAmount, loanDuration, periodDurationInMonths);

        // Calcul of the cost credit
        insuranceFee = loanAmount * (INSURANCE_INTEREST_RATE / 100);
        loanGuarantee = loanAmount * (GUARANTEE_INTEREST_RATE / 100) * periodDurationInMonths;
        applicationFee = APPLICATION_FEE;
        interestCost = (loanPayment * durationInPeriods) - loanAmount;
        loanCost = interestCost + insuranceFee + applicationFee + loanGuarantee;

        calculateInterestRates();
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public double getLoanPayment() {
        return loanPayment;
    }

    public double getInsuranceFee() {
        return insuranceFee;
    }

    public double getLoanGuarantee() {
        return loanGuarantee;
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

    private void calculateInterestRates() {
        double periodicInsuranceFee = insuranceFee / durationInPeriods;
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
        insuranceImpactOnInterestRate = eirWithInsurance - nominalInterestRate;

        // EIR including insurance, guarantee & application fees
        presentValue -= loanGuarantee;
        cashFlow[0] += applicationFee;
        globalEffectiveInterestRate =
                eirCalculator.calculateEffectiveInterestRate(presentValue, cashFlow)
                        * 1200 / periodDurationInMonths;
        System.out.printf("EIR with insurance & fees = %s\n", globalEffectiveInterestRate);
    }

    private double calculateLoanAmount(double annualInterestRate, double loanPayment, int loanDuration, int periodDurationInMonths) {

        // Convert interest rate into a decimal; eg. 0,85% = 0.0085
        annualInterestRate /= 100.0;

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

        // Convert interest rate into a decimal; eg. 0,85% = 0.0085
        annualInterestRate /= 100.0;

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

}

