package fr.bnp.homeloancalculator.exposition;

import fr.bnp.homeloancalculator.domain.mortgage.MortgageProject;

import java.util.List;
import java.util.stream.Collectors;

import static fr.bnp.homeloancalculator.exposition.BorrowerAdapter.adaptToBorrowerListDTO;
import static fr.bnp.homeloancalculator.exposition.BorrowerAdapter.transformToBorrowerList;
import static fr.bnp.homeloancalculator.exposition.HomeloanSimulationAdapter.adaptToHomeloanSimulationListDTO;


public class MortgageProjectAdapter {

    public static MortgageProject transformToMortgageProject(MortgageProjectUpdateDTO mortgageProjectUpdateDTO) {
        return new MortgageProject(mortgageProjectUpdateDTO.referenceId,
                mortgageProjectUpdateDTO.householdCharges,
                transformToBorrowerList(mortgageProjectUpdateDTO.borrowerDTOList),
                mortgageProjectUpdateDTO.maxLoanPayment);
    }

    public static MortgageProjectQueryDTO adaptToMortgageProjectDTO(MortgageProject mortgageProject) {
        return new MortgageProjectQueryDTO(
                mortgageProject.getId().toString(),
                mortgageProject.getReferenceId(),
                mortgageProject.getHouseholdCharges(),
                adaptToBorrowerListDTO(mortgageProject.getBorrowers()),
                mortgageProject.getMaxLoanPayment()
        );
    }

    public static List<MortgageProjectQueryDTO> adaptToMortgageProjectDTOList(List<MortgageProject> mortgageProjects) {
        return mortgageProjects.stream().map(MortgageProjectAdapter::adaptToMortgageProjectDTO).collect(Collectors.toList());
    }


}
