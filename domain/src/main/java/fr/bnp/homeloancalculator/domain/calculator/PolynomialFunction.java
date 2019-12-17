package fr.bnp.homeloancalculator.domain.calculator;

public class PolynomialFunction implements UnivariateFunction
{
    // Array that holds function's coefficients
    private double[] coefficients;

    // Constructor that accepts array of polynomial function's coefficients as its argument
    public PolynomialFunction(double[] coefficients)
    {
        this.coefficients = coefficients;
    }

    // Returns the derivative function of this function
    public UnivariateFunction getDerivativeFunction()
    {
        double[] derivativeCoefficients = new double[this.coefficients.length-1];

        for (int index=0; index < derivativeCoefficients.length; index++)
        {
            derivativeCoefficients[index] = this.coefficients[index+1]*(index+1);
        }

        return new PolynomialFunction(derivativeCoefficients);
    }

    // Returns the value of this function given its input argument

    public double getValue(double input)
    {
        double value = this.coefficients[0];

        for (int exponent=1; exponent < this.coefficients.length; exponent++)
        {
            value += this.coefficients[exponent] * Math.pow(input, exponent);
        }

        return value;
    }
}
