/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package behaviours;

import core.Agent203;
import jade.core.behaviours.Behaviour;

/**
 *
 * @author alexrp
 */
public class checkGoalBehaviour extends Behaviour {

    @Override
    public void action() {
    }

    @Override
    public boolean done() {

        boolean end
                = ((Agent203) this.myAgent).getEnvironment().getCurrentPosition().equals(
                        ((Agent203) this.myAgent).getEnvironment().getGoalPosition()
                );

        if (end) {
            ((Agent203) this.myAgent).doDelete();
            System.out.println("The agent is in the goal after " + ((Agent203) this.myAgent).steps + " steps!!");
        }

        return end;
    }

}
