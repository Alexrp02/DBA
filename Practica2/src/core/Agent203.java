package core;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import jade.core.Agent;
import java.util.LinkedList;
import java.util.List;
import behaviours.*;

/**
 *
 * @author alexrp
 */
public class Agent203 extends Agent{
    
    // Entorno
    private Environment environment;
    private int nextDirection;
    private List<Point2D> agentPath;
    
    // Memoria del agente
    // ...
    
    @Override
    protected void setup() {
        // Inicializar aquí el entorno
        
        //Meter esta inicializacion en el InitializeBehaviour
        String mapPath = "./maps/mapWithoutObstacle.txt";
        Point2D initialPosition = new Point2D(0, 0);
        Point2D goalPosition = new Point2D(2, 2);
        
        agentPath = new LinkedList<>();

        this.environment = new Environment(mapPath, initialPosition, goalPosition);

        
        // Definir comportamientos
        // ...
        
        this.addBehaviour(new PrintBehaviour());
        System.out.println("Hola, estoy funcionando");
//        this.addBehaviour(new UpdateSensorsBehaviour());
//        this.addBehaviour(new EvaluateBehaviour());
//        this.addBehaviour(new MovementBehaviour());

    }
    
    public Environment getEnvironment(){
        return environment; 
    }
    
    public void setNextDirection(int next){
        this.nextDirection = next ;
    }
    
}
