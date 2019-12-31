package fr.bnp.homeloancalculator.domain.calculator;

import fr.bnp.homeloancalculator.domain.mortgage.HomeloanSimulation;

public interface Calculator {
    void calculateCost();
    double getLoanAmount();
    double getLoanPayment();
    double getInsuranceFee();
    double getLoanGuarantee();
    double getApplicationFee();
    double getInterestCost();
    double getLoanCost();
    double getInsuranceImpactOnInterestRate();
    double getGlobalEffectiveInterestRate();
}
