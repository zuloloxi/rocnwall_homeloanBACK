package fr.bnp.homeloancalculator.domain.mortgage;

import fr.bnp.homeloancalculator.domain.mortgage.HomeloanSimulation;

public interface Calculator {
    void calculateCost();
    double getLoanAmount();
    double getLoanPayment();
    double getInsuranceCost();
    double getLoanGuaranty();
    double getGlobalLoanPayment();
    double getApplicationFee();
    double getInterestCost();
    double getLoanCost();
    double getInsuranceImpactOnInterestRate();
    double getGlobalEffectiveInterestRate();
    double getFeesImpactOnInterestRate();
}
