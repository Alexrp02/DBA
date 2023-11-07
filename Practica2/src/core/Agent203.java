/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import jade.core.Agent;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author alexrp
 */
public class Agent203 extends Agent{
    
    // Entorno
    private Environment environment;
    private Direction nextDirection;
    private List<Point2D> agentPath;
    
    // Memoria del agente
    // ...
    
    @Override
    protected void setup() {
        // Inicializar aquí el entorno
        String mapPath = "./maps/mapWithoutObstacle.txt";
        Point2D initialPosition = new Point2D(0, 0);
        Point2D goalPosition = new Point2D(2, 2);
        
        agentPath = new LinkedList<>();

        this.environment = new Environment(mapPath, initialPosition, goalPosition);

        
        // Definir comportamientos
        // ...
    }
    
}
