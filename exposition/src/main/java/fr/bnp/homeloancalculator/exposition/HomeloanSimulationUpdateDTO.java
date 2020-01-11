package fr.bnp.homeloancalculator.exposition;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.bnp.homeloancalculator.domain.mortgage.Periodicity;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class HomeloanSimulationUpdateDTO {
    @JsonProperty
    double personalDeposit;
    @JsonProperty
    double loanAmount;
    @JsonProperty
    double loanPayment;
    @JsonProperty
    double loanInterestRate;
    @JsonProperty
    double loanInsuranceRate;
    @JsonProperty
    double loanGuarantyRate;
    @JsonProperty
    double applicationFee;
    @JsonProperty
    int loanDuration;
    @JsonProperty
    String periodicity;


    public HomeloanSimulationUpdateDTO() {
    }

    public HomeloanSimulationUpdateDTO(
            double personalDeposit,
            double loanAmount,
            double loanPayment,
            double loanInterestRate,
            double loanInsuranceRate,
            double loanGuarantyRate,
            double applicationFee,
            int loanDuration,
            String periodicity) {
        this.personalDeposit = personalDeposit;
        this.loanAmount = loanAmount;
        this.loanPayment = loanPayment;
        this.loanInterestRate = loanInterestRate;
        this.loanInsuranceRate = loanInsuranceRate;
        this.loanGuarantyRate = loanGuarantyRate;
        this.applicationFee = applicationFee;
        this.loanDuration = loanDuration;
        this.periodicity = periodicity;
    }
}
