/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package behaviours;
import core.Agent203;
import core.Point2D;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author manu
 */
public class sendMessageToRudolfBehaviour extends Behaviour{
    private int step = 0; 
    boolean first = true;
    boolean finish = false;
    String CONVERSATION_ID = "ConversationId";
    @Override
    public void action() {
        switch (this.step) {
            case 0 -> {
                
                
                ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                msg.addReceiver(new AID("agentReceiver", AID.ISLOCALNAME));
                msg.setConversationId(CONVERSATION_ID);
                
                msg.setLanguage(((Agent203)this.myAgent).getRudolfCode());
                myAgent.send(msg);
                this.step = 1;
            }
            case 1 -> {
                ACLMessage msg = myAgent.blockingReceive();
                if (msg.getConversationId().equals(CONVERSATION_ID) &&
                 msg.getPerformative() == ACLMessage.AGREE) {
                    
                    ACLMessage replay = msg.createReply(ACLMessage.INFORM);
                    replay.setContent("Send me new reinder coordinates!");
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
                    ((Agent203) myAgent).getEnvironment().addAgentMessage("Agent receive: " + msg.getContent());
                    String content = msg.getContent();
                    
                    if(!content.equals("")){
                        String[] coordinatesArray = content.split(",");

                        Point2D goalPosition = new Point2D(
                                Integer.parseInt(coordinatesArray[0].trim()),
                                Integer.parseInt(coordinatesArray[1].trim())
                        );

                        ((Agent203)this.myAgent).setGoalPosition(goalPosition);
                        ((Agent203)this.myAgent).startSearchingReindeer();
                        
                        
                        
                        this.step = 0;
                        
                    }else{
                        System.out.println("Rudolf dice que no hay más renos");
                        ((Agent203)this.myAgent).addSendSantaBehaviour();
                        finish = true;
                    }
                        
                    
                } else {
                    System.out.println("Error in the coversation protocol - step " + 2);
                    myAgent.doDelete();
                }
            }
            default -> {
                System.out.println("Error in the coversation protocol - step " +
                2);
                myAgent.doDelete();
            }
        }
    }

    @Override
    public boolean done() {
        return finish;
    }
    
}
