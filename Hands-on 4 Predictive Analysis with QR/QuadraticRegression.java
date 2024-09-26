import java.util.List;

public class QuadraticRegression {

    // Coeficientes de la ecuación cuadrática (ax^2 + bx + c)
    private double a, b, c;

    // Método para ajustar el modelo cuadrático a los datos
    public void fit(List<double[]> data) {
        int n = data.size();

        // Variables para almacenar las sumas necesarias para los cálculos
        double sumX = 0, sumY = 0, sumX2 = 0, sumX3 = 0, sumX4 = 0, sumXY = 0, sumX2Y = 0;

        for (double[] point : data) {
            double x = point[0];
            double y = point[1];
            sumX += x;
            sumY += y;
            sumX2 += x * x;
            sumX3 += x * x * x;
            sumX4 += x * x * x * x;
            sumXY += x * y;
            sumX2Y += x * x * y;
        }

        // Crear el sistema de ecuaciones lineales para resolver los coeficientes a, b y
        // c
        double[][] matrix = {
                { sumX4, sumX3, sumX2 },
                { sumX3, sumX2, sumX },
                { sumX2, sumX, n }
        };

        double[] vector = { sumX2Y, sumXY, sumY };

        // Resolver el sistema de ecuaciones para encontrar los coeficientes a, b y c
        double[] solution = solve(matrix, vector);
        a = solution[0];
        b = solution[1];
        c = solution[2];
    }

    // Método para predecir el valor de Y basado en el modelo cuadrático (ax^2 + bx
    // + c)
    public double predict(double x) {
        return a * x * x + b * x + c;
    }

    // Método para calcular el coeficiente de determinación (R²)
    public double getRSquared(List<double[]> data) {
        double sumY = 0;
        double sumY2 = 0;
        double sumResiduals2 = 0;

        for (double[] point : data) {
            double x = point[0];
            double y = point[1];
            double predictedY = predict(x);
            sumY += y;
            sumY2 += y * y;
            sumResiduals2 += (y - predictedY) * (y - predictedY);
        }

        double meanY = sumY / data.size();
        double totalVariation = sumY2 - data.size() * meanY * meanY;
        double unexplainedVariation = sumResiduals2;

        return 1 - (unexplainedVariation / totalVariation);
    }

    // Método para resolver el sistema de ecuaciones lineales (usando eliminación de
    // Gauss)
    private double[] solve(double[][] matrix, double[] vector) {
        int n = vector.length;

        // Forward elimination
        for (int i = 0; i < n; i++) {
            int max = i;
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(matrix[j][i]) > Math.abs(matrix[max][i])) {
                    max = j;
                }
            }

            double[] temp = matrix[i];
            matrix[i] = matrix[max];
            matrix[max] = temp;

            double t = vector[i];
            vector[i] = vector[max];
            vector[max] = t;

            for (int j = i + 1; j < n; j++) {
                double factor = matrix[j][i] / matrix[i][i];
                vector[j] -= factor * vector[i];
                for (int k = i; k < n; k++) {
                    matrix[j][k] -= factor * matrix[i][k];
                }
            }
        }

        // Back substitution
        double[] solution = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < n; j++) {
                sum += matrix[i][j] * solution[j];
            }
            solution[i] = (vector[i] - sum) / matrix[i][i];
        }
        return solution;
    }
}
