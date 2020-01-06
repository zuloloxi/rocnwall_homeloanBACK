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
@CrossOrigin
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
    public MortgageProjectQueryDTO detailMortgageProject(@PathVariable("mortgageProjectId") String projectId) {
        UUID mortgageProjectId = UUID.fromString(projectId);
        MortgageProject mortgageProject = this.mortgageProjectService.obtain(mortgageProjectId);
        return MortgageProjectAdapter.adaptToMortgageProjectDTO(mortgageProject);
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/mortgageProjects"})
    public List<MortgageProjectQueryDTO> listAllMortgageProjects() {
        List<MortgageProject> mortgageProjects = this.mortgageProjectService.listAll();
        return MortgageProjectAdapter.adaptToMortgageProjectDTOList(mortgageProjects);
    }

    @RequestMapping(method = RequestMethod.PUT, path = {"/mortgageProjects/{mortgageProjectId}"})
    public  MortgageProjectQueryDTO updateMortgageProject(@PathVariable("mortgageProjectId") String projectId,
                                                           @RequestBody MortgageProjectUpdateDTO mortgageProjectUpdateDTO) {
        UUID mortgageProjectId = UUID.fromString(projectId);
        MortgageProject mortgageProject = MortgageProjectAdapter.transformToMortgageProject(mortgageProjectUpdateDTO);
        mortgageProject = this.mortgageProjectService.update(mortgageProjectId, mortgageProject);
        return MortgageProjectAdapter.adaptToMortgageProjectDTO(mortgageProject);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = {"/mortgageProjects/{mortgageProjectId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeMortgageProject(@PathVariable("mortgageProjectId") String projectId) {
        UUID mortgageProjectId = UUID.fromString(projectId);
        this.mortgageProjectService.remove(mortgageProjectId);
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/mortgageProjects/{mortgageProjectId}/homeloanSimulations"})
    @ResponseStatus(HttpStatus.CREATED)
    public HomeloanSimulationQueryDTO addHomeloanSimulationToMortgageProject(@PathVariable("mortgageProjectId") String projectId,
                                                                             @RequestBody HomeloanSimulationUpdateDTO homeloanSimulationUpdateDTO) {
        UUID mortgageProjectId = UUID.fromString(projectId);
        HomeloanSimulation homeloanSimulation = HomeloanSimulationAdapter.transformToHomeloanSimulation(homeloanSimulationUpdateDTO);
        homeloanSimulation = this.mortgageProjectService.addHomeloanSimulation(mortgageProjectId, homeloanSimulation);
        return HomeloanSimulationAdapter.adaptToHomeloanSimulationDTO(homeloanSimulation);
    }

    @RequestMapping(method = RequestMethod.PUT, path = {"/mortgageProjects/{mortgageProjectId}/homeloanSimulations/{homeloanSimulationId}"})
    public HomeloanSimulationQueryDTO updateHomeloanSimulator(@PathVariable("mortgageProjectId") String projectId,
                                                              @PathVariable("homeloanSimulationId") String simulationId,
                                                              @RequestBody HomeloanSimulationUpdateDTO homeloanSimulationUpdateDTO) {
        UUID mortgageProjectId = UUID.fromString(projectId);
        UUID homeloanSimulationId = UUID.fromString(simulationId);
        HomeloanSimulation homeloanSimulation = HomeloanSimulationAdapter.transformToHomeloanSimulation(homeloanSimulationUpdateDTO);
        homeloanSimulation = this.mortgageProjectService.updateHomeloanSimulation(mortgageProjectId, homeloanSimulationId, homeloanSimulation);
        return HomeloanSimulationAdapter.adaptToHomeloanSimulationDTO(homeloanSimulation);
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/mortgageProjects/{mortgageProjectId}/homeloanSimulations"})
    public List<HomeloanSimulationQueryDTO> listAllHomeloanSimulations(@PathVariable("mortgageProjectId") String projectId) {
        UUID mortgageProjectId = UUID.fromString(projectId);
        List<HomeloanSimulation> homeloanSimulations = this.mortgageProjectService.listAllHomeloanSimulations(mortgageProjectId);
        return HomeloanSimulationAdapter.adaptToHomeloanSimulationListDTO(homeloanSimulations);
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/mortgageProjects/{mortgageProjectId}/homeloanSimulations/{homeloanSimulationId}"})
    public HomeloanSimulationQueryDTO detailHomeloanSimulation(@PathVariable("mortgageProjectId") String projectId,
                                                              @PathVariable("homeloanSimulationId") String simulationId) {
        UUID mortgageProjectId = UUID.fromString(projectId);
        UUID homeloanSimulationId = UUID.fromString(simulationId);
        HomeloanSimulation homeloanSimulation = this.mortgageProjectService.obtainHomeloanSimulation(mortgageProjectId, homeloanSimulationId);
        return HomeloanSimulationAdapter.adaptToHomeloanSimulationDTO(homeloanSimulation);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = {"/mortgageProjects/{mortgageProjectId}/homeloanSimulations/{homeloanSimulationId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeHomeloanSimulation(@PathVariable("mortgageProjectId") String projectId,
                                                            @PathVariable("homeloanSimulationId") String simulationId) {
        UUID mortgageProjectId = UUID.fromString(projectId);
        UUID homeloanSimulationId = UUID.fromString(simulationId);
        this.mortgageProjectService.removeHomeloanSimulation(mortgageProjectId, homeloanSimulationId);
    }

}
