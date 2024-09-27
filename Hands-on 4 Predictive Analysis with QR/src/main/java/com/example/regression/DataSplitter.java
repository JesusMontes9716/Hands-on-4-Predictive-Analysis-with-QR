package com.example.regression;

import java.util.Random;

public class DataSplitter {

    // Arrays para almacenar los datos y objetivos (targets) de entrenamiento y prueba
    private double[][] trainData;
    private double[] trainTarget;
    private double[][] testData;
    private double[] testTarget;

    // Constructor que divide los datos en conjuntos de entrenamiento y prueba, según un ratio
    public DataSplitter(double[][] data, double[] target, double trainRatio) {
        int totalSize = data.length; // Total de observaciones en los datos originales
        int trainSize = (int) (totalSize * trainRatio); // Tamaño del conjunto de entrenamiento
        int testSize = totalSize - trainSize; // Tamaño del conjunto de prueba

        // Inicialización de los arrays para entrenamiento y prueba
        trainData = new double[trainSize][];
        trainTarget = new double[trainSize];
        testData = new double[testSize][];
        testTarget = new double[testSize];

        // Crear un array de índices para las observaciones
        Random rand = new Random();
        int[] indices = new int[totalSize];
        for (int i = 0; i < totalSize; i++) {
            indices[i] = i; // Inicializar el array de índices en secuencia
        }

        // Mezclar el array de índices para dividir los datos de manera aleatoria
        shuffleArray(indices, rand);

        // Llenar el conjunto de entrenamiento con los primeros 'trainSize' índices
        for (int i = 0; i < trainSize; i++) {
            trainData[i] = data[indices[i]]; // Asignar los datos correspondientes al conjunto de entrenamiento
            trainTarget[i] = target[indices[i]]; // Asignar las etiquetas (targets) correspondientes
        }

        // Llenar el conjunto de prueba con los índices restantes
        for (int i = 0; i < testSize; i++) {
            testData[i] = data[indices[trainSize + i]]; // Asignar los datos al conjunto de prueba
            testTarget[i] = target[indices[trainSize + i]]; // Asignar las etiquetas (targets) correspondientes
        }
    }

    // Métodos para obtener los datos y objetivos de entrenamiento
    public double[][] getTrainData() {
        return trainData;
    }

    public double[] getTrainTarget() {
        return trainTarget;
    }

    // Métodos para obtener los datos y objetivos de prueba
    public double[][] getTestData() {
        return testData;
    }

    public double[] getTestTarget() {
        return testTarget;
    }

    // Método para mezclar el array de índices aleatoriamente
    private void shuffleArray(int[] array, Random rand) {
        // Algoritmo Fisher-Yates para mezclar el array de forma aleatoria
        for (int i = array.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1); // Seleccionar un índice aleatorio entre 0 e i
            int temp = array[index]; // Intercambiar los elementos
            array[index] = array[i];
            array[i] = temp;
        }
    }
}
