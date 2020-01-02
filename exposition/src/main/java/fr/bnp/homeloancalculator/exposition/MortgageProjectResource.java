package fr.bnp.homeloancalculator.exposition;


import fr.bnp.homeloancalculator.application.MortgageProjectService;
import fr.bnp.homeloancalculator.domain.mortgage.HomeloanSimulation;
import fr.bnp.homeloancalculator.domain.mortgage.MortgageProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
public class MortgageProjectResource {

    @Autowired
    MortgageProjectService mortgageProjectService;

    @RequestMapping(method = RequestMethod.POST, path = {"/mortgageProjects"})
    @ResponseStatus(HttpStatus.CREATED)
    public MortgageProjectQueryDTO createMortgageProject(@Valid @RequestBody MortgageProjectUpdateDTO mortgageProjectUpdateDTO) {
        MortgageProject mortgageProject = MortgageProjectAdapter.transformToMortgageProject(mortgageProjectUpdateDTO);
        mortgageProject = this.mortgageProjectService.create(mortgageProject);
        return MortgageProjectAdapter.adaptToMortgageProjectDTO(mortgageProject);
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/mortgageProjects/{mortgageProjectId}"})
    public MortgageProjectQueryDTO detailMortgageProject(@PathVariable("mortgageProjectId") UUID mortgageProjectId) {
        MortgageProject mortgageProject = this.mortgageProjectService.obtain(mortgageProjectId);
        return MortgageProjectAdapter.adaptToMortgageProjectDTO(mortgageProject);
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/mortgageProjects"})
    public List<MortgageProjectQueryDTO> listAllMortgageProjects() {
        List<MortgageProject> mortgageProjects = this.mortgageProjectService.listAll();
        return MortgageProjectAdapter.adaptToMortgageProjectDTOList(mortgageProjects);
    }

    @RequestMapping(method = RequestMethod.PUT, path = {"/mortgageProjects/{mortgageProjectId}"})
    public  MortgageProjectQueryDTO updateMortgageProject(@PathVariable("mortgageProjectId") UUID mortgageProjectId,
                                                           @RequestBody MortgageProjectUpdateDTO mortgageProjectDTO) {
        MortgageProject mortgageProject = MortgageProjectAdapter.transformToMortgageProject(mortgageProjectDTO);
        mortgageProject = this.mortgageProjectService.update(mortgageProjectId, mortgageProject);
        return MortgageProjectAdapter.adaptToMortgageProjectDTO(mortgageProject);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = {"/mortgageProjects/{mortgageProjectId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeMortgageProject(@PathVariable("mortgageProjectId") UUID mortgageProjectId) {
        this.mortgageProjectService.remove(mortgageProjectId);
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/mortgageProjects/{mortgageProjectId}/homeloanSimulations"})
    @ResponseStatus(HttpStatus.CREATED)
    public HomeloanSimulationQueryDTO addHomeloanSimulationToMortgageProject(@PathVariable("mortgageProjectId") UUID mortgageProjectId,
                                                                             @RequestBody HomeloanSimulationUpdateDTO homeloanSimulationUpdateDTO) {
        HomeloanSimulation homeloanSimulation = HomeloanSimulationAdapter.transformToHomeloanSimulation(homeloanSimulationUpdateDTO);
        homeloanSimulation = this.mortgageProjectService.addHomeloanSimulation(mortgageProjectId, homeloanSimulation);
        return HomeloanSimulationAdapter.adaptToHomeloanSimulationDTO(homeloanSimulation);

    }

    @RequestMapping(method = RequestMethod.PUT, path = {"/mortgageProjects/{mortgageProjectId}/homeloanSimulations/{homeloanSimulationId}"})
    public HomeloanSimulationQueryDTO updateHomeloanSimulator(@PathVariable("mortgageProjectId") UUID mortgageProjectId,
                                                              @PathVariable("homeloanSimulationId") UUID homeloanSimulationId,
                                                              @RequestBody HomeloanSimulationUpdateDTO homeloanSimulationUpdateDTO) {
        HomeloanSimulation homeloanSimulation = HomeloanSimulationAdapter.transformToHomeloanSimulation(homeloanSimulationUpdateDTO);
        homeloanSimulation = this.mortgageProjectService.updateHomeloanSimulation(mortgageProjectId, homeloanSimulationId, homeloanSimulation);
        return HomeloanSimulationAdapter.adaptToHomeloanSimulationDTO(homeloanSimulation);
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/mortgageProjects/{mortgageProjectId}/homeloanSimulations"})
    public List<HomeloanSimulationQueryDTO> listAllHomeloanSimulationsFromMortgageProject(@PathVariable("mortgageProjectId") UUID mortgageProjectId) {
        List<HomeloanSimulation> homeloanSimulations = this.mortgageProjectService.listAllHomeloanSimulations(mortgageProjectId);
        return HomeloanSimulationAdapter.adaptToHomeloanSimulationListDTO(homeloanSimulations);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = {"/mortgageProjects/{mortgageProjectId}/homeloanSimulations/{homeloanSimulationId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeHomeloanSimulationFromMortgageProject(@PathVariable("mortgageProjectId") UUID mortgageProjectId,
                                                            @PathVariable("homeloanSimulationId") UUID homeloanSimulationId) {
        this.mortgageProjectService.removeHomeloanSimulation(mortgageProjectId, homeloanSimulationId);
    }

}
