package fr.bnp.homeloancalculator.domain.calculator;

import fr.bnp.homeloancalculator.domain.mortgage.Calculator;
import fr.bnp.homeloancalculator.domain.mortgage.Periodicity;
import org.springframework.stereotype.Component;

import javax.money.MonetaryAmount;

@Component
public class CalculatorImpl implements Calculator {
    @Override
    public MonetaryAmount calculateLoanAmount(Periodicity periodicity, double interestRate, MonetaryAmount loanPayment, int loanDuration) {
        return null;
    }

    @Override
    public MonetaryAmount calculateLoanPayment(Periodicity periodicity, double interestRate, MonetaryAmount loanAmount, int loanDuration) {
        return null;
    }
}
