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
public class EvaluateBehaviour extends Behaviour {

    Point2D goal ;
    Point2D currentPosition ;
    int nextDirection = 0;
    // Inicializamos al valor más alto posible para double
    double bestValue = Double.MAX_VALUE;

    @Override
    public void action() {
        goal = ((Agent203) this.myAgent).getEnvironment().getGoalPosition();
        currentPosition = ((Agent203) this.myAgent).getEnvironment().getCurrentPosition();
        System.out.println("Evaluating...");
        for (Point2D possibleMove : Direction.possibleMoves) {
            // Comprobamos si la dirección a evaluar no nos lleva a un muro ni fuera del mapa
            int currentDirection = Direction.possibleMoves.indexOf(possibleMove) ;
            if (((Agent203) this.myAgent).getEnvironment().getSensors().get(currentDirection) == 0){
                // Comprobamos si la evaluación de movernos es menor que la actual
                double value = getDistance(currentPosition.add(possibleMove), goal) ;
                if(value < bestValue){
                    // Si es menor, actualizamos el mejor
                    nextDirection = currentDirection;
                    bestValue = value ;
                }
            }
                        

        }
        
        ((Agent203) this.myAgent).setNextDirection(nextDirection);

    }

    @Override
    public boolean done() {
        return false ;
    }

    private double getDistance(Point2D start, Point2D end) {
        Point2D substract = start.substract(end);
        double result = Math.abs(substract.i) + Math.abs(substract.j);
        return result;
    }

}
