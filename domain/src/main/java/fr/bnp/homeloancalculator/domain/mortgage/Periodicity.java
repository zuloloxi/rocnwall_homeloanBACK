package fr.bnp.homeloancalculator.domain.mortgage;

public enum Periodicity {
    MONTHLY,
    QUARTERLY,
    BIANNUALLY,
    ANNUALLY;

    // Retourne le nombre de mois de la période
    public int numberOfMonths() {
        switch(this) {
            case MONTHLY: return 1;
            case QUARTERLY: return 3;
            case BIANNUALLY: return 6;
            case ANNUALLY: return 12;
        }
        throw new AssertionError("Opération inconnue : " + this);
    }
}
