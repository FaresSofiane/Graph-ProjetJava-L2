import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class GraphDisplay extends JFrame {
    ArrayList<Graph> Liste = new ArrayList<>();
    int nodeRadius = 20;

    Dimension Dimension_ToolBar;

    Graph selectedNode = null;
    boolean isDragging = false;

    public GraphDisplay() {
        JPanel panel = new JPanel();
        this.add(panel);

        GraphToolBar tb = new GraphToolBar();
        this.add(tb.getToolBar(), BorderLayout.NORTH);
        Dimension_ToolBar = tb.Taille();

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (tb.state.equals("Ajouter")) {
                        if (e.getX() > nodeRadius * 2 && e.getY() > nodeRadius * 2) {
                            Graph t = new Graph("x");
                            int x = e.getX() + nodeRadius/2;
                            int y = e.getY();
                            t.setCoor(x, y);

                            t.setCouleur(Color.BLUE);
                            Liste.add(t);
                            repaint();
                        }
                    } else if (tb.state.equals("Supprimer")) {
                        if (e.getX() > nodeRadius * 2 && e.getY() > nodeRadius * 2) {
                            ArrayList<Graph> temp = new ArrayList<>();
                            for (int i = 0; i < Liste.size(); i++) {
                                if (e.getX() >= Liste.get(i).x && e.getX() <= Liste.get(i).x + nodeRadius * 2) {
                                    if (e.getY() >= Liste.get(i).y && e.getY() <= Liste.get(i).y + nodeRadius * 2) {
                                        Liste.remove(i);
                                    }
                                }
                            }
                        }
                        repaint();
                    } else if (tb.state.equals("Modifier")) {
                        if (e.getX() > nodeRadius * 2 && e.getY() > nodeRadius * 2) {
                            for (int i = 0; i < Liste.size(); i++) {
                                Graph node = Liste.get(i);
                                if (e.getX() >= node.x && e.getX() <= node.x + nodeRadius * 2 &&
                                        e.getY() >= node.y && e.getY() <= node.y + nodeRadius * 2) {
                                    selectedNode = node;
                                    isDragging = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && selectedNode != null) {
                    selectedNode = null;
                    isDragging = false;
                }
            }
        });

        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isDragging && selectedNode != null) {
                    selectedNode.setCoor(e.getX() - nodeRadius, e.getY() - nodeRadius);
                    repaint();
                }
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setVisible(true);
        Dimension_ToolBar = tb.Taille();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < this.Liste.size(); i++) {
            int x = this.Liste.get(i).x;
            int y = (int) (this.Liste.get(i).y + Dimension_ToolBar.getHeight()+nodeRadius + nodeRadius/2);

            g.drawOval(x, y, nodeRadius * 2, nodeRadius * 2);
            g.drawString(this.Liste.get(i).getName(), x + nodeRadius / 2, y - 50 + nodeRadius * 2);
            g.setColor(this.Liste.get(i).getCouleur());
        }
    }

    public static void main(String[] args) {
        new GraphDisplay();
    }
}
