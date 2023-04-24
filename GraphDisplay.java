import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class GraphDisplay extends JFrame {
    ArrayList<Graph> Liste = new ArrayList<>();
    ArrayList<Connection> Liste_Connection = new ArrayList<>();
    Graph G1 = null;
    boolean est_maintenu = false;
    String state = "Ajouter";
    int Taille_Sommet = 20;
    Dimension Dimension_ToolBar;
    JTextField textField = null;
    JButton b;
    JTextArea ta;
    Boolean mouse_ajoute_enable = true;
    Color c = Color.blue;
    public GraphDisplay() {
        JPanel panel = new JPanel();
        this.add(panel);
        GraphToolBar tb = new GraphToolBar();
        this.add(tb.getToolBar(), BorderLayout.NORTH);
        Dimension_ToolBar = tb.Taille();

        panel.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (tb.state.equals("ToutEffacer")) {
                    Liste.clear();
                    repaint();
                } else {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        if (tb.state.equals("Ajouter")) {
                            if (mouse_ajoute_enable) {
                                mouse_ajoute_enable = false;
                                if (e.getX() > Taille_Sommet * 3 && e.getY() > Taille_Sommet * 3) {
                                    b = new JButton("Couleur");
                                    b.setBounds(100, 50, 100, 30);
                                    b.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            c = JColorChooser.showDialog(null, "Choissisez une Couleur", Color.blue);
                                        }
                                    });
                                    JLabel Text = new JLabel("Nom :");
                                    Text.setBounds(230, 50, 100, 30);
                                    Text.setVisible(true);
                                    panel.add(Text);
                                    JLabel Text2 = new JLabel("Faites Enter pour valider");
                                    Text2.setBounds(200, 75, 100, 150);
                                    Text2.setVisible(true);
                                    panel.add(Text2);
                                    panel.add(b);
                                    panel.setLayout(null);
                                    panel.setSize(400, 800);
                                    panel.setVisible(true);

                                    textField = new JTextField();
                                    textField.setBounds(265, 50, 100, 30);
                                    textField.setVisible(true);
                                    panel.add(textField);
                                    textField.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent x) {
                                            String text = textField.getText();
                                            textField.setVisible(false);
                                            b.setVisible(false);
                                            Text.setVisible(false);
                                            Text2.setVisible(false);
                                            Graph g = new Graph(textField.getText());
                                            g.setCoor(e.getX() - Taille_Sommet, e.getY() - Taille_Sommet);
                                            g.setCouleur(c);
                                            Liste.add(g);
                                            repaint();
                                            mouse_ajoute_enable = true;
                                        }
                                    });


                                }
                            }
                        } else if (tb.state.equals("Supprimer")) {

                            if (e.getX() > Taille_Sommet * 3 && e.getY() > Taille_Sommet * 3) {
                                state = "Supprimer";
                                ArrayList<Graph> temp = new ArrayList<>();
                                for (int i = 0; i < Liste.size(); i++) {
                                    if (e.getX() >= Liste.get(i).x && e.getX() <= Liste.get(i).x + Taille_Sommet * 2) {
                                        if (e.getY() >= Liste.get(i).y && e.getY() <= Liste.get(i).y + Taille_Sommet * 2) {
                                            Liste.remove(i);
                                            break;
                                        }
                                    }
                                }
                            }
                            repaint();
                        } else if (tb.state.equals("Modifier")) {
                            if (e.getX() > Taille_Sommet * 3 && e.getY() > Taille_Sommet * 3) {
                                state = "Modifier";
                                for (int i = 0; i < Liste.size(); i++) {
                                    if (e.getX() >= Liste.get(i).x && e.getX() <= Liste.get(i).x + Taille_Sommet * 2) {
                                        if (e.getY() >= Liste.get(i).y && e.getY() <= Liste.get(i).y + Taille_Sommet * 2) {
                                            G1 = Liste.get(i);
                                            est_maintenu = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e){
                if (e.getButton() == MouseEvent.BUTTON1 && G1 != null){
                    est_maintenu = false;
                    G1 = null;
                }
            }
        });

        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                if (est_maintenu && G1 != null){
                    G1.setCoor(e.getX()-Taille_Sommet,e.getY()-Taille_Sommet );
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
            int y = (int) (this.Liste.get(i).y + Dimension_ToolBar.getHeight()+Taille_Sommet + Taille_Sommet/2);
            g.drawOval(x, y, Taille_Sommet * 2, Taille_Sommet * 2);
            g.setColor(this.Liste.get(i).getCouleur());
            g.fillOval(x, y, Taille_Sommet * 2, Taille_Sommet * 2);
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(this.Liste.get(i).getName());
            int textHeight = fm.getHeight();
            int centerX = x + Taille_Sommet;
            int centerY = y + Taille_Sommet;
            int textX = centerX - (textWidth / 2);
            int textY = centerY + (textHeight / 4);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.PLAIN, 24));
            g.drawString(this.Liste.get(i).getName(), textX, textY);
        }
    }


    public static void main(String[] args) {
        new GraphDisplay();
    }
}
