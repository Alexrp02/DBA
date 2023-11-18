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
import java.util.logging.Level;
import java.util.logging.Logger;


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
        
        boolean debug = false;
        
        if(!debug){
            
            //Obtenemos la dirección en la que nos movemos
            int nextDirection = ((Agent203)this.myAgent).getNextDirection();
            
            try {
                //Se la pasamos al entorno y nos movemos despues de esperar 2 segundos
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MovementBehaviour.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(!((Agent203)this.myAgent).getEnvironment().move(nextDirection))
                System.out.println("No puedo moverme");
            else{
                ((Agent203) myAgent).steps ++ ;
            }
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
        return false ;
    }
    
    
}
