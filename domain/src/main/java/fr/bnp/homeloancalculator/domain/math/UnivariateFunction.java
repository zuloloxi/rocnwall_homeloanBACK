package fr.bnp.homeloancalculator.domain.math;

//Representation of an univariate function
public interface UnivariateFunction {

    // Returns the derivative function of this function.
    UnivariateFunction getDerivativeFunction();

    // Returns the value of this function given input argument.
    double getValue(double input);

}

