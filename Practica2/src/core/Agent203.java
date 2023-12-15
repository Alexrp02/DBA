package core;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import jade.core.Agent;
import behaviours.AgentCommunicationBehaviour; 
import java.util.List;
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
 * @author carlosqp
 */
public class Agent203 extends Agent{
    
    
    // Sólo comunicacion
    private final String Rudolf_AID = Globals.RUDOLF_AID;
    private final String Santa_AID = Globals.SANTA_AID;
    
    private String secretConversationID = "¿?";
    
    // Entorno
    private Environment environment;
    private int nextDirection;
    
    // Para pintar por donde va pasando el agente
    private List<Point2D> agentPath;
    
    // Memoria del agente
    public Map<Point2D, Double> memory;
    public List<Double> sensorsWeight;
    
    //Variable para los pasos de ejecución
    public int steps = 0;
    
    private boolean movement = false;
    
        
    String mapPath = "./maps/mapa30.txt";

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
    
        
    public void resetMemory() {
    
    }
    
    
    @Override
    protected void setup() {
        
        
        Point2D initialPosition = new Point2D(0, 0);

        this.agentPath = new ArrayList<Point2D>();
        this.environment = new Environment(mapPath, initialPosition , agentPath);
        
        this.addBehaviour(new AgentCommunicationBehaviour());  
        
        //this.addBehaviour(new PrintBehaviour());
        //this.addBehaviour(new checkGoalBehaviour()) ;
        //this.addBehaviour(new UpdateMemoryBehaviour());
        //this.addBehaviour(new EvaluateBehaviourByWeight());
        //this.addBehaviour(new MovementBehaviour());
    }

    
    public Environment getEnvironment(){
        return environment; 
    }
    
    public void setNextDirection(int next){
        this.nextDirection = next ;
    }
    
    public int getNextDirection(){
        return this.nextDirection;
    }
    
    public List<Point2D> getAgentPath(){
        return this.agentPath;
    };
    
    // Añadir 
    public void setGoalPosition(Point2D goalPosition){
        this.environment.setGoalPosition(goalPosition);
    }
}
