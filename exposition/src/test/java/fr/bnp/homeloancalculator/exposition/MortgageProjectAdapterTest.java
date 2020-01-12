package fr.bnp.homeloancalculator.exposition;

import fr.bnp.homeloancalculator.domain.mortgage.ProjectType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MortgageProjectAdapterTest {
    @Test
    void given_ProjectTypeDTO_Then_Calculate_ProjectType () {
        ProjectTypeDTO projectTypeDTO = ProjectTypeDTO.Achat;
        ProjectType projectType = MortgageProjectAdapter.convertProjectTypeDTOToDomainRange(projectTypeDTO);
        Assertions.assertEquals(ProjectType.PURCHASE, projectType);
    }

    @Test
    void given_ProjectType_Then_Create_ProjectTypeDTO () {
        ProjectType projectType = ProjectType.PURCHASE;
        ProjectTypeDTO projectTypeDTO = MortgageProjectAdapter.convertFromDomainRangeToProjectTypeDTO(projectType);
        Assertions.assertEquals(ProjectTypeDTO.Achat, projectTypeDTO);
    }
}