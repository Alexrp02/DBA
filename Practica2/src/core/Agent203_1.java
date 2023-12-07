package core;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import jade.core.Agent;
import java.util.LinkedList;
import java.util.List;
import behaviours.*;
import jade.core.behaviours.Behaviour;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author alexrp
 */
public class Agent203_1 extends Agent{
    
    
    // Sólo comunicacion
    private final String Rudolf_AID = Globals.RUDOLF_AID;
    private final String Santa_AID = Globals.SANTA_AID;
    
    private String secretConversationID = "¿?";
    
    public String getRudolfAID() {
        return Rudolf_AID;
    }
    
    @Override
    protected void setup() {
        this.addBehaviour(new Agent203Behaviour());
        
    }
       
    
}
