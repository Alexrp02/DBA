package ui;
import java.util.List;
import core.Map;
import javax.swing.*;
import java.awt.*;
import core.Point2D;

public class MapVisualization extends JFrame {
    private int CELL_SIZE = 20 ;
    private Map worldMap;
    private  Point2D goalPosition;
    private Point2D agentPosition;
    private MapPanel mapPanel;
    private List<Point2D> agentPath;

    public MapVisualization(Map worldMap, Point2D agentPosition, List<Point2D> agentPath) {
        this.worldMap = worldMap;
        this.agentPosition = agentPosition;
        this.agentPath = agentPath;

        setTitle("Map Visualization");
        setSize(CELL_SIZE*worldMap.getRows(), CELL_SIZE*worldMap.getCols());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mapPanel = new MapPanel();
        add(mapPanel);

        setVisible(true);
    }
    
    public void setGoalPosition(Point2D goalPosition){
        this.goalPosition = goalPosition;
    }

    // Add this method to update the visualization
    public void updateVisualization(Map worldMap, Point2D agentPosition) {
        MapVisualization.this.worldMap = worldMap;
        MapVisualization.this.agentPosition = agentPosition;
        mapPanel.repaint(); // Trigger repaint to update the visualization
    }

    class MapPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int cellSize = CELL_SIZE; // Adjust this based on your preference

            for (int i = 0; i < worldMap.getRows(); i++) {
                for (int j = 0; j < worldMap.getCols(); j++) {
                    if (worldMap.get(i, j) == -1) {
                        g.setColor(Color.BLACK); // Wall
                    } else {
                        g.setColor(Color.CYAN); // Walkable area
                    }
                    g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                }
            }

            g.setColor(Color.GREEN); // Goal position
            g.fillOval(goalPosition.j * cellSize, goalPosition.i * cellSize, cellSize, cellSize);

            g.setColor(Color.RED); // Agent position
            g.fillOval(agentPosition.j * cellSize, agentPosition.i * cellSize, cellSize, cellSize);
            
            //Ruta del agente
            if (agentPath != null) {
                g.setColor(Color.YELLOW); // Color amarillo para la ruta del agente
                
                
                for (Point2D pathPoint : agentPath) {
                    g.fillOval(pathPoint.j * cellSize, pathPoint.i * cellSize, cellSize/2, cellSize/2);
                }
                
            }
        }
    }
}
