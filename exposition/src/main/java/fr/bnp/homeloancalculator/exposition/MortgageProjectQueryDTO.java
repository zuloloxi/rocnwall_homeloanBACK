package fr.bnp.homeloancalculator.exposition;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class MortgageProjectQueryDTO {
    @JsonProperty (access = JsonProperty.Access.READ_ONLY)
    String id;
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

    public MortgageProjectQueryDTO() {
    }

    public MortgageProjectQueryDTO(String id, String referenceId,
                                   String projectType,
                                   double householdCharges,
                                   List<BorrowerDTO> borrowerDTOList,
                                   double maxLoanPayment) {
        this.id = id;
        this.referenceId = referenceId;
        this.projectType = projectType;
        this.householdCharges = householdCharges;
        this.borrowers = borrowerDTOList;
        this.maxLoanPayment = maxLoanPayment;
    }
}
