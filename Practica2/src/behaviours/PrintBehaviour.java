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
public class PrintBehaviour extends CyclicBehaviour{

    @Override
    public void action() {
        ((Agent203) this.myAgent).getEnvironment().print();
    }
    
}
