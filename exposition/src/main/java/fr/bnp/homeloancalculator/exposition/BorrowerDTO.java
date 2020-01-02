package fr.bnp.homeloancalculator.exposition;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class BorrowerDTO {
    @JsonProperty
    double netIncome;
    @JsonProperty
    Date dateOfBirth;

    public BorrowerDTO(double netIncome, Date dateOfBirth) {
        this.netIncome = netIncome;
        this.dateOfBirth = dateOfBirth;
    }
}
