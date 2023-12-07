/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package behaviours;

import core.Agent203_1;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
// import jade.core.AID;

import core.Rudolf;
import core.Globals;
import core.Point2D;
import jade.core.AID;
import static java.lang.Thread.sleep;
//import core.Globals;    // Para los IDs y el código secreto

/**
 *
 * @author manu
 */
public class Agent203Behaviour extends Behaviour {
    private int step = 0;
    private boolean finish = false;
    // private final String rudolf_AID = ((Agent203_1)this.myAgent).getRudolfAID();
    private final String rudolf_AID = Globals.RUDOLF_AID;
    
    @Override
    public void action() {
        switch (this.step) {
            case 0 -> {

                // Inicio de la conversacion con rudolf
                ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                msg.addReceiver(new AID(rudolf_AID, AID.ISLOCALNAME));
                msg.setConversationId(Globals.SECRET_CODE);
                myAgent.send(msg);
                this.step = 1;

            }

            case 1 -> {

                // Recibimos el mensaje
                ACLMessage msg = myAgent.blockingReceive();
                System.out.println(msg);
                
                // Procesamos el mensaje recibido
                if (msg.getPerformative() == ACLMessage.AGREE) {
                    
                    // Pasamos a pedirle las coordenadas de los renos en el siguiente paso
                    ACLMessage reply = msg.createReply(ACLMessage.REQUEST);
                    reply.setContent("¿Coordenadas?");
                    myAgent.send(reply);
                    
                    // Siguiente paso
                    this.step = 2;
                    
                } else if(msg.getPerformative() == ACLMessage.REFUSE) {
                    
                    System.out.println("Agent: Rudolf ha rechazado nuestro request");
                    myAgent.doDelete();
       
                } else {
                    // Error, terminamos la ejecucion
                    System.out.println("Agent: Error in the coversation protocol - step" + step);
                    myAgent.doDelete();
                }
            }
            
            case 2 -> {
                // Recibimos la respuesta al request de posicion reno
                ACLMessage msg = myAgent.blockingReceive();
                System.out.println(msg);
                
                if(msg.getPerformative() == ACLMessage.INFORM) {
                    // Procesamos el punto
                    String content = msg.getContent();
                    Point2D objetivo = Point2D.fromString(content);
                    
                    System.out.println("Llendo al objetivo... " + objetivo.toString());
                    
                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("No pude dormir");
                    }

                    System.out.println("He llegado a la posicion " + objetivo.toString());
                    
                    // Tras ir hacia el punto, vuelve a pedir uno nuevo
                    ACLMessage reply = msg.createReply(ACLMessage.REQUEST);
                    reply.setContent("¿Coordenadas?");
                    myAgent.send(reply);
                    
                } else if(msg.getPerformative() == ACLMessage.FAILURE) {
                    System.out.println("Agent: Ya tengo todos los renos, procedo a comunicarme con SANTA HO,HO,HO");
                    myAgent.doDelete();
                    
                } else {
                    // Error, terminamos la ejecucion
                    System.out.println("Agent: Error in the coversation protocol - step" + step);
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
