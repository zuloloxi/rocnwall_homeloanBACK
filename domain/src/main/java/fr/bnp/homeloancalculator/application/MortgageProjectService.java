package fr.bnp.homeloancalculator.application;

import fr.bnp.homeloancalculator.domain.mortgage.HomeloanSimulation;
import fr.bnp.homeloancalculator.domain.mortgage.MortgageProject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MortgageProjectService {

    public MortgageProject create(MortgageProject mortgageProject) {
        return null;
    }

    public MortgageProject getProject(String id) {
        return null;
    }

    public MortgageProject updateProject(String id, MortgageProject mortgageProjectWithNewInformations) {
        return null;
    }

    public void removeProject(String id) {
        ;
    }

    public HomeloanSimulation addSimulation(String projectId, HomeloanSimulation homeloanSimulation) {
        return null;
    }

    public HomeloanSimulation updateSimulation(String projectId, UUID simulationId, HomeloanSimulation homeloanSimulation) {
        return null;
    }

    public void removeSimulation(String projectId, UUID simulationId) {
        ;
    }

    public List<HomeloanSimulation> listAllSimulations(String projectId) {
        return null;
    }

    public HomeloanSimulation getSimulation(String projectId, UUID simulationId) {
        return null;
    }

    public MortgageProject obtain(UUID mortgageProjectId) {
        return null;
    }

    public List<MortgageProject> listAll() {
        return null;
    }

    public MortgageProject update(UUID mortgageProjectId, MortgageProject mortgageProject) {
        return null;
    }

    public void remove(UUID mortgageProjectId) {
    }

    public HomeloanSimulation addHomeloanSimulation(UUID mortgageProjectId, HomeloanSimulation homeloanSimulation) {
        return null;
    }

    public HomeloanSimulation updateHomeloanSimulation(UUID mortgageProjectId, UUID homeloanSimulationId, HomeloanSimulation homeloanSimulation) {
        return null;
    }

    public List<HomeloanSimulation> listAllHomeloanSimulations(UUID mortgageProjectId) {
        return null;
    }

    public void removeHomeloanSimulation(UUID mortgageProjectId, UUID homeloanSimulationId) {
    }
}
