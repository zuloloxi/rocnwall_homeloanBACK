package fr.bnp.homeloancalculator.exposition;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MortgageProjectUpdateDTO {
    @JsonProperty
    String referenceId;
    @JsonProperty
    String projectType;
    @JsonProperty
    double householdCharges;
    @JsonProperty
    double maxLoanPayment;
    @JsonProperty
    List<BorrowerDTO> borrowers;

    public MortgageProjectUpdateDTO() {
    }

    public MortgageProjectUpdateDTO(String referenceId,
                                    String projectType,
                                    double householdCharges,
                                    List<BorrowerDTO> borrowers,
                                    double maxLoanPayment) {
        this.referenceId = referenceId;
        this.projectType = projectType;
        this.householdCharges = householdCharges;
        this.borrowers = borrowers;
        this.maxLoanPayment = maxLoanPayment;
    }
}
