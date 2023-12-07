/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package behaviours;

import core.Point2D;
import core.Santa;
import core.Globals;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author manu
 */
public class SantaBehaviour extends Behaviour{
    
    private int step = 0;
    private boolean finish = false;

    @Override
    public void action() {
        switch (this.step) {
            case 0 -> {
                ACLMessage msg = myAgent.blockingReceive();
                System.out.println(msg);             
                
                // Creamos respuesta
                ACLMessage reply = msg.createReply();

                // Procesamos respuesta
                if (msg.getPerformative() == ACLMessage.PROPOSE) {
                    if(((Santa)this.myAgent).isGoodAgent()){
                        System.out.println("Ha sido un agente bueno." );
                        
                        reply.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
                        reply.setContent(Globals.SECRET_CODE);
                        this.myAgent.send(reply);
                        this.step = 1;
                    } else {
                        System.out.println("No ha sido un agente bueno." );

                        reply.setPerformative(ACLMessage.REJECT_PROPOSAL);
                        this.myAgent.send(reply);
                        this.finish = true;
                    }
                    
                } else {
                    System.out.println("Error in the coversation protocol - step " + 1);

                    // Error en el protocolo, devuelve failure
                    reply.setPerformative(ACLMessage.FAILURE);
                    ACLMessage replay = msg.createReply(ACLMessage.FAILURE);
                    this.myAgent.send(replay);
                    
                    myAgent.doDelete();
                }
            }
            
            case 1 -> { // El agente le pregunta su posicion
                ACLMessage msg = myAgent.blockingReceive();
                System.out.println(msg);

                if (msg.getPerformative() == ACLMessage.REQUEST) {
                    ACLMessage reply = msg.createReply(ACLMessage.INFORM);
                    
                    Point2D SantaPosition = new Point2D(0,0);
                    reply.setContent(SantaPosition.toString());
                    
                    this.myAgent.send(reply);
                    this.step = 2;
                    
                } else {
                    System.out.println("Error in the coversation protocol");
                    myAgent.doDelete();
                }
            }
            
            case 2 -> { // El agente ha llegado a la posicion de santa
                ACLMessage msg = myAgent.blockingReceive();
                System.out.println(msg);

                if (msg.getPerformative() == ACLMessage.INFORM) {
                    ACLMessage reply = msg.createReply(ACLMessage.INFORM);                    
                    reply.setContent("¡HO! ¡HO! ¡HO! ¡Feliz Navidad!");
                    this.myAgent.send(reply);
                    this.finish = true;
                    
                } else {
                    System.out.println("Error in the coversation protocol");
                    myAgent.doDelete();
                }
            }

        }
    }

    @Override
    public boolean done() {
        return finish;
    }
    
}
