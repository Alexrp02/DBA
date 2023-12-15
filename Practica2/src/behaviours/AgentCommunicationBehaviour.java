/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package behaviours;

import core.Agent203;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

import core.Point2D;
import jade.core.AID;

/**
 *
 * @author manu
 */
public class AgentCommunicationBehaviour extends Behaviour {
    private int step = 0;
    private boolean finish = false;
    
    private ACLMessage santaChannelRef;    
    
    @Override
    public void action() {
        switch (this.step) {
            case 0 -> {

                // Inicio de la conversacion con santa
                
                // Obtenemos del agente el aid de santa
                String santaAID = ((Agent203) this.myAgent).getSantaAID();
                
                ACLMessage msg = new ACLMessage(ACLMessage.PROPOSE);
                msg.addReceiver(new AID(santaAID, AID.ISLOCALNAME));
                msg.setContent("¿Puedo ayudarte a encontrar tus renos?");
                myAgent.send(msg);
                this.step = 1;
            }

            case 1 -> {

                // Recibimos el mensaje de santa
                ACLMessage msg = myAgent.blockingReceive();
                System.out.println(msg);
                
                // Procesamos el mensaje recibido
                if (msg.getPerformative() == ACLMessage.ACCEPT_PROPOSAL) {
                    
                    // Obtenemos el codigo secreto del content
                    String secretCode = msg.getContent();
                    ((Agent203) this.myAgent).setSecretConversationID(secretCode);
                    
                    // Guardamos el canal de comunicación con Santa para el final de la misión
                    santaChannelRef = msg;

                    // Pasamos a la comunicación con Rudolf (step = 2)
                    this.step = 2;

                } else if(msg.getPerformative() == ACLMessage.REJECT_PROPOSAL) {
                    System.out.println("Santa no me quiere");
                } else {
                    System.out.println("Error");
                }
            }
            
            case 2 -> { // Inicio de la comunicacion con Rudolf
                // Inicio de la conversacion con rudolf
                
                // Obtenemos el AID de rudolf del agente
                String rudolfAID = ((Agent203) this.myAgent).getRudolfAID();
                String secretCode = ((Agent203) this.myAgent).getSecretConversationID();
                
                ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                msg.addReceiver(new AID(rudolfAID, AID.ISLOCALNAME));
                msg.setConversationId(secretCode);
                myAgent.send(msg);
                this.step = 3;
            }
            
            case 3 -> {

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
                    this.step = 4;
                    
                } else if(msg.getPerformative() == ACLMessage.REFUSE) {
                    
                    System.out.println("Agent: Rudolf ha rechazado nuestro request");
                    myAgent.doDelete();
       
                } else {
                    // Error, terminamos la ejecucion
                    System.out.println("Agent: Error in the coversation protocol - step" + step);
                    myAgent.doDelete();
                }              
            }
            
            case 4 -> {
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
                    System.out.println("Agent: Vuelvo a comunicarme con Santa");
                    this.step = 5;
                    
                } else {
                    // Error, terminamos la ejecucion
                    System.out.println("Agent: Error in the coversation protocol - step" + step);
                    myAgent.doDelete();
                }               
            }
            
            case 5 -> { // Vuelta a la comunicación con Santa
                // Pregunta coordenadas a santa (POR EL MISMO CANAL)
                ACLMessage reply = santaChannelRef.createReply();
                reply.setPerformative(ACLMessage.REQUEST);
                reply.setContent("¿Coordenadas Santa?");
                myAgent.send(reply);
                this.step = 6;
                
            }
            
            case 6 -> {
                // Recibimos las coordenadas de Santa
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
                    ACLMessage reply = msg.createReply(ACLMessage.INFORM);
                    reply.setContent("He llegado a Santa con los renos.");
                    myAgent.send(reply);
                    this.step = 7;
                    
                } else {
                    System.out.println("Error en el protocolo");
                    myAgent.doDelete();
                }
                
            }
            
            case 7 -> {
                // Espera a recibir el HoHoHo antes de irse
                ACLMessage msg = myAgent.blockingReceive();
                System.out.println(msg);
                
                if(msg.getPerformative() == ACLMessage.INFORM) {                    
                    System.out.println("Mision cumplida");
                    finish = true;
                } else {
                    System.out.println("Error en el protocolo");
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
