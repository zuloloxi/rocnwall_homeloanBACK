package fr.bnp.homeloancalculator.domain.exception;

import java.util.HashSet;
import java.util.Set;

public class MyAppHomeloanSimulationException extends RuntimeException {
    private Set<String> codeErreurs = new HashSet<>();

    public MyAppHomeloanSimulationException(String codeErreur) {
        this.codeErreurs.add(codeErreur);
    }

    public MyAppHomeloanSimulationException(Set<String> codeErreurs) {
        this.codeErreurs = codeErreurs;
    }

    public Set<String> getCodeErreurs() {
        return codeErreurs;
    }
}
