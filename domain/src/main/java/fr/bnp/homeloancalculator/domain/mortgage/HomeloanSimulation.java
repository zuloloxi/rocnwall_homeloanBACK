package fr.bnp.homeloancalculator.domain.mortgage;

import fr.bnp.homeloancalculator.domain.calculator.Calculator;
import fr.bnp.homeloancalculator.domain.calculator.CalculatorImpl;

import java.util.UUID;

public class HomeloanSimulation {

    private final static double LOAN_INTEREST_RATE = 1.00;

    private UUID id;
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
    public HomeloanSimulation(CalculationMode calculationMode, double nominalInterestRate, double loanPayment, double loanAmount,
                              int loanDuration, Periodicity periodicity) {

        this.id = UUID.randomUUID();
        this.calculationMode = calculationMode;
        this.nominalInterestRate = nominalInterestRate;
        this.loanDuration = loanDuration;
        this.periodicity = periodicity;
        this.loanAmount = loanAmount;
        this.loanPayment = loanPayment;
    }

    public void calculateCost() {
        Calculator calculator = new CalculatorImpl(calculationMode,nominalInterestRate, loanDuration, periodicity.numberOfMonths(), loanAmount, loanPayment);
        calculator.calculateCost();
        loanAmount = calculator.getLoanAmount();
        loanPayment = calculator.getLoanPayment();
        insuranceFee = calculator.getInsuranceFee();
        loanGuarantee = calculator.getLoanGuarantee();
        applicationFee = calculator.getApplicationFee();
        interestCost = calculator.getInterestCost();
        loanCost = calculator.getLoanCost();
        insuranceImpactOnInterestRate = calculator.getInsuranceImpactOnInterestRate();
        globalEffectiveInterestRate = calculator.getGlobalEffectiveInterestRate();
    }

    // Constructor used when simulation is retrieved from the database (credit cost has already been calculated)
    public HomeloanSimulation(UUID id, double personalDeposit, double loanAmount, double loanPayment, int loanDuration,
                              Periodicity periodicity, double loanCost, double interestCost, double applicationFee,
                              double loanGuarantee, double nominalInterestRate, double globalEffectiveInterestRate,
                              double insuranceImpactOnInterestRate) {
        this.id = id;
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

    public double getGlobalEffectiveInterestRate() {
        return globalEffectiveInterestRate;
    }

    public double getInsuranceImpactOnInterestRate() {
        return insuranceImpactOnInterestRate;
    }

}
