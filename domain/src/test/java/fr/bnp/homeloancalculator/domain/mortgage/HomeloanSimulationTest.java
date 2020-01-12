package fr.bnp.homeloancalculator.domain.mortgage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HomeloanSimulationTest {

    private final static double LOAN_INTEREST_RATE = 1.00;
    private final static double INSURANCE_INTEREST_RATE = 3.30;   // Total insurance fees = 3,300% of loan amount
    private final static double GUARANTEE_INTEREST_RATE = 1.305;  // Initial guarantee fee = 1,305% of loan amount
    private final static double APPLICATION_FEE = 1000.00;        // Application fee = 1000 €

    @Test
    void given_Periodic_Payment_Then_Calculate_Loan_Amount () {
        HomeloanSimulation homeloanSimulation =
                new HomeloanSimulation(0, 0, 1244.547635,
                        LOAN_INTEREST_RATE, INSURANCE_INTEREST_RATE, GUARANTEE_INTEREST_RATE, APPLICATION_FEE,
                        15, Periodicity.MONTHLY);
        /* Arrondir à deux décimales */
        double arrondi = (double)Math.round(homeloanSimulation.getLoanAmount() * 100)/100;

        Assertions.assertEquals(207946.37,arrondi);
    }

    @Test
    void given_Loan_Amount_Then_Calculate_Periodic_Payment () {
        HomeloanSimulation homeloanSimulation =
                new HomeloanSimulation(0, 300000.00, 0,
                        LOAN_INTEREST_RATE, INSURANCE_INTEREST_RATE, GUARANTEE_INTEREST_RATE, APPLICATION_FEE,
                        15, Periodicity.MONTHLY);
        double arrondi = (double)Math.round(homeloanSimulation.getLoanPayment() * 100)/100;
        Assertions.assertEquals(1795.48,arrondi);
    }

    @Test
    void should_return_182_when_calculateCostOverview () {
        HomeloanSimulation homeloanSimulation =
                new HomeloanSimulation(0, 300000.00, 0,
                        LOAN_INTEREST_RATE, INSURANCE_INTEREST_RATE, GUARANTEE_INTEREST_RATE, APPLICATION_FEE,
                        15, Periodicity.MONTHLY);
        double arrondi = (double)Math.round(homeloanSimulation.getGlobalEffectiveInterestRate() * 100)/100;
        Assertions.assertEquals(1.64,arrondi);
        Assertions.assertEquals(300000, homeloanSimulation.getLoanAmount());
        Assertions.assertEquals(1795, (double)Math.round(homeloanSimulation.getLoanPayment()));
        Assertions.assertEquals(1000, homeloanSimulation.getApplicationFee());
        Assertions.assertEquals(9900, homeloanSimulation.getInsuranceCost());
        Assertions.assertEquals(3915, (double)Math.round((homeloanSimulation.getLoanGuaranty())));
        Assertions.assertEquals(1850, (double)Math.round(homeloanSimulation.getGlobalLoanPayment()));
        Assertions.assertEquals(23187, (double)Math.round(homeloanSimulation.getInterestCost()));
        Assertions.assertEquals(38002, (double)Math.round(homeloanSimulation.getLoanCost()));
        Assertions.assertEquals(1.0, (double)Math.round(homeloanSimulation.getLoanInterestRate()));
    }

    @Test
    void should_return_186_when_calculateCostOverview () {
        HomeloanSimulation homeloanSimulation =
                new HomeloanSimulation(0, 200000.00, 0,
                        LOAN_INTEREST_RATE, INSURANCE_INTEREST_RATE, GUARANTEE_INTEREST_RATE, APPLICATION_FEE,
                        15, Periodicity.MONTHLY);
        double arrondi = (double)Math.round(homeloanSimulation.getGlobalEffectiveInterestRate() * 100)/100;
        Assertions.assertEquals(1.66,arrondi);
        homeloanSimulation.calculateAmortizationTable();
    }

}