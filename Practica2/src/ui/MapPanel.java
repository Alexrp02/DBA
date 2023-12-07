/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import core.Map;
import core.Point2D;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author alexrp
 */
public class MapPanel extends JPanel {

    private int CELL_SIZE = 20;
    private Map worldMap;
    private Point2D goalPosition;
    private Point2D agentPosition;
    private List<Point2D> agentPath;

    public void initializeVariables(Map worldMap, Point2D agentPosition, List<Point2D> agentPath) {
        this.worldMap = worldMap;
        this.agentPosition = agentPosition;
        this.agentPath = agentPath;
//        setSize(CELL_SIZE * worldMap.getRows(), CELL_SIZE * worldMap.getCols());
    }

    public void setGoalPosition(Point2D goalPosition) {
        this.goalPosition = goalPosition;
    }

    // Add this method to update the visualization
    public void updateVisualization(Map worldMap, Point2D agentPosition, Point2D goalPosition) {
        this.worldMap = worldMap;
        this.agentPosition = agentPosition;
        this.goalPosition = goalPosition ;
        repaint(); // Trigger repaint to update the visualization
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int cellSize = CELL_SIZE; // Adjust this based on your preference

        if (this.worldMap == null) {
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {
                    g.setColor(Color.CYAN); // Walkable area
                    g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                    g.setColor(Color.BLACK); // Border color
                    g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
                }
            }
            return;
        }

        for (int i = 0; i < worldMap.getRows(); i++) {
            for (int j = 0; j < worldMap.getCols(); j++) {
                if (worldMap.get(i, j) == -1) {
                    g.setColor(Color.BLACK); // Wall
                } else {
                    g.setColor(Color.CYAN); // Walkable area
                }
                g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);

                g.setColor(Color.BLACK); // Border color
                g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
            }
        }

        g.setColor(Color.GREEN); // Goal position
        g.fillOval(goalPosition.j * cellSize, goalPosition.i * cellSize, cellSize, cellSize);

        g.setColor(Color.BLACK); // Border color for goal position
        g.drawOval(goalPosition.j * cellSize, goalPosition.i * cellSize, cellSize, cellSize);

        g.setColor(Color.RED); // Agent position
        g.fillOval(agentPosition.j * cellSize, agentPosition.i * cellSize, cellSize, cellSize);

        g.setColor(Color.BLACK); // Border color for agent position
        g.drawOval(agentPosition.j * cellSize, agentPosition.i * cellSize, cellSize, cellSize);

        // Ruta del agente
        if (agentPath != null) {
            g.setColor(Color.YELLOW); // Color amarillo para la ruta del agente

            for (Point2D pathPoint : agentPath) {
                g.fillOval(pathPoint.j * cellSize, pathPoint.i * cellSize, cellSize / 2, cellSize / 2);
                g.setColor(Color.BLACK); // Border color for agent path
                g.drawOval(pathPoint.j * cellSize, pathPoint.i * cellSize, cellSize / 2, cellSize / 2);
            }
        }
    }

}
