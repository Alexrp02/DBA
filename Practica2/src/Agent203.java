/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import jade.core.Agent;

/**
 *
 * @author alexrp
 */
public class Agent203 extends Agent{
    
    // Entorno
    private Environment environment;
    
    // Memoria del agente
    // ...
    
    @Override
    protected void setup() {
        // Inicializar aquí el entorno
        String path = "./maps/mapWithoutObstacle.txt";
        Point2D initialPosition = new Point2D(0, 0);
        Point2D goalPosition = new Point2D(2, 2);

        this.environment = new Environment(path, initialPosition, goalPosition);

        
        // Definir comportamientos
        // ...
    }
    
}
