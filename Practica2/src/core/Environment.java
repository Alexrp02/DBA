package core;

import java.util.ArrayList;
import java.util.List;
import ui.MapVisualization;
import ui.UI;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author carlosqp
 */
public class Environment {
    
    // World
    private Map map;
    
//    //Map Visualization
//    private MapVisualization ui ; 
    
    //GUI
    private UI ui = new UI() ;
            
    
    
    // Sensors
    private ArrayList<Integer> sensors;
    
    
    // Positions
    private Point2D currentPosition;
    private Point2D goalPosition;
    
    //agentPath
    private List<Point2D> agentPath;
    
    public Environment(String path, Point2D initialPosition, List<Point2D> agentPath) {
        
        this.map = new Map(path);
        this.agentPath = agentPath;
        
        this.currentPosition = initialPosition;
//        ui = new MapVisualization(map, currentPosition, this.agentPath) ;
        ui.setVisible(true);
        ui.initializeVariables(map, currentPosition, agentPath);
        
        
        this.sensors = new ArrayList<>(8);
        
        for(int i = 0; i < 8; i++){
            this.sensors.add(-2);
        }
        
        // To initialize sensors to the correct value
        this.see();
    }
    
    // ¿¿Cómo se ve el mundo??
    public void see() {
        
        // Logic here
        this.sensors.set(Direction.NORTH, this.map.get(this.currentPosition.i-1, this.currentPosition.j));
        this.sensors.set(Direction.SOUTH,this.map.get(this.currentPosition.i+1, this.currentPosition.j));
        this.sensors.set(Direction.EAST,this.map.get(this.currentPosition.i, this.currentPosition.j+1));
        this.sensors.set(Direction.WEST,this.map.get(this.currentPosition.i, this.currentPosition.j-1));
        
        for(int i=0 ; i<Direction.possibleMoves.size() ; i++) {
            this.sensors.set(i, this.map.get(currentPosition.add(Direction.possibleMoves.get(i)))) ;
        }
   
        
//        this.northSensor = this.map.get(this.currentPosition.i, this.currentPosition.j-1);
//        this.southSensor = this.map.get(this.currentPosition.i, this.currentPosition.j+1);
//        this.eastSensor = this.map.get(this.currentPosition.i+1, this.currentPosition.j);
//        this.westSensor = this.map.get(this.currentPosition.i-1, this.currentPosition.j);

    }

    public ArrayList<Integer> getSensors() {
        return sensors;
    }

    
    public int getNorthSensor() {
        return this.sensors.get(Direction.NORTH);
    }

    public int getEastSensor() {
        return this.sensors.get(Direction.EAST);
    }

    public int getSouthSensor() {
        return this.sensors.get(Direction.SOUTH);
    }

    public int getWestSensor() {
        return this.sensors.get(Direction.WEST);
    }

    public Point2D getCurrentPosition() {
        return currentPosition;
    }

    public Point2D getGoalPosition() {
        return goalPosition;
    }
    
    public void setGoalPosition(Point2D goalPosition){
        this.goalPosition = goalPosition;
        this.ui.setGoalPosition(goalPosition);
    }
    
    // Movement functions
    public Boolean moveNorth() {
        // Comprueba si se puede mover (no hay obstáculo)
        if(this.map.get(this.currentPosition.i-1, this.currentPosition.j) == 0) {
            this.currentPosition.i--;
            return true;
        } else {
            return false;
        }
    }
    
    public Boolean moveEast() {
        // Comprueba si se puede mover (no hay obstáculo)
        if(this.map.get(this.currentPosition.i, this.currentPosition.j+1) == 0) {
            this.currentPosition.j++;
            return true;
        } else {
            return false;
        }
    }
    
    public Boolean moveSouth() {
        // Comprueba si se puede mover (no hay obstáculo)
        if(this.map.get(this.currentPosition.i+1, this.currentPosition.j) == 0) {
            this.currentPosition.i++;
            return true;
        } else {
            return false;
        }
    }
    
    public Boolean moveWest() {
        // Comprueba si se puede mover (no hay obstáculo)
        if(this.map.get(this.currentPosition.i, this.currentPosition.j-1) == 0) {
            this.currentPosition.j--;
            return true;
        } else {
            return false;
        }
    }
    
    public Boolean move(int direction) {
//        switch(direction) {
//            case(Direction.NORTH): return moveNorth();
//            case(Direction.EAST): return moveEast();
//            case(Direction.SOUTH): return moveSouth();
//            case(Direction.WEST): return moveWest();
//            default: return false;            
//        }
        Point2D newPoint = currentPosition.add(Direction.possibleMoves.get(direction)) ;
        if(this.map.get(newPoint.i, newPoint.j) == 0) {
            agentPath.add(currentPosition);
            currentPosition = newPoint ;
            return true ;
        }
        return false;
    }
    
    public void addAgentMessage(String message) {
        ui.addAgentMessage(message);
    }
    
    public void addSantaMessage(String message) {
        ui.addSantaMessage(message);
    }
    
    // Print data
    public void print() {
        String data = 
                "\n---------------------------------\nPrinting environment:\n";
        
        for (int i = 0; i < this.map.getRows(); i++) {
            for (int j = 0; j < this.map.getCols(); j++) {
                
                if (i==this.currentPosition.i && j==this.currentPosition.j) 
                    data += "A";
                else if (i==this.goalPosition.i && j==this.goalPosition.j) 
                    data += "X";
                else 
                    data += this.map.get(i,j);
                
                data += "\t";
            }
            data += "\n";
        }
        
        data += "Agent position = [i: " + this.currentPosition.i + ", j: " + this.currentPosition.j + "]\n";
        data += "Goal position = [i: " + this.goalPosition.i + ", j: " + this.goalPosition.j + "]\n";
        
        // Pintamos en la UI:
        ui.updateVisualization(map, currentPosition, goalPosition);
        
        System.out.println(data);
    }
    
}
