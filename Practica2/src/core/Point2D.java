package core;

import java.util.Objects;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * @option
 */
public class Point2D {
    public int i;
    public int j;
    
    public Point2D() {
        i=0;
        j=0;
    }

    public Point2D(int i, int j) {
        this.i = i;
        this.j = j;
    }
    
    public Point2D substract (Point2D other) {
        return new Point2D(i-other.i, j-other.j) ;
    }
    
    public Point2D add (Point2D other) {
        return new Point2D(i+other.i, j+other.j) ;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Point2D point2D = (Point2D) obj;
        return i == point2D.i && j == point2D.j;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }
    
    // Método toString para representar el punto en formato "i,j"
    @Override
    public String toString() {
        return i + "," + j;
    }

    // Método estático fromString para crear un objeto Point2D desde una cadena en formato "i,j"
    public static Point2D fromString(String str) {
        String[] coordinates = str.split(",");
        if (coordinates.length == 2) {
            try {
                int i = Integer.parseInt(coordinates[0].trim());
                int j = Integer.parseInt(coordinates[1].trim());
                return new Point2D(i, j);
            } catch (NumberFormatException e) {
                // Manejar la excepción si las coordenadas no son números válidos
                e.printStackTrace();
            }
        }
        // Retornar null si el formato no es válido
        return null;
    }
}


