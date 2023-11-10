/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package behaviours;

import jade.core.behaviours.CyclicBehaviour;
import core.Agent203;

/**
 *
 * @author alberto
 */
public class UpdateSensorsBehaviour extends CyclicBehaviour{

    @Override
    public void action() {
        ((Agent203) myAgent).getEnvironment().see();
        
        //Cuando tengamos memoria, la actualizaremos aqui con el ultimo movimiento
    }
    
}
