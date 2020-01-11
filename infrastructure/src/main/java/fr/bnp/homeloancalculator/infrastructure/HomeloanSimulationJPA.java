package fr.bnp.homeloancalculator.infrastructure;

import fr.bnp.homeloancalculator.domain.mortgage.HomeloanSimulation;
import fr.bnp.homeloancalculator.domain.mortgage.Periodicity;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity(name = "HOMELOAN_SIMULATION")
public class HomeloanSimulationJPA {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "SIMULATION_DATE")
    private Date simulationDate;

    @Column(name = "PERSONAL_DEPOSIT")
    private double personalDeposit;

    @Column(name = "LOAN_AMOUNT")
    private double loanAmount;

    @Column(name = "LOAN_PAYMENT")
    private double loanPayment;

    @Column(name = "LOAN_INTEREST_RATE")
    private double loanInterestRate;

    @Column(name = "LOAN_INSURANCE_RATE")
    private double loanInsuranceRate;

    @Column(name = "LOAN_GUARANTY_RATE")
    private double loanGuarantyRate;

    @Column(name = "LOAN_DURATION")
    private int loanDuration;

    @Enumerated(EnumType.STRING)
    @Column(name = "PERIODICITY")
    private Periodicity periodicity;

    @Column(name = "GLOBAL_LOAN_PAYMENT")
    private double globalLoanPayment;

    @Column(name = "LOAN_COST")
    private double loanCost;

    @Column(name = "INTEREST_COST")
    private double interestCost;

    @Column(name = "INSURANCE_COST")
    private double insuranceCost;

    @Column(name = "APPLICATION_FEE")
    private double applicationFee;

    @Column(name = "LOAN_GUARANTY")
    private double loanGuaranty;

    @Column(name = "EFFECTIVE_INTEREST_RATE")
    private double globalEffectiveInterestRate;

    @Column(name = "INSURANCE_IMPACT_ON_INTEREST_RATE")
    private double insuranceImpactOnInterestRate;

    @Column(name = "FEES_IMPACT_ON_INTEREST_RATE")
    private double feesImpactOnInterestRate;

    @Column(name = "CREDIT_REQUEST")
    private boolean creditRequest;

    public HomeloanSimulationJPA() {
    }

    protected HomeloanSimulationJPA(HomeloanSimulation homeloanSimulation) {
        this.id = homeloanSimulation.getId().toString();
        this.simulationDate = homeloanSimulation.getSimulationDate();
        this.personalDeposit = homeloanSimulation.getPersonalDeposit();
        this.loanAmount = homeloanSimulation.getLoanAmount();
        this.loanPayment = homeloanSimulation.getLoanPayment();
        this.loanInterestRate = homeloanSimulation.getLoanInterestRate();
        this.loanInsuranceRate = homeloanSimulation.getLoanInsuranceRate();
        this.loanGuarantyRate = homeloanSimulation.getLoanGuarantyRate();
        this.loanDuration = homeloanSimulation.getLoanDuration();
        this.periodicity = homeloanSimulation.getPeriodicity();
        this.globalLoanPayment = homeloanSimulation.getGlobalLoanPayment();
        this.loanCost = homeloanSimulation.getLoanCost();
        this.interestCost = homeloanSimulation.getInterestCost();
        this.insuranceCost = homeloanSimulation.getInsuranceCost();
        this.applicationFee = homeloanSimulation.getApplicationFee();
        this.loanGuaranty = homeloanSimulation.getLoanGuaranty();
        this.globalEffectiveInterestRate = homeloanSimulation.getGlobalEffectiveInterestRate();
        this.insuranceImpactOnInterestRate = homeloanSimulation.getInsuranceImpactOnInterestRate();
        this.feesImpactOnInterestRate = homeloanSimulation.getFeesImpactOnInterestRate();
        this.creditRequest = homeloanSimulation.isCreditRequest();
    }

    protected HomeloanSimulation toHomeloanSimulation () {
        return new HomeloanSimulation(
                UUID.fromString(getId()),
                getSimulationDate(),
                getPersonalDeposit(),
                getLoanAmount(),
                getLoanPayment(),
                getLoanInterestRate(),
                getLoanInsuranceRate(),
                getLoanGuarantyRate(),
                getLoanDuration(),
                getPeriodicity(),
                getGlobalLoanPayment(),
                getLoanCost(),
                getInterestCost(),
                getInsuranceCost(),
                getApplicationFee(),
                getLoanGuaranty(),
                getGlobalEffectiveInterestRate(),
                getInsuranceImpactOnInterestRate(),
                getFeesImpactOnInterestRate(),
                getCreditRequest());
    }

    public String getId() {
        return id;
    }

    public Date getSimulationDate() { return simulationDate; }

    public double getPersonalDeposit() { return personalDeposit; }

    public double getLoanAmount() {
        return loanAmount;
    }

    public double getLoanPayment() {
        return loanPayment;
    }

    public double getLoanInterestRate() {
        return loanInterestRate;
    }

    public double getLoanInsuranceRate() {
        return loanInsuranceRate;
    }

    public double getLoanGuarantyRate() {
        return loanGuarantyRate;
    }

    public int getLoanDuration() {
        return loanDuration;
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public double getGlobalLoanPayment() {
        return globalLoanPayment;
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

    public double getLoanGuaranty() { return loanGuaranty; }

    public double getGlobalEffectiveInterestRate() {
        return globalEffectiveInterestRate;
    }

    public double getInsuranceImpactOnInterestRate() {
        return insuranceImpactOnInterestRate;
    }

    public double getFeesImpactOnInterestRate() {
        return feesImpactOnInterestRate;
    }

    public boolean getCreditRequest() {
        return creditRequest;
    }
}
