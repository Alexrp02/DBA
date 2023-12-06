/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package behaviours;

import core.Point2D;
import core.Santa;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author manu
 */
public class ReceiveMessageSantaBehaviour extends Behaviour{
    
    private int step = 0;
    private boolean finish = false;

    @Override
    public void action() {
        switch (this.step) {
            case 0 -> {
                ACLMessage msg = myAgent.blockingReceive();
                System.out.println(msg);                
                
                if (msg.getPerformative() == ACLMessage.CFP) {
                    if(((Santa)this.myAgent).isGoodAgent()){
                        ACLMessage replay = msg.createReply(ACLMessage.AGREE);
                        System.out.println("Ha sido un agente bueno." );
                        this.myAgent.send(replay);
                        this.step = 1;
                    }else{
                        ACLMessage replay = msg.createReply(ACLMessage.REFUSE);
                        System.out.println("No ha sido un agente bueno." );
                        this.myAgent.send(replay);
                        this.finish = true;
                    }
                    
                } else {
                    ACLMessage replay = msg.createReply(ACLMessage.REFUSE);
                    this.myAgent.send(replay);
                    System.out.println("Error in the coversation protocol - step " + 1);
                    myAgent.doDelete();
                }
            }
            
          
            case 1 -> {
                ACLMessage msg = myAgent.blockingReceive();
                System.out.println(msg);

                if (msg.getPerformative() == ACLMessage.ACCEPT_PROPOSAL) {
                    ACLMessage replay = msg.createReply(ACLMessage.INFORM);
                    
                    replay.setContent(((Santa)this.myAgent).getCode());
                    this.myAgent.send(replay);
                    this.step = 2;
                    
                } else {
                    System.out.println("Error in the coversation protocol - step " + 2);
                    myAgent.doDelete();
                }
            }
            
            case 2 -> {
                ACLMessage msg = myAgent.blockingReceive();
                System.out.println(msg);

                if (msg.getPerformative() == ACLMessage.INFORM) {
                    ACLMessage replay = msg.createReply(ACLMessage.INFORM);
                    
                    System.out.println("HO! HO! HO! Feliz Navidad!");
                    
                    replay.setContent("HO! HO! HO! Feliz Navidad!");
                    this.myAgent.send(replay);
                    this.finish = true;
                    
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
