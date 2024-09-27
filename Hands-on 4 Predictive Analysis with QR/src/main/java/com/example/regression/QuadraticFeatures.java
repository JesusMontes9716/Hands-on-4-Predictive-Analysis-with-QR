package com.example.regression;

public class QuadraticFeatures {

    // Este método transforma un conjunto de datos añadiendo características cuadráticas
    public double[][] transform(double[][] data) {
        int n = data.length; // Número de filas (observaciones) en el conjunto de datos original
        int m = data[0].length; // Número de columnas (variables independientes) en el conjunto de datos original

        // Calcula el número total de características cuadráticas que se añadirán.
        // Incluye las variables originales más todas las combinaciones de productos cuadráticos.
        int numQuadraticFeatures = m + (m * (m + 1)) / 2;

        // Matriz para almacenar los datos transformados, con las nuevas características cuadráticas
        double[][] transformedData = new double[n][numQuadraticFeatures];

        // Bucle para recorrer cada observación (fila) en los datos originales
        for (int i = 0; i < n; i++) {
            int colIndex = 0; // Índice para colocar las nuevas características en la fila correspondiente

            // Primera parte: copia las características originales a la matriz transformada
            for (int j = 0; j < m; j++) {
                transformedData[i][colIndex++] = data[i][j];
            }

            // Segunda parte: añade las características cuadráticas (productos de las variables originales)
            for (int j = 0; j < m; j++) {
                for (int k = j; k < m; k++) {
                    // Añade el producto de las variables j y k (incluyendo cuadrados cuando j == k)
                    transformedData[i][colIndex++] = data[i][j] * data[i][k];
                }
            }
        }

        // Devuelve la nueva matriz con las características cuadráticas añadidas
        return transformedData;
    }
}
