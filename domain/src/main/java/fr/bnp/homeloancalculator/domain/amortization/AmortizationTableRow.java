package fr.bnp.homeloancalculator.domain.amortization;

import fr.bnp.homeloancalculator.domain.mortgage.Periodicity;

public class AmortizationTableRow {

    private int rowId; // Index related to the row of the amortization table (begins with 1)
    private double initialCapital; // amount of the outstanding capital at the beginning of the period
    private double interest; // Interest of the period
    private double depreciation; // Depreciation (=amortization) of the period
    private double payment;
    private double finalCapital; // amount of the outstanding capital at the end of the period

    AmortizationTableRow(int rowId, double initialCapital, double interest,
          double depreciation, double payment, double finalCapital)
    {
        this.rowId = rowId;
        this.initialCapital = initialCapital;
        this.interest = interest;
        this.depreciation = depreciation;
        this.payment = payment;
        this.finalCapital = finalCapital;
    }

    public int getRowId() { return rowId+1; }

    public double getInitialCapital() { return initialCapital; }

    public double getInterest() { return interest; }

    public double getDepreciation() { return depreciation; }

    public double getPayment() { return payment; }

    public double getFinalCapital() { return finalCapital; }

    // Return the first row of the amortization tableRow.
    public static AmortizationTableRow firstAmortizationTableRow(
            double loanAmount, double loanPayment,
            double loanInterestRate, Periodicity periodicity)
    {
        double interest = loanAmount
                *(loanInterestRate/
                    (100 * periodicity.numberOfMonths()));
        double depreciation = loanPayment - interest;
        double finalCapital = loanAmount - depreciation;
        return new AmortizationTableRow (0, loanAmount, interest, depreciation, loanPayment, finalCapital);
    }

    // Return the next row of the amortization tableRow (Null for the last one).
    public AmortizationTableRow nextAmortizationTableRow(
            double loanInterestRate, int loanDuration, Periodicity periodicity)
    {
        if (this.rowId == ((loanDuration * 12) / periodicity.numberOfMonths()))
        { return null; }
        else
        {
            int rowIdNumber = this.rowId + 1;
            double outstandingCapital = this.finalCapital;
            double interest =
                    outstandingCapital * loanInterestRate
                            * periodicity.numberOfMonths() / 1200;
            double depreciation = this.payment - interest;
            double finalCapital = outstandingCapital - depreciation;

            return new AmortizationTableRow(rowIdNumber, outstandingCapital, interest, depreciation, this.payment, finalCapital);
        }
    }

    public String toString()
        {
        return (this.getRowId() + "|" + this.getInitialCapital() + "|" + this.getDepreciation() + "|" + this.getInterest() + "|" + this.getFinalCapital());
        }
}
