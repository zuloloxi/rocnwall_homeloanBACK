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
    double householdCharges;
    @JsonProperty
    double maxLoanPayment;
    @JsonProperty
    List<BorrowerDTO> borrowerDTOList;

    public MortgageProjectQueryDTO() {
    }

    public MortgageProjectQueryDTO(String id, String referenceId,
                                   double householdCharges,
                                   List<BorrowerDTO> borrowerDTOList,
                                   double maxLoanPayment) {
        this.id = id;
        this.referenceId = referenceId;
        this.householdCharges = householdCharges;
        this.borrowerDTOList = borrowerDTOList;
        this.maxLoanPayment = maxLoanPayment;
    }
}
