package fr.bnp.homeloancalculator.domain.calculator;

 // Interface that can find the root (= zero) of univariate functions.
 public interface UnivariateSolver {

     // Calculate the root of a given univariate function.
     public double calculateRoot(UnivariateFunction univariateFunction);

     //Calculate the root of given univariate function and startvalue for the first iteration in numerical method
     public double calculateRoot(UnivariateFunction univariateFunction, double startValue);
}
