import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Cargar los datos desde el archivo DataSet.csv
        DataSetLoader loader = new DataSetLoader("DataSet.csv");

        // Obtener los datos cargados
        List<double[]> data = loader.getData();

        // Crear una instancia del modelo de regresión cuadrática
        QuadraticRegression model = new QuadraticRegression();

        // Entrenar el modelo con los datos cargados
        model.fit(data);

        // Calcular el coeficiente de determinación (R²)
        double rSquared = model.getRSquared(data);

        // Imprimir el resultado
        System.out.println("Coeficiente de determinación (R^2): " + rSquared);
    }
}
