package fr.bnp.homeloancalculator.domain.mortgage;

import java.util.List;
import java.util.UUID;

public interface MortgageProjectRepository {
    void save(MortgageProject mortgageProject);
    MortgageProject get(UUID id);
    List<MortgageProject> findAll();
    void delete(UUID id);
}
