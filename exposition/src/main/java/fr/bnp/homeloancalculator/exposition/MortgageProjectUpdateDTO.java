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
    List<BorrowerDTO> borrowerDTOList;

    public MortgageProjectUpdateDTO() {
    }

    public MortgageProjectUpdateDTO(String referenceId,
                                    String projectType,
                                    double householdCharges,
                                    List<BorrowerDTO> borrowerDTOList,
                                    double maxLoanPayment) {
        this.referenceId = referenceId;
        this.projectType = projectType;
        this.householdCharges = householdCharges;
        this.borrowerDTOList = borrowerDTOList;
        this.maxLoanPayment = maxLoanPayment;
    }
}
