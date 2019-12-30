package fr.bnp.homeloancalculator.domain.calculator;

import fr.bnp.homeloancalculator.domain.math.EIRCalculator;
import fr.bnp.homeloancalculator.domain.mortgage.HomeloanSimulation;

public class CalculatorImpl implements Calculator {

    private final static double INSURANCE_INTEREST_RATE = 3.30;   // Total insurance fees = 3,300% of loan amount
    private final static double GUARANTEE_INTEREST_RATE = 1.305;  // Initial guarantee fee = 1,305% of loan amount
    private final static double APPLICATION_FEE = 1000.00;        // Application fee = 1000 €

    EIRCalculator eirCalculator = new EIRCalculator();

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
    private double insuranceImpactOnInterestRate;
    private double globalEffectiveInterestRate;


    public void calculateCost(HomeloanSimulation homeloanSimulation) {
        loanDuration = homeloanSimulation.getLoanDuration();
        periodDurationInMonths = homeloanSimulation.getPeriodicity().numberOfMonths();
        // length of the term in periods
        durationInPeriods = getNumberOfPeriods(loanDuration, periodDurationInMonths);
        loanAmount = homeloanSimulation.getLoanAmount();
        loanPayment = homeloanSimulation.getLoanPayment();

        // Calcul of the cost credit
        insuranceFee = loanAmount * (INSURANCE_INTEREST_RATE / 100);
        loanGuarantee = loanAmount * (GUARANTEE_INTEREST_RATE / 100) * periodDurationInMonths;
        applicationFee = APPLICATION_FEE;
        interestCost = (loanPayment * durationInPeriods) - loanAmount;
        loanCost = interestCost + insuranceFee + applicationFee + loanGuarantee;

        calculateRates();

        // Update simulation structure
        homeloanSimulation.setInsuranceFee(insuranceFee);
        homeloanSimulation.setLoanGuarantee(loanGuarantee);
        homeloanSimulation.setApplicationFee(applicationFee);
        homeloanSimulation.setInterestCost(interestCost);
        homeloanSimulation.setLoanCost(loanCost);
        homeloanSimulation.setGlobalEffectiveInterestRate(globalEffectiveInterestRate);
        homeloanSimulation.setInsuranceImpactOnInterestRate(insuranceImpactOnInterestRate);
    }

    private void calculateRates() {
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

    public double calculateLoanAmount(double annualInterestRate, double loanPayment, int loanDuration, int periodDurationInMonths) {

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

    public double calculateLoanPayment(double annualInterestRate, double loanAmount, int loanDuration, int periodDurationInMonths) {

        // Convert interest rate into a decimal; eg. 0,85% = 0.0085
        annualInterestRate /= 100.0;

        // Periodic interest rate
        double periodicRate = getPeriodicRate(annualInterestRate, periodDurationInMonths);

        // length of the term in periods
        int durationInPeriods = getNumberOfPeriods(loanDuration, periodDurationInMonths);

        // Calculate the periodic payment
        double periodicPayment = loanAmount * periodicRate
                / (1 - Math.pow(1 + periodicRate, -durationInPeriods));

        return periodicPayment;
    }

    private double getPeriodicRate(double annualInterestRate, int numberOfMonths) {
        return (annualInterestRate * numberOfMonths / 12.0);
    }

    private int getNumberOfPeriods(int loanDuration, int numberOfMonths) {
        return (loanDuration * 12) / numberOfMonths;
    }

}

