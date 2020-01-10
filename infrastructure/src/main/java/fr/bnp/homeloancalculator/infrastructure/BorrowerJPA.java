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

    public void setId(Long id) {
        this.id = id;
    }

    public void setNetIncome(double netIncome) {
        this.netIncome = netIncome;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BorrowerJPA that = (BorrowerJPA) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
