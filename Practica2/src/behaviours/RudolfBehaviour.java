/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package behaviours;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
// import jade.core.AID;

import core.Rudolf;
import core.Point2D;
//import core.Globals;    // Para los IDs y el código secreto

/**
 *
 * @author manu
 */
public class RudolfBehaviour extends Behaviour{
    private int step = 0;
    private boolean finish = false;
    
    private int posiciones_enviadas = 0;
   
    
    @Override
    public void action() {
        switch (this.step) {
            case 0 -> {
                ACLMessage msg = myAgent.blockingReceive();
                System.out.println(msg);
                
                String msgConversationID = msg.getConversationId();
                String rudolfCode = ((Rudolf)this.myAgent).getCode();
                
                ACLMessage reply = msg.createReply();
                
                // ¿¿Request o CFP??
                if (msg.getPerformative() == ACLMessage.REQUEST && msgConversationID.equals(rudolfCode)) {
                    reply.setPerformative(ACLMessage.AGREE);
                    this.myAgent.send(reply);
                    this.step = 1;
                } else {
                    reply.setPerformative(ACLMessage.REFUSE);
                    this.myAgent.send(reply);
                    System.out.println("Error in the coversation protocol - step " + 1);
                    myAgent.doDelete();
                }
            }
            
            
            case 1 -> {
                
                // Recibimos el mensaje
                ACLMessage msg = myAgent.blockingReceive();
                System.out.println(msg);

                // Procesamos el mensaje recibido
                if (msg.getPerformative() == ACLMessage.REQUEST) {                    

                    // Obtenemos el total de renos de Rudolf
                    final int TOTAL_RENOS = ((Rudolf)this.myAgent).reindeerPositions.size();
                    
                    // Creamos la respuesta
                    ACLMessage reply = msg.createReply();
                    
                    // Si quedan posiciones aún por enviar
                    if(posiciones_enviadas < TOTAL_RENOS) {
                        
                        // Obtenemos la posición que corresponde
                        String reindeerPosition = ((Rudolf)this.myAgent).reindeerPositions.get(posiciones_enviadas).toString();
                        // Incrementamos para la siguiente
                        posiciones_enviadas++;
                        
                        // Configuramos la respuesta
                        reply.setPerformative(ACLMessage.INFORM);
                        reply.setContent(reindeerPosition);
                        
                        // Enviamos la respuesta
                        this.myAgent.send(reply);
                        // this.step = 0; ¿?
                        
                    } else {
                        reply.setPerformative(ACLMessage.FAILURE);
                        reply.setContent("¡No tengo má posiciones de renos!");
                        this.myAgent.send(reply);
                        this.finish = true;
                    }
                    
                } else {
                    System.out.println("Error in the coversation protocol - step " + 2);
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
