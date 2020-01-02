package fr.bnp.homeloancalculator.application;

import fr.bnp.homeloancalculator.domain.mortgage.HomeloanSimulation;
import fr.bnp.homeloancalculator.domain.mortgage.MortgageProject;
import fr.bnp.homeloancalculator.domain.mortgage.MortgageProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MortgageProjectService {

    @Autowired
    private MortgageProjectRepository mortgageProjectRepository;

    public MortgageProject create(MortgageProject mortgageProject) {
        mortgageProjectRepository.save(mortgageProject);
        return mortgageProject;
    }

    public MortgageProject obtain(UUID mortgageProjectId) {
        return mortgageProjectRepository.get(mortgageProjectId);
    }

    public List<MortgageProject> listAll() {
        return mortgageProjectRepository.findAll();
    }

    public MortgageProject update(UUID mortgageProjectId, MortgageProject mortgageProjectWithNewInformations) {
        MortgageProject mortgageProject= obtain(mortgageProjectId);
        mortgageProject = mortgageProject.update(mortgageProjectWithNewInformations);
        mortgageProjectRepository.save(mortgageProject);
        return mortgageProject;
    }

    public void remove(UUID mortgageProjectId) {
        obtain(mortgageProjectId);  // throw an exception if the project doesn't exist
        this.mortgageProjectRepository.delete(mortgageProjectId);
    }

    public HomeloanSimulation addHomeloanSimulation(UUID mortgageProjectId, HomeloanSimulation homeloanSimulation) {
        MortgageProject mortgageProject = obtain(mortgageProjectId);
        mortgageProject.addHomeloanSimulation(homeloanSimulation);
        this.mortgageProjectRepository.save(mortgageProject);
        return homeloanSimulation;
    }

    public HomeloanSimulation updateHomeloanSimulation(UUID mortgageProjectId, UUID homeloanSimulationId, HomeloanSimulation homeloanSimulation) {
        MortgageProject mortgageProject = obtain(mortgageProjectId);
        mortgageProject.updateHomeloanSimulation(homeloanSimulationId, homeloanSimulation);
        this.mortgageProjectRepository.save(mortgageProject);
        return homeloanSimulation;
    }

    public HomeloanSimulation obtainHomeloanSimulation(UUID mortgageProjectId, UUID homeloanSimulationId) {
        MortgageProject mortgageProject = obtain(mortgageProjectId);
        return mortgageProject.searchHomeloanSimulation(homeloanSimulationId);
    }

    public List<HomeloanSimulation> listAllHomeloanSimulations(UUID mortgageProjectId) {
        MortgageProject mortgageProject = obtain(mortgageProjectId);
        return mortgageProject.getHomeloanSimulations();
    }

    public void removeHomeloanSimulation(UUID mortgageProjectId, UUID homeloanSimulationId) {
        MortgageProject mortgageProject = obtain(mortgageProjectId);
        mortgageProject.removeHomeloanSimulation(homeloanSimulationId);
        this.mortgageProjectRepository.save(mortgageProject);
    }
}
