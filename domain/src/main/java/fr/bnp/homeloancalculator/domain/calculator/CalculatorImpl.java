package fr.bnp.homeloancalculator.domain.calculator;

import fr.bnp.homeloancalculator.domain.mortgage.Calculator;
import fr.bnp.homeloancalculator.domain.mortgage.Periodicity;
import org.springframework.stereotype.Component;

public class CalculatorImpl implements Calculator {
    @Override
    public Double calculateLoanAmount(Periodicity periodicity, double interestRate, double loanPayment, int loanDuration) {

        // Convert interest rate into a decimal; eg. 0,85% = 0.0085
        interestRate /= 100.0;

        // Periodic interest rate
         double periodicRate =
                 interestRate * periodicity.numberOfMonths() / 12.0;

        // length of the term in periods
         int durationInPeriods =
                 (loanDuration * 12) / periodicity.numberOfMonths();

        // Calculate the loan amount
        Double loanAmount;
        loanAmount =
                (loanPayment / periodicRate)
                        * ((1 - Math.pow(1 + periodicRate, -durationInPeriods)))
                ;

        return loanAmount;

    }

    @Override
    public Double calculateLoanPayment(Periodicity periodicity, double interestRate, double loanAmount, int loanDuration) {

        // Convert interest rate into a decimal; eg. 0,85% = 0.0085
        interestRate /= 100.0;

        // Periodic interest rate
        double periodicRate =
                interestRate * periodicity.numberOfMonths() / 12.0;

        // length of the term in periods
        int durationInPeriods =
                (loanDuration * 12) / periodicity.numberOfMonths();

        // Calculate the periodic payment
        Double periodicPayment = loanAmount * periodicRate
                / (1 - Math.pow(1 + periodicRate, -durationInPeriods));

        return periodicPayment;
    }

    @Override
    public Double calculateCostOverview (Periodicity periodicity, double loanAmount, double loanPayment, int loanDuration) {

        // length of the term in periods
        int durationInPeriods =
                (loanDuration * 12) / periodicity.numberOfMonths();

        // Total insurance fees = 3,300% of loan amount
        Double periodicInsuranceFee =
                loanAmount * 0.033 / durationInPeriods;

        // Initial guarantee fee = 1,305% of loan amount
        Double guaranteeFee =
                loanAmount * 0.01305 * periodicity.numberOfMonths();

        // Application fee = 1000 €
        Double applicationFee = 1000.00;

        // Interest cost
        Double interestCost = (loanPayment*durationInPeriods) - loanAmount;

        // Loan cost
        Double loanCost =
                interestCost + (periodicInsuranceFee * durationInPeriods)
                    + applicationFee + guaranteeFee;

        // --> Calculation of the annual effective interest rates (EIR)
        double presentValue = loanAmount;
        double[] cashFlow = new double[durationInPeriods];
        for(int i=0; i < cashFlow.length; i++){
            cashFlow[i]= loanPayment;
        };

        EIRCalculator eirCalculator = new EIRCalculator();

        // Nominal EIR
        double interestRate =
                eirCalculator.calculateEIR(presentValue, cashFlow)
                        *1200/periodicity.numberOfMonths();
        System.out.printf("Nominal EIR = %s\n", interestRate);

        // EIR including insurance fees
        for(int i=0; i < cashFlow.length; i++){
            cashFlow[i] += periodicInsuranceFee;
        };
        double eirWithInsurance =
                eirCalculator.calculateEIR(presentValue, cashFlow)
                        *1200/periodicity.numberOfMonths();
        System.out.printf("EIR with insurance = %s\n", eirWithInsurance);
        double insuranceImpactOnInterestRate = eirWithInsurance - interestRate;

        // EIR including insurance, guarantee & application fees
        presentValue -= guaranteeFee;
        cashFlow[0] += applicationFee;
        double effectiveInterestRate =
                eirCalculator.calculateEIR(presentValue, cashFlow)
                        *1200/periodicity.numberOfMonths();
        System.out.printf("EIR with insurance & fees = %s\n", effectiveInterestRate);

        // A faire : renvoyer une instance de la classe "CostOverview" (à créer)
        // => variable d'instance de la classe "HomeloanSimulator"
        return effectiveInterestRate;
    }

}

