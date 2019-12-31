package fr.bnp.homeloancalculator.domain.mortgage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HomeloanSimulationTest {

    private final static double LOAN_INTEREST_RATE = 1.00;

    @Test
    void given_Periodic_Payment_Then_Calculate_Loan_Amount () {
        HomeloanSimulation homeloanSimulation =
                new HomeloanSimulation(CalculationMode.PAYMENT_TARGET, LOAN_INTEREST_RATE, 1244.547635, 0, 15, Periodicity.MONTHLY);
        homeloanSimulation.calculateCost();
        /* Arrondir à deux décimales */
        double arrondi = (double)Math.round(homeloanSimulation.getLoanAmount() * 100)/100;

        Assertions.assertEquals(207946.37,arrondi);
    }

    @Test
    void given_Loan_Amount_Then_Calculate_Periodic_Payment () {
        HomeloanSimulation homeloanSimulation =
                new HomeloanSimulation(CalculationMode.CAPITAL_TARGET, LOAN_INTEREST_RATE, 0,300000.00, 15, Periodicity.MONTHLY);
        homeloanSimulation.calculateCost();
        double arrondi = (double)Math.round(homeloanSimulation.getLoanPayment() * 100)/100;
        Assertions.assertEquals(1795.48,arrondi);
    }

    @Test
    void should_return_182_when_calculateCostOverview () {
        HomeloanSimulation homeloanSimulation =
                new HomeloanSimulation(CalculationMode.CAPITAL_TARGET, LOAN_INTEREST_RATE, 0,300000.00, 15, Periodicity.MONTHLY);
        homeloanSimulation.calculateCost();
        double arrondi = (double)Math.round(homeloanSimulation.getGlobalEffectiveInterestRate() * 100)/100;
        Assertions.assertEquals(1.64,arrondi);
    }

    @Test
    void should_return_186_when_calculateCostOverview () {
        HomeloanSimulation homeloanSimulation =
                new HomeloanSimulation(CalculationMode.CAPITAL_TARGET, LOAN_INTEREST_RATE, 0,200000.00, 15, Periodicity.MONTHLY);
        homeloanSimulation.calculateCost();
        double arrondi = (double)Math.round(homeloanSimulation.getGlobalEffectiveInterestRate() * 100)/100;
        Assertions.assertEquals(1.66,arrondi);
    }

}