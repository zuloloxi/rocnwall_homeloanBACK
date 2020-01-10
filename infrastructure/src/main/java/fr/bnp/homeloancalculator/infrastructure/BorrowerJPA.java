package fr.bnp.homeloancalculator.infrastructure;

import fr.bnp.homeloancalculator.domain.mortgage.Borrower;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

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

    private BorrowerJPA() {}

    public BorrowerJPA(Borrower borrower){
        this.id = borrower.getId();
        this.netIncome = borrower.getNetIncome();
        this.dateOfBirth = borrower.getDateOfBirth();
    }

    public Borrower toBorrower() {
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
