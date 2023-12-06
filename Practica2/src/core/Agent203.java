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
public class Agent203 extends Agent{
    
    // Entorno
    private Environment environment;
    private int nextDirection;
    private String codeRudolf = "";
    
    // Para pintar por donde va pasando el agente
    private List<Point2D> agentPath;
    
    // Memoria del agente
    public Map<Point2D, Double> memory;
    public List<Double> sensorsWeight;
    
    //Variable para los pasos de ejecución
    public int steps = 0;
    
    
    //Comportamiento para el envio de mensajes
    private sendMessageToRudolfBehaviour sendRudolfBehaviour;
    
    //Comportamientos para la búsqueda de renos
    private PrintBehaviour printBehaviour;
    private checkGoalBehaviour checkGoalBehaviour ;
    private UpdateMemoryBehaviour updateMemoryBehaviour;
    private EvaluateBehaviourByWeight evaluateByWeight;
    private MovementBehaviour movementBehaviour;
    
    //Comportamientos para hablar con santa
    private sendMessageToSantaBehaviour sendSantaBehaviour;
    
        
    String mapPath = "./maps/mapa30.txt";
    
    @Override
    protected void setup() {
        // Inicializar aquí el entorno
        
//        String mapPath = "./maps/mapWithVerticalWall.txt";
         Point2D initialPosition = new Point2D(0, 0);
//        Point2D goalPosition = new Point2D(5, 5);
        
//        String mapPath = "./maps/mapWithComplexObstacle1.txt";
//        Point2D initialPosition = new Point2D(5, 6);
//        Point2D goalPosition = new Point2D(8, 0);
        
       
        //Point2D goalPosition = new Point2D(9, 4);

//        String mapPath = "./maps/mapMuerte.txt";
//        Point2D initialPosition = new Point2D(9, 9);
//        Point2D goalPosition = new Point2D(9, 4);
        this.agentPath = new ArrayList<Point2D>();
        this.environment = new Environment(mapPath, initialPosition , agentPath);
        
        
        // Definir comportamientos
        // ...
        
        sendRudolfBehaviour = new sendMessageToRudolfBehaviour();
        sendSantaBehaviour = new sendMessageToSantaBehaviour();
        
        printBehaviour = new PrintBehaviour();
        checkGoalBehaviour = new checkGoalBehaviour();
        updateMemoryBehaviour = new UpdateMemoryBehaviour();
        evaluateByWeight = new EvaluateBehaviourByWeight();
        movementBehaviour = new MovementBehaviour();
        
        this.addSendSantaBehaviour();

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
    
    public void setGoalPosition(Point2D goalPosition){
        this.environment.setGoalPosition(goalPosition);
    }
    
    public void startSearchingReindeer(){
        this.retrieveSendRudolfBehaviour();
        agentPath = new LinkedList<>();
        
        
        // initialization of sensorsWeight
        sensorsWeight = new ArrayList<>(8);
        //sensorsWeight.addAll(Arrays.asList(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE));
        for (int i = 0; i < 8; i++) {
            sensorsWeight.add(Double.MAX_VALUE);
        }
        
        

        memory = new HashMap<>();
        // Establecemos el minimo valor a la posicion de goal para que siempre se escoja este movimiento
        memory.put(this.environment.getGoalPosition(), -100.0);

        this.addBehaviour(this.printBehaviour);
        this.addBehaviour(this.checkGoalBehaviour) ;
        this.addBehaviour(this.updateMemoryBehaviour);
        this.addBehaviour(this.evaluateByWeight);
        // this.addBehaviour(new EvaluateBehaviour());
        this.addBehaviour(this.movementBehaviour);
    }
    
    public void finishSearchingReindeer(){
         this.removeBehaviour(this.printBehaviour);
        //this.removeBehaviour(this.checkGoalBehaviour) ;
        this.removeBehaviour(this.updateMemoryBehaviour);
        //this.removeBehaviour(this.evaluateByWeight);
        // this.addBehaviour(new EvaluateBehaviour());
        this.removeBehaviour(this.movementBehaviour);
    }
    public void addSendRudolfBehaviour(){
        this.addBehaviour(sendRudolfBehaviour);
    }
    
    public void retrieveSendRudolfBehaviour(){
        this.removeBehaviour(sendRudolfBehaviour);
    }
    
    public String getRudolfCode(){
        return this.codeRudolf;
    }
    
    public void setRudolfCode(String code){
        this.codeRudolf = code;
    }
    
    public void addSendSantaBehaviour(){
        this.addBehaviour(sendSantaBehaviour);
    }
    
    public void retrieveSendSantaBehaviour(){
        this.removeBehaviour(sendSantaBehaviour);
    }
}
