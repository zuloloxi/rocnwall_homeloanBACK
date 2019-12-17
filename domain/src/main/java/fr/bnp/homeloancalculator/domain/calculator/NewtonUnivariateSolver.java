package fr.bnp.homeloancalculator.domain.calculator;

// A class that can find the root (=zero) of univariate function using Newton-Raphson method.
public class NewtonUnivariateSolver implements UnivariateSolver
{
    // Maximum iteration steps
    private int MAX_ITERATION = 1000000;

    // Root found if current root - previous root < ACCURACY
    private double ACCURACY = 0.0000000000001;

    // Default initial value for the first iteration, if not specified.
    private double DEFAULT_START_VALUE = 1.25;

    // Calculate the root of given univariate function.
    public double calculateRoot(UnivariateFunction univariateFunction)
    {
        return calculateRoot(univariateFunction, DEFAULT_START_VALUE);
    }

    // Calculate the root of given univariate function using Newton-Raphson method.
    public double calculateRoot(UnivariateFunction univariateFunction, double startValue)
    {
        UnivariateFunction derivative = univariateFunction.getDerivativeFunction();
        double x0 = startValue;
        double x1;

        for (int counter=0; counter < this.MAX_ITERATION; counter++)
        {
            x1 = x0 - ( univariateFunction.getValue(x0) / derivative.getValue(x0) );

            if ( Math.abs(x1 - x0) < this.ACCURACY )
                return x1;

            x0 = x1;
        }

        return Double.NaN;
    }
}
