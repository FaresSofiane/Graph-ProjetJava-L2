import javax.swing.*;
import java.util.*;
import java.awt.*;

public class GraphDisplay extends JFrame {
    private ArrayList<Graph> nodes;
    private int nodeRadius = 10;

    public GraphDisplay(ArrayList<Graph> nodes) {
        this.nodes = nodes;
        setTitle("Graph Display");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int numNodes = nodes.size();
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int angleStep = 360 / numNodes;

        for (int i = 0; i < numNodes; i++) {
            Graph node = nodes.get(i);
            int x = (int) (centerX + Math.cos(Math.toRadians(angleStep * i)) * 200);
            int y = (int) (centerY + Math.sin(Math.toRadians(angleStep * i)) * 200);
            g.setColor(Color.BLUE);
            g.drawOval(x - nodeRadius, y - nodeRadius, nodeRadius * 2, nodeRadius * 2);
            g.drawString(node.getName(), x - nodeRadius / 2, y + nodeRadius / 2);
            g.setColor(Color.BLACK);
            node.GetConnection();
            for (Graph neighbor : node.Connection) {
                int neighborIndex = nodes.indexOf(neighbor);
                int neighborX = (int) (centerX + Math.cos(Math.toRadians(angleStep * neighborIndex)) * 200);
                int neighborY = (int) (centerY + Math.sin(Math.toRadians(angleStep * neighborIndex)) * 200);
                g.drawLine(x, y, neighborX, neighborY);
            }
        }

    }

    public static void main(String[] args) {
        Graph g1 = new Graph("A");
        Graph g2 = new Graph("B");
        Graph g3 = new Graph("C");
        Graph g4 = new Graph("D");
        Graph g5 = new Graph("E");
        g1.AddConnection(g2);
        g1.AddConnection(g3);
        g1.AddConnection(g4);
        g1.AddConnection(g5);
        g3.AddConnection(g5);

        ArrayList<Graph> nodes = new ArrayList<>();
        nodes.add(g1);
        nodes.add(g2);
        nodes.add(g3);
        nodes.add(g4);
        nodes.add(g5);

        new GraphDisplay(nodes);
    }


}