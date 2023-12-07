/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;

import behaviours.SantaBehaviour;
import jade.core.Agent;
import java.util.Random;
/**
 *
 * @author manu
 */
public class Santa extends Agent { 
    private final String secretConversationID = Globals.SECRET_CODE;

    public String getSecretConversationID() {
        return secretConversationID;
    }
    
    @Override
    protected void setup(){
        this.addBehaviour(new SantaBehaviour());
    }
    
    public boolean isGoodAgent(){
        Random random = new Random();
        double probabilidad = random.nextDouble(); // Genera un número entre 0 y 1
        return probabilidad <= 0.8; // Eres digno si el número es menor o igual a 0.8
        // return true;
    }
    
}
