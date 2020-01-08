package fr.bnp.homeloancalculator.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MortgageProjectDAO extends JpaRepository <MortgageProjectJPA, String> {
}
