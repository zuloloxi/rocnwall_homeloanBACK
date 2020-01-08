package fr.bnp.homeloancalculator.domain.mortgage;

import java.util.Date;

public class Borrower {

    private Long id;
    private double netIncome;
    private Date dateOfBirth;

    public Borrower() {}

    public Borrower(Long id, double netIncome, Date dateOfBirth) {
        this.id = id;
        this.netIncome = netIncome;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public double getNetIncome() {
        return netIncome;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

}
