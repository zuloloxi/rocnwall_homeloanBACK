package fr.bnp.homeloancalculator.domain.amortization;

import fr.bnp.homeloancalculator.domain.mortgage.HomeloanSimulation;

public class AmortizationTableRow {

    private int year; // Index of the year related to the row of AmortizationTable (begins with 1)
    private double initialCapital; // amount of the outstanding capital at the beginning of the period
    private double interest; // Interest of the period
    private double depreciation; // Depreciation (=amortization) of the period
    private double payment;
    private double finalCapital; // amount of the outstanding capital at the end of the period

    AmortizationTableRow(int year, double initialCapital, double interest,
          double depreciation, double payment, double finalCapital)
    {
        this.year = year;
        this.initialCapital = initialCapital;
        this.interest = interest;
        this.depreciation = depreciation;
        this.payment = payment;
        this.finalCapital = finalCapital;
    }

    public int getYear() { return year+1; }

    public double getInitialCapital() { return initialCapital; }

    public double getInterest() { return interest; }

    public double getDepreciation() { return depreciation; }

    public double getPayment() { return payment; }

    public double getFinalCapital() { return finalCapital; }

    // Return the first row of the amortization tableRow.
    public static AmortizationTableRow firstAmortizationTableRow(HomeloanSimulation homeloanSimulation)
    {
        double interest = homeloanSimulation.getLoanAmount()
                *(homeloanSimulation.getLoanInterestRate()/
                    (100 * homeloanSimulation.getPeriodicity().numberOfMonths()));
        double depreciation = homeloanSimulation.getLoanPayment() - interest;
        double finalCapital = homeloanSimulation.getLoanAmount()- depreciation;
        return new AmortizationTableRow (0, homeloanSimulation.getLoanAmount(), interest, depreciation, homeloanSimulation.getLoanPayment(), finalCapital);
    }

    // Return the next row of the amortization tableRow (Null for the last one).
    public AmortizationTableRow nextAmortizationTableRow(HomeloanSimulation homeloanSimulation)
    {
        if(this.year == homeloanSimulation.getLoanDuration())
        { return null; }
        else
        {
            int yearNumber = this.year+1;
            double loanAmount = this.finalCapital;
            double interest =
                    loanAmount *
                            (homeloanSimulation.getLoanInterestRate()/
                                    (100 * homeloanSimulation.getPeriodicity().numberOfMonths()));
            double depreciation = this.payment - interest;
            double finalCapital = loanAmount- depreciation;

            return new AmortizationTableRow(yearNumber, loanAmount, interest, depreciation, this.payment, finalCapital);
        }
    }

    public String toString()
        {
        return (this.getYear() + "|" + this.getInitialCapital() + "|" + this.getDepreciation() + "|" + this.getInterest() + "|" + this.getPayment() + "|" + this.getFinalCapital());
        }
}
