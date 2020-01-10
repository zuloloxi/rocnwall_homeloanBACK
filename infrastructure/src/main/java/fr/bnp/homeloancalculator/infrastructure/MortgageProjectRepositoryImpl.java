package fr.bnp.homeloancalculator.infrastructure;

import fr.bnp.homeloancalculator.domain.exception.ErrorCodes;
import fr.bnp.homeloancalculator.domain.exception.MyAppHomeloanSimulationException;
import fr.bnp.homeloancalculator.domain.mortgage.MortgageProject;
import fr.bnp.homeloancalculator.domain.mortgage.MortgageProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class MortgageProjectRepositoryImpl implements MortgageProjectRepository {

    @Autowired
    private MortgageProjectDAO mortgageProjectDAO;

    @Override
    public void save(MortgageProject mortgageProject) {
        MortgageProjectJPA mortgageProjectJPA = mortgageProjectDAO.save(new MortgageProjectJPA(mortgageProject));
    }

    @Override
    public MortgageProject get(UUID id) {
        return mortgageProjectDAO
                .findById(id.toString())
                .map(MortgageProjectJPA::toMortgageProject)
                .orElseThrow(() -> new MyAppHomeloanSimulationException(ErrorCodes.MORTGAGE_PROJECT_NOT_FOUND));
    }

    @Override
    public List<MortgageProject> findAll() {
        return mortgageProjectDAO
                .findAll()
                .stream()
                .map(MortgageProjectJPA::toMortgageProject)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        mortgageProjectDAO.deleteById(id.toString());
    }
}
