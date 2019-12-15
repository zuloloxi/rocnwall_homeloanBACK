package fr.bnp.homeloancalculator.exposition;


import fr.bnp.homeloancalculator.application.MortgageProjectService;
import fr.bnp.homeloancalculator.domain.mortgage.MortgageProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class MortgageProjectResource {

    @Autowired
    MortgageProjectService mortgageProjectService;


    @RequestMapping(method = RequestMethod.POST, path = {"/mortgageproject"})
    @ResponseStatus(HttpStatus.CREATED)
    public void createMortgageProject(@Valid @RequestBody MortgageProjectDTO mortgageProjectDTO) {
        MortgageProject mortgageProject = MortgageProjectAdapter.transformToMortgageProject(mortgageProjectDTO);
        this.mortgageProjectService.initializeProject(mortgageProject);
    }

}
