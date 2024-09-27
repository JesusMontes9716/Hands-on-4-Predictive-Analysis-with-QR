package com.example.regression;

import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

public class Main {
    public static void main(String[] args) {
        try {
            String filePath = "dataset.csv"; //  ruta del archivo CSV
            DataLoader loader = new DataLoader(); // Crear una instancia de DataLoader

            // Cargar los datos y las etiquetas (targets) desde el archivo CSV
            double[][] data = loader.loadData(filePath);
            double[] target = loader.getTarget(filePath);

            // Dividir el conjunto de datos en 70% para entrenamiento y 30% para prueba
            DataSplitter splitter = new DataSplitter(data, target, 0.7);

            // Obtener los conjuntos de entrenamiento y prueba
            double[][] trainData = splitter.getTrainData();
            double[] trainTarget = splitter.getTrainTarget();
            double[][] testData = splitter.getTestData();
            double[] testTarget = splitter.getTestTarget();

            // Transformar los datos de entrenamiento y prueba a características cuadráticas
            QuadraticFeatures qf = new QuadraticFeatures();
            double[][] transformedTrainData = qf.transform(trainData);
            double[][] transformedTestData = qf.transform(testData);

            // Realizar la regresión cuadrática usando los datos de entrenamiento transformados
            OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
            regression.newSampleData(trainTarget, transformedTrainData);

            // Obtener los coeficientes de la regresión cuadrática
            double[] coefficients = regression.estimateRegressionParameters();

            // Calcular el R² (coeficiente de determinación) en los datos de prueba
            double rSquared = calculateRSquared(coefficients, transformedTestData, testTarget);

            // Mostrar los resultados
            System.out.println("Mejor R²: " + rSquared);
            System.out.println("Coeficientes: ");
            for (double coef : coefficients) {
                System.out.print(coef + " ");
            }
        } catch (Exception e) {
            System.out.println("No se pudieron cargar los datos correctamente.");
            e.printStackTrace();
        }
    }

    // Método para calcular el coeficiente de determinación (R²) para los datos de prueba
    public static double calculateRSquared(double[] coefficients, double[][] testData, double[] testTarget) {
        double[] predictions = new double[testTarget.length];
        double ssTotal = 0.0;
        double ssResidual = 0.0;
        double mean = 0.0;

        // Calcular la media de los valores objetivo (target)
        for (double value : testTarget) {
            mean += value;
        }
        mean /= testTarget.length;

        // Calcular las predicciones manualmente usando los coeficientes del modelo
        for (int i = 0; i < testData.length; i++) {
            predictions[i] = coefficients[0]; // Intercepto (beta_0)
            for (int j = 0; j < testData[i].length; j++) {
                predictions[i] += coefficients[j + 1] * testData[i][j]; // Sumar los términos correspondientes
            }
            ssTotal += Math.pow(testTarget[i] - mean, 2);
            ssResidual += Math.pow(testTarget[i] - predictions[i], 2);
        }

        // Calcular el coeficiente de determinación (R²)
        return 1 - (ssResidual / ssTotal);
    }
}
