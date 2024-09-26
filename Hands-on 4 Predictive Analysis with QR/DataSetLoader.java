import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataSetLoader {

    private List<double[]> dataList = new ArrayList<>();

    // Constructor: carga los datos del archivo CSV
    public DataSetLoader(String filePath) {
        loadData(filePath);
    }

    // Método para cargar los datos del archivo CSV
    private void loadData(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                double x = Double.parseDouble(values[0]);
                double y = Double.parseDouble(values[1]);
                dataList.add(new double[] { x, y });
            }
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para obtener los datos cargados
    public List<double[]> getData() {
        return dataList;
    }
}
