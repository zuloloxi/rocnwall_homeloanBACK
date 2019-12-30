package fr.bnp.homeloancalculator.domain.mortgage;

import fr.bnp.homeloancalculator.domain.calculator.Calculator;
import fr.bnp.homeloancalculator.domain.math.EIRCalculator;
import fr.bnp.homeloancalculator.domain.calculator.CalculatorImpl;

public class HomeloanSimulation {

    private final static double LOAN_INTEREST_RATE = 1.00;

    private CalculationMode calculationMode;
    private double personalDeposit;
    private double loanAmount;
    private double loanPayment;
    private int loanDuration;
    private Periodicity periodicity;

    private double loanCost;
    private double interestCost;
    private double insuranceFee;
    private double applicationFee;
    private double loanGuarantee;

    private double nominalInterestRate;
    private double globalEffectiveInterestRate;
    private double insuranceImpactOnInterestRate;

    // Constructor used when credit cost must be calculated
    public HomeloanSimulation(CalculationMode calculationMode, double loanPayment, double loanAmount, int loanDuration, Periodicity periodicity) {

        Calculator calculator = new CalculatorImpl();

        this.calculationMode = calculationMode;
        this.nominalInterestRate = LOAN_INTEREST_RATE;
        this.loanDuration = loanDuration;
        this.periodicity = periodicity;
        this.loanPayment = calculationMode == CalculationMode.PAYMENT_TARGET ?
                loanPayment : calculator.calculateLoanPayment(LOAN_INTEREST_RATE, loanAmount, loanDuration, periodicity.numberOfMonths());
        this.loanAmount = calculationMode == CalculationMode.CAPITAL_TARGET ?
                loanAmount : calculator.calculateLoanAmount(LOAN_INTEREST_RATE, loanPayment, loanDuration, periodicity.numberOfMonths());
        calculator.calculateCost(this);
    }

    // Constructor used when simulation is retrieved from the database (credit cost has already been calculated)
    public HomeloanSimulation(double personalDeposit, double loanAmount, double loanPayment, int loanDuration,
                              Periodicity periodicity, double loanCost, double interestCost, double applicationFee,
                              double loanGuarantee, double nominalInterestRate, double globalEffectiveInterestRate,
                              double insuranceImpactOnInterestRate) {
        this.personalDeposit = personalDeposit;
        this.loanAmount = loanAmount;
        this.loanPayment = loanPayment;
        this.loanDuration = loanDuration;
        this.periodicity = periodicity;
        this.loanCost = loanCost;
        this.interestCost = interestCost;
        this.applicationFee = applicationFee;
        this.loanGuarantee = loanGuarantee;
        this.nominalInterestRate = nominalInterestRate;
        this.globalEffectiveInterestRate = globalEffectiveInterestRate;
        this.insuranceImpactOnInterestRate = insuranceImpactOnInterestRate;
    }

    public CalculationMode getCalculationMode() {
        return calculationMode;
    }

    public double getPersonalDeposit() {
        return personalDeposit;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public double getLoanPayment() {
        return loanPayment;
    }

    public int getLoanDuration() {
        return loanDuration;
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public double getLoanCost() {
        return loanCost;
    }

    public double getInterestCost() {
        return interestCost;
    }

    public double getInsuranceFee() {
        return insuranceFee;
    }

    public double getApplicationFee() {
        return applicationFee;
    }

    public double getLoanGuarantee() {
        return loanGuarantee;
    }

    public double getNominalInterestRate() {
        return nominalInterestRate;
    }

    public void setLoanCost(double loanCost) {
        this.loanCost = loanCost;
    }

    public void setInterestCost(double interestCost) {
        this.interestCost = interestCost;
    }

    public void setInsuranceFee(double insuranceFee) {
        this.insuranceFee = insuranceFee;
    }

    public void setApplicationFee(double applicationFee) {
        this.applicationFee = applicationFee;
    }

    public void setLoanGuarantee(double loanGuarantee) {
        this.loanGuarantee = loanGuarantee;
    }

    public void setGlobalEffectiveInterestRate(double globalEffectiveInterestRate) {
        this.globalEffectiveInterestRate = globalEffectiveInterestRate;
    }

    public void setInsuranceImpactOnInterestRate(double insuranceImpactOnInterestRate) {
        this.insuranceImpactOnInterestRate = insuranceImpactOnInterestRate;
    }

    public double getGlobalEffectiveInterestRate() {
        return globalEffectiveInterestRate;
    }

    public double getInsuranceImpactOnInterestRate() {
        return insuranceImpactOnInterestRate;
    }
}
