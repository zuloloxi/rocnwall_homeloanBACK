package fr.bnp.homeloancalculator.domain.mortgage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HomeloanSimulationTest {

    @Test
    void should_return_xxxx_when_calculateLoanAmount () {
        HomeloanSimulation homeloanSimulation =
                new HomeloanSimulation(CalculationMode.PAYMENT_TARGET, 1244.547635, 0, 15, Periodicity.MONTHLY);
        /* Arrondir à deux décimales */
        double arrondi = (double)Math.round(homeloanSimulation.getLoanAmount() * 100)/100;

        Assertions.assertEquals(207946.37,arrondi);
    }

    @Test
    void should_return_xxxx_when_calculateLoanPayment () {
        HomeloanSimulation homeloanSimulation =
                new HomeloanSimulation(CalculationMode.CAPITAL_TARGET, 0,300000.00, 15, Periodicity.MONTHLY);
        double arrondi = (double)Math.round(homeloanSimulation.getLoanPayment() * 100)/100;
        Assertions.assertEquals(1795.48,arrondi);
    }

    @Test
    void should_return_182_when_calculateCostOverview () {
        HomeloanSimulation homeloanSimulation =
                new HomeloanSimulation(CalculationMode.CAPITAL_TARGET, 0,300000.00, 15, Periodicity.MONTHLY);
        double arrondi = (double)Math.round(homeloanSimulation.getGlobalEffectiveInterestRate() * 100)/100;
        Assertions.assertEquals(1.64,arrondi);
    }

    @Test
    void should_return_186_when_calculateCostOverview () {
        HomeloanSimulation homeloanSimulation =
                new HomeloanSimulation(CalculationMode.CAPITAL_TARGET, 0,200000.00, 15, Periodicity.MONTHLY);

        double arrondi = (double)Math.round(homeloanSimulation.getGlobalEffectiveInterestRate() * 100)/100;
        Assertions.assertEquals(1.66,arrondi);
    }

}