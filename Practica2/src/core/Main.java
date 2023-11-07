/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author carlosqp
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("¡Hola, Mundo!");
        System.out.println("Dirección: " + Direction.NORTH);
      
        // Inicializar el entorno
        String path = "./maps/mapWithHorizontalWall.txt";
        Point2D initialPosition = new Point2D(0, 0); // Coordenadas iniciales
        Point2D goalPosition = new Point2D(2, 2); // Coordenadas de la meta

        Environment environment = new Environment(path, initialPosition, goalPosition);

        // Imprimir el entorno
        environment.print();
        
        environment.moveEast();
        environment.print();
        
        environment.moveSouth();
        environment.print();
        
        environment.moveWest();
        environment.print();
        
        environment.moveNorth();
        environment.print();
        
        environment.move(Direction.SOUTH);
        environment.print();
    }
}


//// Ejemplo de ejecución del agente desde el main (ver guión P1)
//import jade.core.Profile;
//import jade.core.ProfileImpl;
//import jade.wrapper.AgentController;
//import jade.wrapper.ContainerController;
//import jade.wrapper.StaleProxyException;
//
//public class Main {
//    public static void main(String[] args) throws StaleProxyException {
//        /// Obtenemos instancia del entorno de ejecución (jade.core.Runtime)
//        jade.core.Runtime rt = jade.core.Runtime.instance();
//        
//        /// Creamos un contenedor de agentes 
//        // Utilizamos la clase Profile para especificar los parámetros del contenedor.
//        Profile p = new ProfileImpl();
//        
//        String host = "localhost";
//        p.setParameter(Profile.MAIN_HOST, host);    // por defecto, host = localhost
//
//        String containerName = "Container-Practica2";
//        p.setParameter(Profile.CONTAINER_NAME, containerName);
//        
//        // Creamos el controlador del contenedor con los parametros que queremos
//        ContainerController cc = rt.createAgentContainer(p);
//        
//        /// Creamos el agente y comenzamos su ejecución
//
//        String agentName = "agent203";
//        String className = Agent203.class.getCanonicalName();
//                
//        AgentController ac = cc.createNewAgent(agentName, className, null);
//        
//        ac.start();        
//    }
//}
