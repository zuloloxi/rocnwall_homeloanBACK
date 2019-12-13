package fr.bnp.homeloan.domain.mortgage;

import javax.money.MonetaryAmount;
import java.util.List;

public class MortgageProject {

    public static enum ProjectType {
        PURCHASE, CONSTRUCTION;
    }

    private MonetaryAmount householdCharges;
    private List<Borrower> borrowers;
    private List<HomeloanSimulator> homeloanSimulators;

}
