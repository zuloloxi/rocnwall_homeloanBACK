package fr.bnp.homeloancalculator.exposition;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class MortgageProjectQueryDTO {
    @JsonProperty
    UUID id;
    @JsonProperty
    String referenceId;
    @JsonProperty
    double householdCharges;
    @JsonProperty
    double maxLoanPayment;
    @JsonProperty
    List<BorrowerDTO> borrowerDTOList;
    @JsonProperty
    List<HomeloanSimulationQueryDTO> homeloanSimulationDTOList;

    public MortgageProjectQueryDTO() {
    }

    public MortgageProjectQueryDTO(UUID id, String referenceId,
                                   double householdCharges,
                                   List<BorrowerDTO> borrowerDTOList,
                                   double maxLoanPayment,
                                   List<HomeloanSimulationQueryDTO> homeloanSimulationDTOList) {
        this.id = id;
        this.referenceId = referenceId;
        this.householdCharges = householdCharges;
        this.borrowerDTOList = borrowerDTOList;
        this.maxLoanPayment = maxLoanPayment;
        this.homeloanSimulationDTOList = homeloanSimulationDTOList;
    }
}
