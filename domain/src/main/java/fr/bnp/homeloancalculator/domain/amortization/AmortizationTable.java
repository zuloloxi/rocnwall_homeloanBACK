package fr.bnp.homeloancalculator.domain.amortization;

import fr.bnp.homeloancalculator.domain.mortgage.HomeloanSimulation;

public class AmortizationTable {
    // Create the amortization table of the loan simulation .

    private AmortizationTableRow[] amortizationTable;

    public AmortizationTable(HomeloanSimulation homeloanSimulation)
    {
        amortizationTable =
                new AmortizationTableRow [
                        homeloanSimulation.getLoanDuration()* 12
                        / (homeloanSimulation.getPeriodicity().numberOfMonths())];
        amortizationTable[0] = AmortizationTableRow.firstAmortizationTableRow(homeloanSimulation);
        for (int i = 1; i < amortizationTable.length; i++)
        {
            amortizationTable[i]= amortizationTable[i-1].nextAmortizationTableRow(homeloanSimulation);
        }
    }

    // Return the term of amortization
    public int getNumberOfAmortizationTableRows()
    { return amortizationTable.length; }

    // Return the ith row of the amortization table du tableau d'amortissement.
    public AmortizationTableRow getAmortizationTableRow(int i)
    { return amortizationTable[i]; }

    // Return a representation of the table as a string
    public String toString() {
        String message = "";
        for (int i = 0; i < amortizationTable.length; i++) {
            message+= amortizationTable[i].toString()+"\n";
        }
        return message;
    }
}
