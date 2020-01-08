package fr.bnp.homeloancalculator.exposition;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.bnp.homeloancalculator.domain.mortgage.Periodicity;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class HomeloanSimulationQueryDTO {
    @JsonProperty
    String id;
    @JsonProperty
    Date simulationDate;
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
    int loanDuration;
    @JsonProperty
    Periodicity periodicity;
    @JsonProperty
    double globalLoanPayment;
    @JsonProperty
    double loanCost;
    @JsonProperty
    double interestCost;
    @JsonProperty
    double insuranceCost;
    @JsonProperty
    double applicationFee;
    @JsonProperty
    double loanGuaranty;
    @JsonProperty
    double effectiveInterestRate;
    @JsonProperty
    double insuranceImpactOnInterestRate;
    @JsonProperty
    double feesImpactOnInterestRate;
    @JsonProperty
    boolean creditRequest;

    public HomeloanSimulationQueryDTO() {
    }

    public HomeloanSimulationQueryDTO(
            String id,
            Date simulationDate,
            double personalDeposit,
            double loanAmount,
            double loanPayment,
            double loanInterestRate,
            double loanInsuranceRate,
            double loanGuarantyRate,
            int loanDuration,
            Periodicity periodicity,
            double globalLoanPayment,
            double loanCost,
            double interestCost,
            double insuranceCost,
            double applicationFee,
            double loanGuaranty,
            double effectiveInterestRate,
            double insuranceImpactOnInterestRate,
            double feesImpactOnInterestRate,
            boolean creditRequest) {
        this.id = id;
        this.simulationDate = simulationDate;
        this.personalDeposit = personalDeposit;
        this.loanAmount = loanAmount;
        this.loanPayment = loanPayment;
        this.loanInterestRate = loanInterestRate;
        this.loanInsuranceRate = loanInsuranceRate;
        this.loanGuarantyRate = loanGuarantyRate;
        this.loanDuration = loanDuration;
        this.periodicity = periodicity;
        this.globalLoanPayment = globalLoanPayment;
        this.loanCost = loanCost;
        this.interestCost = interestCost;
        this.insuranceCost = insuranceCost;
        this.applicationFee = applicationFee;
        this.loanGuaranty = loanGuaranty;
        this.effectiveInterestRate = effectiveInterestRate;
        this.insuranceImpactOnInterestRate = insuranceImpactOnInterestRate;
        this.feesImpactOnInterestRate = feesImpactOnInterestRate;
        this.creditRequest = creditRequest;
    }

}
