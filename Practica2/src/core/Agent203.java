package core;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import jade.core.Agent;
import java.util.LinkedList;
import java.util.List;
import behaviours.*;
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
    
    // Para pintar por donde va pasando el agente
    private List<Point2D> agentPath;
    
    // Memoria del agente
    public Map<Point2D, Integer> memory;
    public List<Integer> sensorsWeight;
    
    @Override
    protected void setup() {
        // Inicializar aquí el entorno
        
//        String mapPath = "./maps/mapWithVerticalWall.txt";
//        Point2D initialPosition = new Point2D(0, 0);
//        Point2D goalPosition = new Point2D(5, 5);
        
//        String mapPath = "./maps/mapWithComplexObstacle1.txt";
//        Point2D initialPosition = new Point2D(5, 6);
//        Point2D goalPosition = new Point2D(8, 0);
        
//        String mapPath = "./maps/mapWithComplexObstacle3_1.txt";
//        Point2D initialPosition = new Point2D(8, 8);
//        Point2D goalPosition = new Point2D(8, 0);

        String mapPath = "./maps/mapMuerte.txt";
        Point2D initialPosition = new Point2D(9, 9);
        Point2D goalPosition = new Point2D(9, 4);
        
        
        agentPath = new LinkedList<>();
        
        // initialization of sensorsWeight
        sensorsWeight = new ArrayList<>(4);
        //sensorsWeight.addAll(Arrays.asList(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE));
        for (int i = 0; i < 4; i++) {
            sensorsWeight.add(Integer.MAX_VALUE);
        }
        
        this.environment = new Environment(mapPath, initialPosition, goalPosition);

        memory = new HashMap<>();
        // Establecemos el minimo valor a la posicion de goal para que siempre se escoja este movimiento
        memory.put(goalPosition, -100);

        // Definir comportamientos
        // ...
        
        this.addBehaviour(new PrintBehaviour());
        this.addBehaviour(new checkGoalBehaviour()) ;
        this.addBehaviour(new UpdateMemoryBehaviour());
        this.addBehaviour(new EvaluateBehaviourByWeight());
        // this.addBehaviour(new EvaluateBehaviour());
        this.addBehaviour(new MovementBehaviour());

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
    
}
