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
    
    // Sensors
    private int northSensor;
    private int eastSensor;
    private int southSensor;
    private int westSensor;
    
    // Positions
    private Point2D currentPosition;
    private Point2D goalPosition;
    
    public Environment(String path, Point2D initialPosition, Point2D goalPosition) {
        
        this.map = new Map(path);
        
        this.currentPosition = initialPosition;
        this.goalPosition = goalPosition;
        
        // To initialize sensors to the correct value
        this.see();
    }
    
    // ¿¿Cómo se ve el mundo??
    public void see() {
        
        // Logic here
        this.northSensor = this.map.get(this.currentPosition.i, this.currentPosition.j-1);
        this.southSensor = this.map.get(this.currentPosition.i, this.currentPosition.j+1);
        this.eastSensor = this.map.get(this.currentPosition.i+1, this.currentPosition.j);
        this.westSensor = this.map.get(this.currentPosition.i-1, this.currentPosition.j);

    }

    public int getNorthSensor() {
        return northSensor;
    }

    public int getEastSensor() {
        return eastSensor;
    }

    public int getSouthSensor() {
        return southSensor;
    }

    public int getWestSensor() {
        return westSensor;
    }

    public Point2D getCurrentPosition() {
        return currentPosition;
    }

    public Point2D getGoalPosition() {
        return goalPosition;
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

        System.out.println(data);
    }
    
}
