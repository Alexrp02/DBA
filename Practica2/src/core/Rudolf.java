/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;

import jade.core.Agent;
// import behaviours.receiveMessageRudolfBehaviour;
import behaviours.RudolfBehaviour;
import java.util.ArrayList;
/**
 *
 * @author manu
 */
public class Rudolf extends Agent{
    // private String secretConversationID = "ENCUENTRA_RENOS";
    
    // PIN secreto de la conversacion
    private final String secretConversationID = Globals.SECRET_CODE;

    // Lista de posiciones de renos perdidos por el mapa
    public ArrayList<Point2D> reindeerPositions;
    
     @Override
    protected void setup(){
        // Inicializacion de estructuras
        reindeerPositions = new ArrayList<>();
        
        // Setup de posiciones de renos
        reindeerPositions.add( new Point2D(29,29));
        reindeerPositions.add( new Point2D(15,15));
        
        // this.addBehaviour(new receiveMessageRudolfBehaviour());
        this.addBehaviour(new RudolfBehaviour());

    }
    
   public String getSecretConversationID(){
       return secretConversationID;
   }
    
}
