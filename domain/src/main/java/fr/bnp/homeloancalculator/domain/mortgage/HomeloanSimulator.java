package fr.bnp.homeloancalculator.domain.mortgage;

import org.springframework.beans.factory.annotation.Autowired;

public class HomeloanSimulator {

    private final static double LOAN_INTEREST_RATE = 1.00;

    enum  CalculationMode {
        CAPITALTARGET, PAYMENTTARGET;
    }

    private double personalDeposit;
    private double loanAmount;
    private double loanPayment;
    private int loanDuration;
    private Periodicity periodicity;

    private double loanCost;
    private double interestCost;
    private double applicationFee;
    private double loanGuaranty;

    private double effectiveInterestRate;
    private double insuranceImpactOnInterestRate;
    private double feesImpactOnInterestRate;

    @Autowired
    private Calculator loanCalculator;

}
