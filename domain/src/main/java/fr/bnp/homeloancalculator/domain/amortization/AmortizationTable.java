package fr.bnp.homeloancalculator.domain.amortization;

import fr.bnp.homeloancalculator.domain.mortgage.Periodicity;

public class AmortizationTable {
    // Create the amortization table of the loan simulation .

    private AmortizationTableRow[] amortizationTable;

    public AmortizationTable(
            double loanAmount, double loanPayment,
            double loanInterestRate, int loanDuration, int periodDurationInMonths
    )
    {
        amortizationTable =
                new AmortizationTableRow [
                        loanDuration * 12 / periodDurationInMonths];
        amortizationTable[0] =
                AmortizationTableRow.firstAmortizationTableRow
                        (loanAmount, loanPayment, loanInterestRate, periodDurationInMonths);


        for (int i = 1; i < amortizationTable.length; i++)
        {
            amortizationTable[i]=
                    amortizationTable[i-1].nextAmortizationTableRow
                            (loanInterestRate, loanDuration, periodDurationInMonths);
        }
    }

    // Return the term of amortization
    public int getNumberOfAmortizationTableRows()
    { return amortizationTable.length; }

    // Return the i-th row of the amortization table du tableau d'amortissement.
    public AmortizationTableRow getAmortizationTableRow(int i)
    { return amortizationTable[i]; }

    // Return a representation of the table as a string
    public String toString() {
        String outputString = "";
        outputString+= ("Rang période | Capital initial | Intérêt | Amortissement   | Capital final"+"\n");
        for (int i = 0; i < amortizationTable.length; i++) {
            outputString+= amortizationTable[i].toString()+"\n";
        }
        return outputString;
    }
}
