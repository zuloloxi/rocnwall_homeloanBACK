package fr.bnp.homeloancalculator.domain.math;

import org.springframework.stereotype.Component;

// Calculator of the effective interest rate (EIR) given the present value and a series of cash flow.
public class EIRCalculator {

    private UnivariateSolver univariateSolver = new NewtonUnivariateSolver();

    public EIRCalculator() {
    }

    public double calculateEffectiveInterestRate(double presentValue, double[] cashFlow) {
        // transform presentValue and cashFlow to polynomial function's coefficients
        double[] coefficients = new double[cashFlow.length + 1];
        coefficients[cashFlow.length] = presentValue;

        for (int index = 0; index < cashFlow.length; index++) {
            coefficients[cashFlow.length - index - 1] = -1.00 * cashFlow[index];
        }

        // get zero of the function
        UnivariateFunction polynomialFunction = new PolynomialFunction(coefficients);
        double root = univariateSolver.calculateRoot(polynomialFunction);

        return (root - 1.00);
    }
}
