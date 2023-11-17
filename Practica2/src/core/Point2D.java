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
}


