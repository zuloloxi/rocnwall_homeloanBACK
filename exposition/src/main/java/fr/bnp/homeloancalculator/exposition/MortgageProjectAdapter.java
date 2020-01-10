package fr.bnp.homeloancalculator.exposition;

import fr.bnp.homeloancalculator.domain.mortgage.MortgageProject;
import fr.bnp.homeloancalculator.domain.mortgage.ProjectType;

import java.util.List;
import java.util.stream.Collectors;

import static fr.bnp.homeloancalculator.exposition.BorrowerAdapter.adaptToBorrowerListDTO;
import static fr.bnp.homeloancalculator.exposition.BorrowerAdapter.transformToBorrowerList;


public class MortgageProjectAdapter {

    public static MortgageProject transformToMortgageProject(MortgageProjectUpdateDTO mortgageProjectUpdateDTO) {
        ProjectType projectType;

        // Convert values to map to domain range of values (enum)


        return new MortgageProject(mortgageProjectUpdateDTO.referenceId,
                Enum.valueOf(ProjectType.class, mortgageProjectUpdateDTO.projectType),
                mortgageProjectUpdateDTO.householdCharges,
                transformToBorrowerList(mortgageProjectUpdateDTO.borrowers),
                mortgageProjectUpdateDTO.maxLoanPayment);
    }

    public static MortgageProjectQueryDTO adaptToMortgageProjectDTO(MortgageProject mortgageProject) {
        return new MortgageProjectQueryDTO(
                mortgageProject.getId().toString(),
                mortgageProject.getReferenceId(),
                mortgageProject.getProjectType().toString(),
                mortgageProject.getHouseholdCharges(),
                adaptToBorrowerListDTO(mortgageProject.getBorrowers()),
                mortgageProject.getMaxLoanPayment()
        );
    }

    public static List<MortgageProjectQueryDTO> adaptToMortgageProjectDTOList(List<MortgageProject> mortgageProjects) {
        return mortgageProjects.stream().map(MortgageProjectAdapter::adaptToMortgageProjectDTO).collect(Collectors.toList());
    }

    public static ProjectType convertProjectTypeDTOToDomainRange(ProjectTypeDTO projectTypeDTO) {
        switch(projectTypeDTO) {
            case ACHAT: return ProjectType.PURCHASE;
            case CONSTRUCTION: return ProjectType.CONSTRUCTION;
        }
        throw new AssertionError("Opération inconnue : " + projectTypeDTO);
    }

    public static ProjectTypeDTO convertFromDomainRangeToProjectTypeDTO(ProjectType projectType) {
        switch(projectType) {
            case PURCHASE: return ProjectTypeDTO.ACHAT;
            case CONSTRUCTION: return ProjectTypeDTO.CONSTRUCTION;
        }
        throw new AssertionError("Opération inconnue : " + projectType);
    }
}
