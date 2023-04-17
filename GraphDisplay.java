import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class GraphDisplay extends JFrame {
    ArrayList<Graph> Liste = new ArrayList<>();

    public GraphDisplay() {

        JPanel panel = new JPanel();
        this.add(panel);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println(e.getX() + "," + e.getY());
                Graph t = new Graph("x");
                t.setCoor(e.getX(),e.getY());
                Liste.add(t);
                repaint();
            }
        });
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(200, 200);
        this.setVisible(true);
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        for (int i = 0; i<this.Liste.size();i++) {
            g.drawLine(this.Liste.get(i).x, this.Liste.get(i).y, this.Liste.get(i).x+5,this.Liste.get(i).y+5);
        }

    }

    public static void main(String[] args) {
        new GraphDisplay();
    }


}