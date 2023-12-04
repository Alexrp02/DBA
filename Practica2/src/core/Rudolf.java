/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;
import jade.core.Agent;
import behaviours.receiveMessageBehaviour;
import java.util.ArrayList;
/**
 *
 * @author manu
 */
public class Rudolf extends Agent{
    
    public ArrayList<Point2D> reindeerPositions;
     @Override
    protected void setup(){
        reindeerPositions = new ArrayList<Point2D>();
        reindeerPositions.add( new Point2D(29,29));
        reindeerPositions.add( new Point2D(15,15));
        this.addBehaviour(new receiveMessageBehaviour());
    }
    
}
