package fr.bnp.homeloancalculator.infrastructure;

import fr.bnp.homeloancalculator.domain.mortgage.Borrower;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "BORROWER")
public class BorrowerJPA {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "NET_INCOME")
    private double netIncome;

    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;

    protected BorrowerJPA() {}

    protected BorrowerJPA(Borrower borrower){
        this.id = borrower.getId();
        this.netIncome = borrower.getNetIncome();
        this.dateOfBirth = borrower.getDateOfBirth();
    }

    protected Borrower toBorrower() {
        return new Borrower(
                getId(),
                getNetIncome(),
                getDateOfBirth());
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
