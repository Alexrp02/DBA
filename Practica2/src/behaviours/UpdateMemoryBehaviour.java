/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package behaviours;

import jade.core.behaviours.CyclicBehaviour;
import core.Agent203;
import core.Direction;
import core.Point2D;

/**
 *
 * @author carlosqp
 */
public class UpdateMemoryBehaviour extends CyclicBehaviour{
    
    private static final int PENALIZACION_POR_PASO = 5;
    private static final int PENALIZACION_POR_MURO = Integer.MAX_VALUE;
    
    Point2D currentPosition;
    Point2D goalPosition;
    
    public int manhattanDistance(Point2D start, Point2D end) {
        Point2D substract = start.substract(end);
        int result = Math.abs(substract.i) + Math.abs(substract.j);
        return result;
    }
    
    @Override
    public void action() {
        
        // Actualiza los sensores, y las variables que dependen de ellos
        updateSensors();
        
        // Actualizar aquí la memoria
        updateMemory();

        /// Actualizamos el peso de la casilla en la que estamos
        updateSensorsWeight();
    }
    
    private void updateSensors() {
        ((Agent203) myAgent).getEnvironment().see();
        goalPosition = ((Agent203) this.myAgent).getEnvironment().getGoalPosition();
        Point2D ref = ((Agent203) this.myAgent).getEnvironment().getCurrentPosition();
        currentPosition = new Point2D(ref.i, ref.j);
    }
    
   private void updateMemory() {
        ///////////////////////////////////////////////////////////////////////
        /// Actualizamos el peso de la casilla en la que estamos (le sumamos 5)
        // System.out.println("Stop me here");
        
        // Si no contiene esa casilla, la agrega (sólo ocurrirá en la primera iteración)
        if(!((Agent203) myAgent).memory.containsKey(currentPosition)) {
            ((Agent203) myAgent).memory.put(currentPosition, manhattanDistance(currentPosition, goalPosition));
        }
        
        // Si la casilla actual está en la memoria (debería estarlo), actualiza su peso sumándole 5
        ((Agent203) myAgent).memory.put(currentPosition, ((Agent203) myAgent).memory.get(currentPosition) + PENALIZACION_POR_PASO);
        // System.out.println("UpdateSensorsBehaviour: Actualizado el punto donde estoy... " + ((Agent203) myAgent).memory.get(currentPosition));

        ///////////////////////////////////////////////////////////////////////
        /// Actualizamos el peso de las casillas que observa el agente por sensores (sólo si no están)
        
       for (Point2D possibleMove : Direction.possibleMoves) {
           
            // Obtiene el punto del mundo
            Point2D worldPoint = currentPosition.add(possibleMove);
            
            // Obtiene el indice de la direccion
            int sensorIndex = Direction.possibleMoves.indexOf(possibleMove);

            // Comprueba si el punto está ya en la memoria. 
            //  - Si el punto ya existe en la memoria, no lo procesa.
            //  - Si el punto está en la memoria, lo procesa.

            if(!((Agent203) myAgent).memory.containsKey(worldPoint)) {
                
                int sensorValue = ((Agent203) myAgent).getEnvironment().getSensors().get(sensorIndex);
                
                switch (sensorValue) {
                    // case -2 -> System.out.println("UpdateSensorsBehaviour: Fuera del mapa");
                    case -1 -> {
                        // System.out.println("UpdateSensorsBehaviour: Añadiendo un muro en la memoria");
                        ((Agent203) myAgent).memory.put(worldPoint, PENALIZACION_POR_MURO);
                    }
                    case 0 -> {
                        // System.out.println("UpdateSensorsBehaviour: Añadiendo nuevo punto valido");
                        ((Agent203) myAgent).memory.put(worldPoint, manhattanDistance(worldPoint, goalPosition));
                    }
                    default -> {
                    }
                }
                // Comprueba si está fuera del mapa, en tal caso no añade el punto a la memoria.
                // Comprueba si es muro, en tal caso añade el punto a la memoria con un valor máximo para que nunca sea escogido
                // En caso de que sea un punto al que se puede mover, se añade a la memoria con el valor de la distancia manhattan
            }
        }
       
       // System.out.println("UpdateSensorsBehaviour: memory actualizada");
   }
   
   // Debe ser llamada después de updateMemory puesto que depende de memory actualizada
   private void updateSensorsWeight() {
       
       for (Point2D possibleMove : Direction.possibleMoves) {
           
            // Obtiene el punto del mundo
            Point2D worldPoint = currentPosition.add(possibleMove);
            
            // Obtiene el indice de la direccion
            int sensorIndex = Direction.possibleMoves.indexOf(possibleMove);
            
            // Obtiene el valor del sensor
            int sensorValue = ((Agent203) myAgent).getEnvironment().getSensors().get(sensorIndex);
            
            // Actualiza el valor de sensorWeight(sensorIndex) con su valor de la memoria
            // En caso de que el punto no esté en el mapa (sensorValue==-2) entonces le da el maximo valor
            int sensorWeight;
            if(sensorValue==-2) {
                sensorWeight = PENALIZACION_POR_MURO;
            } else {
                sensorWeight = ((Agent203) myAgent).memory.get(worldPoint);
                // System.out.println("Direction: " + sensorIndex + ", Weight: " + sensorWeight);
            }
            
            ((Agent203) myAgent).sensorsWeight.set(sensorIndex, sensorWeight);
       }
       
       // System.out.println("UpdateSensorsBehaviour: sensorsWeight actualizada");
   }
}
