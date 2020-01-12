package fr.bnp.homeloancalculator.domain.mortgage;

public enum Periodicity {
    MONTHLY,
    QUARTERLY,
    BIANNUALLY,
    ANNUALLY;

    // Retourne le nombre de mois de la période
    public int numberOfMonths() {
        int numberOfMonths = 0;
        switch(this) {
            case MONTHLY: numberOfMonths = 1;
            break;
            case QUARTERLY: numberOfMonths = 3;
            break;
            case BIANNUALLY: numberOfMonths = 6;
            break;
            case ANNUALLY: numberOfMonths = 12;
            break;
        }
        return  numberOfMonths;
    }
}
