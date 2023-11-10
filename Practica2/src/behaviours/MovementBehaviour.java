/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package behaviours;

import core.Environment;
import core.Direction;
import core.Agent203;

import jade.core.behaviours.Behaviour;

import java.util.Scanner;


/**
 *
 * @author alberto
 */
public class MovementBehaviour extends Behaviour {
    private Scanner scanner;
    
    public MovementBehaviour(){
        super();
        this.scanner = new Scanner(System.in);
    }
    
    
    @Override
    public void action() {
        
        boolean debug = true;
        
        if(!debug){
            
            //Obtenemos la dirección en la que nos movemos
            int nextDirection = ((Agent203)this.myAgent).getNextDirection();
            
            //Se la pasamos al entorno y nos movemos
            
            if(!((Agent203)this.myAgent).getEnvironment().move(nextDirection))
                System.out.println("No puedo moverme");
        }else{
            //Se pide al usuario que se inserte la dirección a la que se mueve
            System.out.println("Introduzca la dirección del movimiento: ");
            
            int nextDirection = scanner.nextInt();
            
            if(!((Agent203)this.myAgent).getEnvironment().move(nextDirection))
                System.out.println("No puedo moverme");
            else{
                System.out.println(((Agent203)this.myAgent).getEnvironment().getCurrentPosition().equals(
                        ((Agent203)this.myAgent).getEnvironment().getGoalPosition())
                );
                
                System.out.println("Current: "+((Agent203)this.myAgent).getEnvironment().getCurrentPosition().i + ", "+((Agent203)this.myAgent).getEnvironment().getCurrentPosition().j);
                System.out.println("Goal: "+((Agent203)this.myAgent).getEnvironment().getGoalPosition().i + "," + ((Agent203)this.myAgent).getEnvironment().getGoalPosition().j);
            }
            
        }
        
    }

    @Override
    public boolean done() {
        return ((Agent203)this.myAgent).getEnvironment().getCurrentPosition().equals(((Agent203)this.myAgent).getEnvironment().getGoalPosition());
    }
    
    
}
