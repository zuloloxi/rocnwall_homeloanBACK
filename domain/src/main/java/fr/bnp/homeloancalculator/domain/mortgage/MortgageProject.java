package fr.bnp.homeloancalculator.domain.mortgage;

import javax.money.MonetaryAmount;
import java.util.List;

public class MortgageProject {


    enum ProjectType {
        PURCHASE, CONSTRUCTION;
    }

    private double householdCharges;
    private List<Borrower> borrowers;
    private List<HomeloanSimulation> homeloanSimulations;

}
