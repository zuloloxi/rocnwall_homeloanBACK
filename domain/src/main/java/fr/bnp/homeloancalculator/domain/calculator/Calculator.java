package fr.bnp.homeloancalculator.domain.calculator;

import fr.bnp.homeloancalculator.domain.mortgage.HomeloanSimulation;

public interface Calculator {
    double calculateLoanAmount (double interestRate, double loanPayment, int loanDuration, int paymentPeriodicity);
    double calculateLoanPayment (double interestRate, double loanAmount, int loanDuration, int paymentPeriodicity);
    void calculateCost(HomeloanSimulation homeloanSimulation);
}
