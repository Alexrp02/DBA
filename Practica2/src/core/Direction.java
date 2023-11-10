package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */

/**
 *
 * @author carlosqp
 */
public class Direction {
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;
    // Array of positions to be evaluated around the agent    
    public static final List<Point2D> possibleMoves = new ArrayList<>(Arrays.asList(
        new Point2D(-1,0), // NORTH
        new Point2D(0,1), // EAST
        new Point2D(1,0), // SOUTH
        new Point2D(0,-1) // WEST
    )) ;
    
}