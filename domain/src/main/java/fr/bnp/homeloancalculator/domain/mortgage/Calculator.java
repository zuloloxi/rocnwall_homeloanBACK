package fr.bnp.homeloancalculator.domain.mortgage;

public interface Calculator {
    Double calculateLoanAmount (Periodicity periodicity, double interestRate, double loanPayment, int loanDuration);
    Double calculateLoanPayment (Periodicity periodicity, double interestRate, double loanAmount, int loanDuration);
    Double calculateCostOverview (Periodicity periodicity, double loanAmount, double loanPayment,int loanDuration);
}
