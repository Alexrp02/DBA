/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package behaviours;
import core.Agent203;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
/**
 *
 * @author manu
 */
public class sendMessageToSantaBehaviour extends Behaviour{
     private int step = 0; 
    boolean first = true;
    boolean finish = false;
    String CONVERSATION_ID = "ConversationId";
    @Override
    public void action() {
        switch (this.step) {
            case 0 -> {
                
                ACLMessage msg = new ACLMessage(ACLMessage.CFP);
                msg.addReceiver(new AID("santa", AID.ISLOCALNAME));
                msg.setConversationId(CONVERSATION_ID);
                myAgent.send(msg);
                this.step = 1;
            }
            case 1 -> {
                ACLMessage msg = myAgent.blockingReceive();
                
                if (msg.getConversationId().equals(CONVERSATION_ID) &&
                 msg.getPerformative() == ACLMessage.AGREE) {
                    
                    ACLMessage replay = msg.createReply(ACLMessage.ACCEPT_PROPOSAL);
                    this.myAgent.send(replay);
                    this.step = 2;
                } else {
                    System.out.println("Sender:Error in the coversation protocol - step" + 1);
                    myAgent.doDelete();
                }
            }
            case 2 -> {
                ACLMessage msg = myAgent.blockingReceive();
                if (msg.getConversationId().equals(CONVERSATION_ID) &&
                    msg.getPerformative() == ACLMessage.INFORM) {
                    System.out.println("Agent receive: " + msg.getContent());
                    
                    
                    ((Agent203)this.myAgent).setRudolfCode(msg.getContent());
                    ((Agent203)this.myAgent).addSendRudolfBehaviour();
                    this.step = 3;
                    ((Agent203)this.myAgent).retrieveSendSantaBehaviour();
                    
                } else {
                    System.out.println("Error in the coversation protocol - step " + 2);
                    myAgent.doDelete();
                }
            }
            
            case 3 -> {
                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                msg.addReceiver(new AID("santa", AID.ISLOCALNAME));
                msg.setConversationId(CONVERSATION_ID);
                msg.setContent("Ya tengo todos los renos");
                myAgent.send(msg);
                this.step = 4;
            }
            case 4 -> {
                 ACLMessage msg = myAgent.blockingReceive();
                 
                 if (msg.getConversationId().equals(CONVERSATION_ID) &&
                    msg.getPerformative() == ACLMessage.INFORM){
                      System.out.println("Agent receive: " + msg.getContent());
                      ((Agent203) myAgent).getEnvironment().addAgentMessage("Agent receive: " + msg.getContent());
                      
                      this.finish = true;
                 }
                 else {
                    System.out.println("Error in the coversation protocol - step " + 2);
                    myAgent.doDelete();
                }
            }
            default -> {
                System.out.println("Error in the coversation protocol - step " +
                3);
                myAgent.doDelete();
            }
        }
    }

    @Override
    public boolean done() {
        return finish;
    }
    
}
