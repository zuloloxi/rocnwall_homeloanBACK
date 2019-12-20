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
        double result = calculator.calculateLoanAmount(
                Periodicity.MONTHLY,
                1.00,
                1244.547635,
                15);
        /* Arrondir à deux décimales */
        double arrondi = (double)Math.round(result * 100)/100;

        Assertions.assertEquals(207946.37,arrondi);
    }

    @Test
    void should_return_xxxx_when_calculateLoanPayment() {
        double result = calculator.calculateLoanPayment(
                Periodicity.MONTHLY,
                1.00,
                300000.00,
                15);

        double arrondi = (double)Math.round(result * 100)/100;
        Assertions.assertEquals(1795.48,arrondi);
    }

    @Test
    void should_return_182_when_calculateCostOverview () {
        double result = calculator.calculateCostOverview(
                Periodicity.MONTHLY,
                300000.00,
                1795.483544,
                15);

        double arrondi = (double)Math.round(result * 100)/100;
        Assertions.assertEquals(1.82,arrondi);
    }

    @Test
    void should_return_186_when_calculateCostOverview () {
        double result = calculator.calculateCostOverview(
                Periodicity.MONTHLY,
                200000.00,
                1214.66,
                15);

        double arrondi = (double)Math.round(result * 100)/100;
        Assertions.assertEquals(1.86,arrondi);
    }
}