package core;

// Ejemplo de ejecución del agente desde el main (ver guión P1)
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import java.util.ArrayList;
import java.util.List;

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

        String agentReceiverName = "agentReceiver";
        String classReceiverName = Rudolf.class.getCanonicalName();

        String santaName = "santa";
        String classSantaName = Santa.class.getCanonicalName();

        String mapPath = "./maps/mapa30.txt";
        Point2D initialPosition = new Point2D(0, 0);

        List<Point2D> agentPath = new ArrayList<>();
        Environment environment = new Environment(mapPath, initialPosition, agentPath);
        Object[] agentArgs = new Object[2] ;
        agentArgs[0] = environment ;
        agentArgs[1] = agentPath;

        try {
            //AgentController asc = cc.createNewAgent(agentSenderName, classSenderName, null);
            AgentController arc = cc.createNewAgent(agentReceiverName, classReceiverName, agentArgs);
            AgentController ac = cc.createNewAgent(agentName, className, agentArgs);
            AgentController asc = cc.createNewAgent(santaName, classSantaName, agentArgs);

            ac.start();
            asc.start();
            arc.start();

        } catch (StaleProxyException e) {
            e.printStackTrace(); // Imprime la traza de la excepción para diagnóstico
            // También puedes manejar la excepción de otras formas según tus necesidades
        }
    }
}
