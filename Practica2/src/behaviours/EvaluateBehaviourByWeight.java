/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package behaviours;

import core.Agent203;
import core.Direction;
import core.Point2D;
import jade.core.behaviours.Behaviour;

/**
 *
 * @author alberto
 */
public class EvaluateBehaviourByWeight extends Behaviour {

    int nextDirection = 0;
    // Inicializamos al valor más alto posible para double
    double bestValue = Double.MAX_VALUE;

    @Override
    public void action() {
        
        // Restablece los valores
        nextDirection = 0;
        bestValue = Double.MAX_VALUE;
        
        // Obtiene los pesos de las casillas colindantes
        // Trata memory para obtener los pesos de las posiciones de los sensores
        
        for(int i=0; i< ((Agent203) myAgent).sensorsWeight.size(); ++i) {
            double value = ((Agent203) myAgent).sensorsWeight.get(i);

            if(value < bestValue) {
                nextDirection = i;
                bestValue = value;
            }
        } 
        
        ((Agent203) myAgent).setNextDirection(nextDirection);
        // printSensorsWeight();
    }
    
    // Debug only
    public void printSensorsWeight() {
        String output = "sensorsWeight = [North:";
        output += ((Agent203) myAgent).sensorsWeight.get(0);
        output += ", East:";
        output += ((Agent203) myAgent).sensorsWeight.get(1);
        output += ", South:";
        output += ((Agent203) myAgent).sensorsWeight.get(2);
        output += ", West:";
        output += ((Agent203) myAgent).sensorsWeight.get(3);
        output += "], NextDirection: ";
        output += nextDirection;
        
        System.out.println(output);
    }

    @Override
    public boolean done() {
        return false ;
    }

}
