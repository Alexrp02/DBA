package core;

// Ejemplo de ejecución del agente desde el main (ver guión P1)
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;


// Antes de ejecutar el programa, lanzar otro proceso:
// java -cp jade.jar jade.Boot -name dba_server -gui


public class Main {
    public static void main(String[] args) throws StaleProxyException {
        /// Obtenemos instancia del entorno de ejecución (jade.core.Runtime)
        jade.core.Runtime rt = jade.core.Runtime.instance();
        
        /// Creamos un contenedor de agentes 
        // Utilizamos la clase Profile para especificar los parámetros del contenedor.
        Profile p = new ProfileImpl();
        
        String host = "localhost";
        p.setParameter(Profile.MAIN_HOST, host);    // por defecto, host = localhost

        String containerName = "Container-Practica2";
        p.setParameter(Profile.CONTAINER_NAME, containerName);
        
        // Creamos el controlador del contenedor con los parametros que queremos
        ContainerController cc = rt.createAgentContainer(p);
        
        /// Creamos el agente y comenzamos su ejecución

        String agentName = "agent203";
        String className = Agent203.class.getCanonicalName();
                
        AgentController ac = cc.createNewAgent(agentName, className, null);
        
        ac.start();        
    }
}