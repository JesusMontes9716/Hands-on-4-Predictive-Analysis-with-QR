package com.example.regression;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {
    public double[][] loadData(String filePath) throws Exception {
        List<double[]> data = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            reader.readNext(); // Saltar la primera línea (encabezado)
            while ((line = reader.readNext()) != null) {
                double[] row = new double[]{Double.parseDouble(line[0]), Double.parseDouble(line[1])};
                data.add(row);
            }
        }
        return data.toArray(new double[0][]);
    }

    public double[] getTarget(String filePath) throws Exception {
        List<Double> target = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            reader.readNext(); // Saltar la primera línea (encabezado)
            while ((line = reader.readNext()) != null) {
                target.add(Double.parseDouble(line[2]));
            }
        }
        return target.stream().mapToDouble(Double::doubleValue).toArray();
    }
}
