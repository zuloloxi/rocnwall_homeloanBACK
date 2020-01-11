package fr.bnp.homeloancalculator.exposition;

import fr.bnp.homeloancalculator.domain.mortgage.HomeloanSimulation;
import fr.bnp.homeloancalculator.domain.mortgage.Periodicity;
import fr.bnp.homeloancalculator.domain.mortgage.ProjectType;

import java.util.List;
import java.util.stream.Collectors;

public class HomeloanSimulationAdapter {
    public static HomeloanSimulation transformToHomeloanSimulation(HomeloanSimulationUpdateDTO homeloanSimulationDTO) {
        // Convert some data values betwween front-end and back-end
        PeriodicityDTO periodicityDTO = Enum.valueOf(PeriodicityDTO.class, homeloanSimulationDTO.periodicity);
        Periodicity periodicity = convertPeriodicityDTOToDomainRange(periodicityDTO);
        return new HomeloanSimulation(
                homeloanSimulationDTO.personalDeposit,
                homeloanSimulationDTO.loanAmount,
                homeloanSimulationDTO.loanPayment,
                homeloanSimulationDTO.loanInterestRate,
                homeloanSimulationDTO.loanInsuranceRate,
                homeloanSimulationDTO.loanGuarantyRate,
                homeloanSimulationDTO.applicationFee,
                homeloanSimulationDTO.loanDuration,
                periodicity
        );
    }

    public static HomeloanSimulationQueryDTO adaptToHomeloanSimulationDTO(HomeloanSimulation homeloanSimulation) {
        // Convert some data values betwween front-end and back-end
        PeriodicityDTO periodicityDTO = convertFromDomainRangeToPeriodicityDTO(homeloanSimulation.getPeriodicity());
        return new HomeloanSimulationQueryDTO(
                homeloanSimulation.getId().toString(),
                homeloanSimulation.getSimulationDate(),
                homeloanSimulation.getPersonalDeposit(),
                homeloanSimulation.getLoanAmount(),
                homeloanSimulation.getLoanPayment(),
                homeloanSimulation.getLoanInterestRate(),
                homeloanSimulation.getLoanInsuranceRate(),
                homeloanSimulation.getLoanGuarantyRate(),
                homeloanSimulation.getLoanDuration(),
                periodicityDTO.toString(),
                homeloanSimulation.getGlobalLoanPayment(),
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
        return homeloanSimulations.stream().map(HomeloanSimulationAdapter::adaptToHomeloanSimulationDTO).collect(Collectors.toList());
    }

    public static Periodicity convertPeriodicityDTOToDomainRange(PeriodicityDTO periodicityDTO) {
        switch(periodicityDTO) {
            case Mensuelle: return Periodicity.MONTHLY;
            case Trimestrielle: return Periodicity.QUARTERLY;
            case Semestrielle: return Periodicity.BIANNUALLY;
            case Annuelle: return Periodicity.ANNUALLY;
        }
        throw new AssertionError("Opération inconnue : " + periodicityDTO);
    }

    public static PeriodicityDTO convertFromDomainRangeToPeriodicityDTO(Periodicity periodicity) {
        switch(periodicity) {
            case MONTHLY: return PeriodicityDTO.Mensuelle;
            case QUARTERLY: return PeriodicityDTO.Trimestrielle;
            case BIANNUALLY: return PeriodicityDTO.Semestrielle;
            case ANNUALLY: return PeriodicityDTO.Annuelle;
        }
        throw new AssertionError("Opération inconnue : " + periodicity);
    }
}

