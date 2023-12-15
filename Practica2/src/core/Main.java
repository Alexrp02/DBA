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

        String agentName = Globals.AGENT203_AID;
        String className = Agent203.class.getCanonicalName();
        
        String rudolfName = Globals.RUDOLF_AID;
        String classRudolfName = Rudolf.class.getCanonicalName();
        
        String santaName = Globals.SANTA_AID;
        String classSantaName = Santa.class.getCanonicalName();
        
        
        try {
        AgentController arc = cc.createNewAgent(rudolfName, classRudolfName, null);
        AgentController ac = cc.createNewAgent(agentName, className, null);
        AgentController asc = cc.createNewAgent(santaName, classSantaName, null);
        
        ac.start(); 
        asc.start();
        arc.start();
        
        } catch (StaleProxyException e) {
            e.printStackTrace(); // Imprime la traza de la excepción para diagnóstico
            // También puedes manejar la excepción de otras formas según tus necesidades
        }
    }
}