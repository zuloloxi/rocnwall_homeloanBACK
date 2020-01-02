package fr.bnp.homeloancalculator.exposition;

import fr.bnp.homeloancalculator.domain.mortgage.HomeloanSimulation;

import java.util.List;
import java.util.stream.Collectors;

public class HomeloanSimulationAdapter {
    public static HomeloanSimulation transformToHomeloanSimulation(HomeloanSimulationUpdateDTO homeloanSimulationDTO) {
        return new HomeloanSimulation(
                homeloanSimulationDTO.personalDeposit,
                homeloanSimulationDTO.loanAmount,
                homeloanSimulationDTO.loanPayment,
                homeloanSimulationDTO.loanInterestRate,
                homeloanSimulationDTO.loanInsuranceRate,
                homeloanSimulationDTO.loanGuarantyRate,
                homeloanSimulationDTO.applicationFee,
                homeloanSimulationDTO.loanDuration,
                homeloanSimulationDTO.periodicity
        );
    }

    public static HomeloanSimulationQueryDTO adaptToHomeloanSimulationDTO(HomeloanSimulation homeloanSimulation) {
        return new HomeloanSimulationQueryDTO(
                homeloanSimulation.getId(),
                homeloanSimulation.getSimulationDate(),
                homeloanSimulation.getPersonalDeposit(),
                homeloanSimulation.getLoanAmount(),
                homeloanSimulation.getLoanPayment(),
                homeloanSimulation.getLoanInterestRate(),
                homeloanSimulation.getLoanInsuranceRate(),
                homeloanSimulation.getLoanGuarantyRate(),
                homeloanSimulation.getLoanDuration(),
                homeloanSimulation.getPeriodicity(),
                homeloanSimulation.getLoanCost(),
                homeloanSimulation.getInterestCost(),
                homeloanSimulation.getInsuranceCost(),
                homeloanSimulation.getApplicationFee(),
                homeloanSimulation.getLoanGuaranty(),
                homeloanSimulation.getGlobalEffectiveInterestRate(),
                homeloanSimulation.getInsuranceImpactOnInterestRate(),
                homeloanSimulation.getFeesImpactOnInterestRate(),
                homeloanSimulation.isCreditRequest()
        );
    }

    public static List<HomeloanSimulationQueryDTO> adaptToHomeloanSimulationListDTO(List<HomeloanSimulation> homeloanSimulations) {
        return null;
    }

    public static List<HomeloanSimulationQueryDTO> adaptToHomeloanSimulatorListDTO(List<HomeloanSimulation> homeloanSimulations) {
        return homeloanSimulations.stream().map(HomeloanSimulationAdapter::adaptToHomeloanSimulationDTO).collect(Collectors.toList());
    }
}

