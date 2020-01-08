package fr.bnp.homeloancalculator.domain.exception;

public final class ErrorCodes {
    private ErrorCodes() {}

    public static final String NOT_FOUND = "ERR_0001";
    public static final String MORTGAGE_PROJECT_NOT_FOUND = NOT_FOUND + ".1";
    public static final String HOMELOAN_SIMULATION_NOT_FOUND = NOT_FOUND + ".2";

    public static final String MORTGAGE_PROJECT_MUST_HAVE_A_BORROWER = "ERR_0002";
}
