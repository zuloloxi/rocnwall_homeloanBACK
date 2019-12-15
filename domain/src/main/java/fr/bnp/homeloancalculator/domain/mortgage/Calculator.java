package fr.bnp.homeloancalculator.domain.mortgage;

import javax.money.MonetaryAmount;

public interface Calculator {
    MonetaryAmount calculateLoanAmount (Periodicity periodicity, double interestRate, MonetaryAmount loanPayment, int loanDuration);
    MonetaryAmount calculateLoanPayment (Periodicity periodicity, double interestRate, MonetaryAmount loanAmount, int loanDuration);

}
