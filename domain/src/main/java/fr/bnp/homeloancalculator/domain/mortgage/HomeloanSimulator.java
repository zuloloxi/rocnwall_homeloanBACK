package fr.bnp.homeloancalculator.domain.mortgage;

import org.springframework.beans.factory.annotation.Autowired;

import javax.money.MonetaryAmount;

public class HomeloanSimulator {

    private final static double LOAN_INTEREST_RATE = 0.0085;

    enum  CalculationMode {
        CAPITALTARGET, PAYMENTTARGET;
    }

    private MonetaryAmount personalDeposit;
    private MonetaryAmount loanAmount;
    private MonetaryAmount loanPayment;
    private int loanDuration;
    private Periodicity periodicity;

    private MonetaryAmount  loanCost;
    private MonetaryAmount interestCost;
    private MonetaryAmount applicationFee;
    private MonetaryAmount loanGuaranty;

    private double effectiveInterestRate;
    private double insuranceImpactOnInterestRate;
    private double feesImpactOnInterestRate;

    @Autowired
    private Calculator loanCalculator;

}
