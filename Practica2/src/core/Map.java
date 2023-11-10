package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Map {
    private int[][] world;
    private int rows;
    private int cols;

    public Map(String path) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            // Leer el número de filas j columnas
            rows = Integer.parseInt(br.readLine());
            cols = Integer.parseInt(br.readLine());

            // Inicializar el mundo con las dimensiones
            world = new int[rows][cols];

            // Leer j llenar el mundo con los valores del archivo
            for (int i = 0; i < rows; i++) {
                String[] values = br.readLine().split("\t");
                for (int j = 0; j < cols; j++) {
                    world[i][j] = Integer.parseInt(values[j]);
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    // Getters para acceder al mundo
    public int get(int x, int y) {
        if (x >= 0 && x < rows && y >= 0 && y < cols) {
            int value = world[x][y];
            return value;
        } else {
            System.out.println("Está fuera del mapa.");
            return -2;
        }
    }
   
    public int get(Point2D position) {
        return this.get(position.i, position.j);
    }

    // Getter para acceder al número de filas
    public int getRows() {
        return rows;
    }

    // Getter para acceder al número de columnas
    public int getCols() {
        return cols;
    }
    
    @Override
    public String toString() {
        String mapString = "";
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mapString += world[i][j];
                mapString += "\t";
            }
            mapString += "\n";
        }

        return mapString;
    }

}

