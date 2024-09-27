package com.example.regression;

public class QuadraticRegression {

    private double[] coefficients;

    public void fit(double[][] X, double[] y) {


        // Implementa la lógica de ajuste aquí
        coefficients = new double[3]; // Ejemplo: a, b, c para ax^2 + bx + c
    }

    public double getRSquared() {
        // Implementa la lógica para calcular R²
        return 0.0; // Retorna el valor calculado
    }

    public double[] getCoefficients() {
        return coefficients;
    }
}
