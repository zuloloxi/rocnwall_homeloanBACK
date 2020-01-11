package fr.bnp.homeloancalculator.exposition;

import fr.bnp.homeloancalculator.domain.mortgage.MortgageProject;
import fr.bnp.homeloancalculator.domain.mortgage.ProjectType;

import java.util.List;
import java.util.stream.Collectors;

import static fr.bnp.homeloancalculator.exposition.BorrowerAdapter.adaptToBorrowerListDTO;
import static fr.bnp.homeloancalculator.exposition.BorrowerAdapter.transformToBorrowerList;


public class MortgageProjectAdapter {

    public static MortgageProject transformToMortgageProject(MortgageProjectUpdateDTO mortgageProjectUpdateDTO) {
        // Convert some data values betwween front-end and back-end
        ProjectTypeDTO projectTypeDTO = Enum.valueOf(ProjectTypeDTO.class, mortgageProjectUpdateDTO.projectType);
        ProjectType projectType = convertProjectTypeDTOToDomainRange(projectTypeDTO);
        return new MortgageProject(mortgageProjectUpdateDTO.referenceId,
                projectType,
                mortgageProjectUpdateDTO.householdCharges,
                transformToBorrowerList(mortgageProjectUpdateDTO.borrowers),
                mortgageProjectUpdateDTO.maxLoanPayment);
    }

    public static MortgageProjectQueryDTO adaptToMortgageProjectDTO(MortgageProject mortgageProject) {
        // Convert some data values betwween front-end and back-end
        ProjectTypeDTO projectTypeDTO = convertFromDomainRangeToProjectTypeDTO(mortgageProject.getProjectType());
        return new MortgageProjectQueryDTO(
                mortgageProject.getId().toString(),
                mortgageProject.getReferenceId(),
                projectTypeDTO.toString(),
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
            case Achat: return ProjectType.PURCHASE;
            case Construction: return ProjectType.CONSTRUCTION;
        }
        throw new AssertionError("Opération inconnue : " + projectTypeDTO);
    }

    public static ProjectTypeDTO convertFromDomainRangeToProjectTypeDTO(ProjectType projectType) {
        switch(projectType) {
            case PURCHASE: return ProjectTypeDTO.Achat;
            case CONSTRUCTION: return ProjectTypeDTO.Construction;
        }
        throw new AssertionError("Opération inconnue : " + projectType);
    }
}
