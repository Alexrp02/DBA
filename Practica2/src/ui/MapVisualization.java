package ui;

import core.Map;
import javax.swing.*;
import java.awt.*;
import core.Point2D;

public class MapVisualization extends JFrame {

    private Map worldMap;
    private final Point2D goalPosition;
    private Point2D agentPosition;
    private MapPanel mapPanel;

    public MapVisualization(Map worldMap, Point2D goalPosition, Point2D agentPosition) {
        this.worldMap = worldMap;
        this.goalPosition = goalPosition;
        this.agentPosition = agentPosition;

        setTitle("Map Visualization");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mapPanel = new MapPanel();
        add(mapPanel);

        setVisible(true);
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

            int cellSize = 40; // Adjust this based on your preference

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
        }
    }
}
