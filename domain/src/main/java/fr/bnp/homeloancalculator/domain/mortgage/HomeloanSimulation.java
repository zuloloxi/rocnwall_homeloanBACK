package fr.bnp.homeloancalculator.domain.mortgage;

import fr.bnp.homeloancalculator.domain.calculator.CalculatorImpl;

import java.util.Date;
import java.util.UUID;

public class HomeloanSimulation {

    private UUID id;
    private Date simulationDate;
    private double personalDeposit;
    private double loanAmount;
    private double loanPayment;
    private double loanInterestRate;
    private double loanInsuranceRate; // % on loan amount
    private double loanGuarantyRate;  // % on loan amount
    private int loanDuration;
    private Periodicity periodicity;
    private double globalLoanPayment; // Loan payment including insurance
    private double loanCost;
    private double interestCost;
    private double insuranceCost;
    private double applicationFee;
    private double loanGuaranty;
    private double globalEffectiveInterestRate;
    private double insuranceImpactOnInterestRate;
    private double feesImpactOnInterestRate;
    private boolean creditRequest;

    // Constructor used when the simulation is created for the first time and credit cost is not yet calculated
    public HomeloanSimulation(double personalDeposit, double loanAmount, double loanPayment,
                              double loanInterestRate, double loanInsuranceRate, double loanGuarantyRate, double applicationFee,
                              int loanDuration, Periodicity periodicity) {

        this.id = UUID.randomUUID();
        this.personalDeposit = personalDeposit;
        this.loanAmount = loanAmount;
        this.loanPayment = loanPayment;
        this.loanInterestRate = loanInterestRate;
        this.loanInsuranceRate = loanInsuranceRate;
        this.loanGuarantyRate = loanGuarantyRate;
        this.applicationFee = applicationFee;
        this.loanDuration = loanDuration;
        this.periodicity = periodicity;
    }

    // Constructor used when simulation is retrieved from the database (credit cost has already been calculated)
    public HomeloanSimulation(UUID id, double personalDeposit, double loanAmount, double loanPayment,
                              double loanInterestRate, double loanInsuranceRate, double loanGuarantyRate,
                              int loanDuration, Periodicity periodicity, double globalLoanPayment,
                              double loanCost, double interestCost, double insuranceCost, double applicationFee,
                              double loanGuaranty, double globalEffectiveInterestRate,
                              double insuranceImpactOnInterestRate, double feesImpactOnInterestRate, boolean creditRequest) {
        this.id = id;
        this.personalDeposit = personalDeposit;
        this.loanAmount = loanAmount;
        this.loanPayment = loanPayment;
        this.loanInterestRate = loanInterestRate;
        this.loanInsuranceRate = loanInsuranceRate;
        this.loanGuarantyRate = loanGuarantyRate;
        this.loanDuration = loanDuration;
        this.periodicity = periodicity;
        this.globalLoanPayment = globalLoanPayment;
        this.loanCost = loanCost;
        this.interestCost = interestCost;
        this.insuranceCost = insuranceCost;
        this.applicationFee = applicationFee;
        this.loanGuaranty = loanGuaranty;
        this.globalEffectiveInterestRate = globalEffectiveInterestRate;
        this.insuranceImpactOnInterestRate = insuranceImpactOnInterestRate;
        this.feesImpactOnInterestRate = feesImpactOnInterestRate;
        this.creditRequest = creditRequest;
    }

    public void calculateCost(CalculationMode calculationMode) {
        Calculator calculator = new CalculatorImpl(loanInterestRate, loanInsuranceRate, loanGuarantyRate, applicationFee,
                loanDuration, periodicity.numberOfMonths(), loanAmount, loanPayment);
        calculator.calculateCost(calculationMode);
        loanAmount = calculator.getLoanAmount();
        loanPayment = calculator.getLoanPayment();
        insuranceCost = calculator.getInsuranceCost();
        loanGuaranty = calculator.getLoanGuaranty();
        globalLoanPayment = calculator.getGlobalLoanPayment();
        applicationFee = calculator.getApplicationFee();
        interestCost = calculator.getInterestCost();
        loanCost = calculator.getLoanCost();
        insuranceImpactOnInterestRate = calculator.getInsuranceImpactOnInterestRate();
        globalEffectiveInterestRate = calculator.getGlobalEffectiveInterestRate();
        feesImpactOnInterestRate = calculator.getFeesImpactOnInterestRate();
    }

    public void update(HomeloanSimulation homeloanSimulationWithNewInformation) {
        this.simulationDate = homeloanSimulationWithNewInformation.getSimulationDate();
        this.personalDeposit = homeloanSimulationWithNewInformation.getPersonalDeposit();
        this.loanAmount = homeloanSimulationWithNewInformation.getLoanAmount();
        this.loanPayment = homeloanSimulationWithNewInformation.getLoanPayment();
        this.loanInterestRate = homeloanSimulationWithNewInformation.getLoanInterestRate();
        this.loanInsuranceRate = homeloanSimulationWithNewInformation.getLoanInsuranceRate();
        this.loanGuarantyRate = homeloanSimulationWithNewInformation.getLoanGuarantyRate();
        this.loanDuration = homeloanSimulationWithNewInformation.getLoanDuration();
        this.periodicity = homeloanSimulationWithNewInformation.getPeriodicity();
        this.globalLoanPayment = homeloanSimulationWithNewInformation.getGlobalLoanPayment();
        this.loanCost = homeloanSimulationWithNewInformation.getLoanCost();
        this.interestCost = homeloanSimulationWithNewInformation.getInterestCost();
        this.insuranceCost = homeloanSimulationWithNewInformation.getInsuranceCost();
        this.applicationFee = homeloanSimulationWithNewInformation.getApplicationFee();
        this.loanGuaranty = homeloanSimulationWithNewInformation.getLoanGuaranty();
        this.globalEffectiveInterestRate = homeloanSimulationWithNewInformation.getGlobalEffectiveInterestRate();
        this.insuranceImpactOnInterestRate = homeloanSimulationWithNewInformation.getInsuranceImpactOnInterestRate();
        this.feesImpactOnInterestRate = homeloanSimulationWithNewInformation.getFeesImpactOnInterestRate();
        this.creditRequest = homeloanSimulationWithNewInformation.isCreditRequest();
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

    public UUID getId() {
        return id;
    }

    public Date getSimulationDate() {
        return simulationDate;
    }

    public double getLoanInsuranceRate() {
        return loanInsuranceRate;
    }

    public double getLoanGuarantyRate() {
        return loanGuarantyRate;
    }

    public double getGlobalLoanPayment() {
        return globalLoanPayment;
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

    public double getInsuranceCost() {
        return insuranceCost;
    }

    public double getApplicationFee() {
        return applicationFee;
    }

    public double getLoanGuaranty() {
        return loanGuaranty;
    }

    public double getLoanInterestRate() {
        return loanInterestRate;
    }

    public double getGlobalEffectiveInterestRate() {
        return globalEffectiveInterestRate;
    }

    public double getInsuranceImpactOnInterestRate() {
        return insuranceImpactOnInterestRate;
    }

    public double getFeesImpactOnInterestRate() {
        return feesImpactOnInterestRate;
    }

    public boolean isCreditRequest() {
        return creditRequest;
    }
}
