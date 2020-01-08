package fr.bnp.homeloancalculator.exposition;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class BorrowerDTO {
    @JsonProperty
    double netIncome;
    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Date dateOfBirth;

    public BorrowerDTO(double netIncome, Date dateOfBirth) {
        this.netIncome = netIncome;
        this.dateOfBirth = dateOfBirth;
    }
}
