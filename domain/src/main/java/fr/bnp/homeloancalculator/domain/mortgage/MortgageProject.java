package fr.bnp.homeloancalculator.domain.mortgage;

import fr.bnp.homeloancalculator.domain.exception.ErrorCodes;
import fr.bnp.homeloancalculator.domain.exception.MyAppHomeloanSimulationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class MortgageProject {
    private UUID id;
    private String referenceId; // External reference (eg. "name" of the principal borrower)
    private ProjectType projectType;
    private double householdCharges;
    private List<Borrower> borrowers;
    private double maxLoanPayment;
    private List<HomeloanSimulation> homeloanSimulations;

    // Constructor used when the project is created for the first time and credit cost is not yet calculated
    public MortgageProject(String referenceId,
                           ProjectType projectType,
                           double householdCharges,
                           List<Borrower> borrowers,
                           double maxLoanPayment) {
        this.id = UUID.randomUUID();
        this.referenceId = referenceId;
        this.projectType = projectType;
        this.householdCharges = householdCharges;
        this.borrowers = borrowers;
        this.maxLoanPayment = maxLoanPayment;
        this.homeloanSimulations = new ArrayList<>();
        // Validate
    }

    // Constructor used when the project is retrieved from the database
    public MortgageProject(UUID id, String referenceId,
                           ProjectType projectType,
                           double householdCharges,
                           List<Borrower> borrowers,
                           double maxLoanPayment,
                           List<HomeloanSimulation> homeloanSimulations) {
        this.id = id;
        this.referenceId = referenceId;
        this.projectType = projectType;
        this.householdCharges = householdCharges;
        this.borrowers = borrowers;
        this.maxLoanPayment = maxLoanPayment;
        this.homeloanSimulations = homeloanSimulations;
    }

    public MortgageProject update(MortgageProject mortgageProjectWithNewInformation) {
        this.referenceId = mortgageProjectWithNewInformation.getReferenceId();
        this.projectType = mortgageProjectWithNewInformation.getProjectType();
        this.householdCharges = mortgageProjectWithNewInformation.getHouseholdCharges();
        this.householdCharges = mortgageProjectWithNewInformation.getHouseholdCharges();
        this.borrowers = mortgageProjectWithNewInformation.getBorrowers();
        this.maxLoanPayment = mortgageProjectWithNewInformation.getMaxLoanPayment();
        this.homeloanSimulations = mortgageProjectWithNewInformation.getHomeloanSimulations();
        return this;
    }

    public void addHomeloanSimulation(HomeloanSimulation homeloanSimulation) {
        this.getHomeloanSimulations().add(homeloanSimulation);
    }

    public void updateHomeloanSimulation(UUID homeloanSimulationId, HomeloanSimulation homeloanSimulationWithNewInformation) {
        HomeloanSimulation homeloanSimulation = searchHomeloanSimulation(homeloanSimulationId);
        homeloanSimulation.update(homeloanSimulationWithNewInformation);
    }

    public void removeHomeloanSimulation(UUID homeloanSimulationId) {
        HomeloanSimulation homeloanSimulation = searchHomeloanSimulation(homeloanSimulationId);
        this.homeloanSimulations.remove(homeloanSimulation);
    }

    public HomeloanSimulation searchHomeloanSimulation(UUID homeloanSimulationId) {
        HomeloanSimulation homeloanSimulation =
                this.homeloanSimulations
                        .stream()
                        .filter(l -> l.getId().equals(homeloanSimulationId))
                        .findFirst()
                        .orElseThrow(() -> new MyAppHomeloanSimulationException(ErrorCodes.HOMELOAN_SIMULATION_NOT_FOUND));
        return homeloanSimulation;
    }

    public UUID getId() {
        return id;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public ProjectType getProjectType() {
        return projectType;
    }

    public double getHouseholdCharges() {
        return householdCharges;
    }

    public List<Borrower> getBorrowers() {
        return borrowers;
    }

    // public double getMaxLoanPayment() {return maxLoanPayment;}

    // Max loan payment = 35% of borrowers net income minus global charges
    public double getMaxLoanPayment() {
        return (((borrowers.stream().mapToDouble(b -> b.getNetIncome()).sum())
                - householdCharges) * 0.35);
    }

    public List<HomeloanSimulation> getHomeloanSimulations() {
        return homeloanSimulations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MortgageProject that = (MortgageProject) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return String.format("%s{id:%s)", this.getClass().getSimpleName(), referenceId);
    }

}
