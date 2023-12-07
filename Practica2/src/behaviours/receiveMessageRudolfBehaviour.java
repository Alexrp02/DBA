/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package behaviours;
import core.Rudolf;
import core.Point2D;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.core.AID;
/**
 *
 * @author manu
 */
public class receiveMessageRudolfBehaviour extends Behaviour{
    private int step = 0;
    private boolean finish = false;
   
    
    @Override
    public void action() {
        switch (this.step) {
            case 0 -> {
                ACLMessage msg = myAgent.blockingReceive();
                System.out.println(msg);
                
                String message_code = msg.getLanguage();
                
                
                if (msg.getPerformative() == ACLMessage.REQUEST && message_code == ((Rudolf)this.myAgent).getSecretConversationID()) {
                    ACLMessage replay = msg.createReply(ACLMessage.AGREE);
                    this.myAgent.send(replay);
                    this.step = 1;
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

                if (msg.getPerformative() == ACLMessage.INFORM) {
                    ACLMessage replay = msg.createReply(ACLMessage.INFORM);
                    
                    if(((Rudolf)this.myAgent).reindeerPositions.size() != 0){
                        Point2D reindeerPosition = ((Rudolf)this.myAgent).reindeerPositions.get(0);
                        
                        ((Rudolf)this.myAgent).reindeerPositions.remove(0);
                     
                        
                        replay.setContent(reindeerPosition.i+","+reindeerPosition.j);
                        this.myAgent.send(replay);
                        this.step = 0;
                        
                    }else{
                        replay.setContent("");
                        this.myAgent.send(replay);
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
