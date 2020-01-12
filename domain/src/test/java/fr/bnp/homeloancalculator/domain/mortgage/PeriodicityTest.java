package fr.bnp.homeloancalculator.domain.mortgage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PeriodicityTest {

    @Test
    void given_Monthly_Then_Return_1 () {
        Periodicity periodicity = Periodicity.MONTHLY;
        Assertions.assertEquals(1,periodicity.numberOfMonths());
    }

    @Test
    void given_Quarterly_Then_Return_3 () {
        Periodicity periodicity = Periodicity.QUARTERLY;
        Assertions.assertEquals(3,periodicity.numberOfMonths());
    }

    @Test
    void given_Biannually_Then_Return_6 () {
        Periodicity periodicity = Periodicity.BIANNUALLY;
        Assertions.assertEquals(6,periodicity.numberOfMonths());
    }

    @Test
    void given_Annually_Then_Return_12 () {
        Periodicity periodicity = Periodicity.ANNUALLY;
        Assertions.assertEquals(12,periodicity.numberOfMonths());
    }

}