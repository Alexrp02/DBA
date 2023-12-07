package core;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import jade.core.Agent;
import behaviours.AgentBehaviour; 

/**
 *
 * @author carlosqp
 */
public class Agent203_P3 extends Agent{
    
    
    // Sólo comunicacion
    private final String Rudolf_AID = Globals.RUDOLF_AID;
    private final String Santa_AID = Globals.SANTA_AID;
    
    private String secretConversationID = "¿?";

    public String getRudolfAID() {
        return Rudolf_AID;
    }
    
    public String getSantaAID() {
        return Santa_AID;
    }
    
    public void setSecretConversationID(String secretConversationID) {
        this.secretConversationID = secretConversationID;
    }

    public String getSecretConversationID() {
        return secretConversationID;
    }
    
    @Override
    protected void setup() {
        this.addBehaviour(new AgentBehaviour());   
    }

}
