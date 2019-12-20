package fr.bnp.homeloancalculator.domain.calculator;

import fr.bnp.homeloancalculator.domain.mortgage.Calculator;
import fr.bnp.homeloancalculator.domain.mortgage.Periodicity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorImplTest {
    private final Calculator calculator = new CalculatorImpl();

    @Test
    void should_return_xxxx_when_calculateLoanAmount() {
        Assertions.assertEquals(12002.00,
                calculator.calculateLoanAmount(
                        Periodicity.MONTHLY,
                        1.20,
                        2629.00,
                        15));
    }

    @Test
    void calculateLoanPayment() {
    }

    @Test
    void calculateCostOverview() {
    }
}