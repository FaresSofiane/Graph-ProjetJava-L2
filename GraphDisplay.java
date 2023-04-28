import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

public class GraphDisplay extends JFrame {

    private Sommet G1 = null;
    private int taille_sommet = Main.TAILLE_SOMMET;
    private Sommet G1_Connection, G2_Connection = null;
    private boolean est_maintenue = false;
    private Boolean mouse_ajoute_enable = true;
    public static JPanel panel = new JPanel();
    private Graph_ToolBar tb = new Graph_ToolBar();
    static Graph G = null;
    private Color Couleur_Sommet = Color.blue;
    private XML_Graph xml = null;


    public GraphDisplay() {

        super(Main.TITLE);
        this.add(panel);
        tb.addButton(new ImageIcon("png/data-storage.png"), "Enregistrer sous", e -> {xml = new XML_Graph(GraphDisplay.G.getSommets());
            GraphDisplay.G.setSommets(xml.Open(new File("XML_résultat.xml")));repaint();});
        tb.Init_Button();


        this.add(tb, BorderLayout.NORTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(800, 600));
        this.setSize(Main.WIDTH_WINDOWS, Main.HEIGHT_WINDOWS);
        this.setVisible(true);
        Dimension Dimension_MenuBar = tb.getSize();
        G = new Graph(Dimension_MenuBar);



        panel.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3){
                    System.out.println("Clic droit Coordonnées : " + e.getX() + " " + e.getY());


                }
                if (e.getButton() == MouseEvent.BUTTON2){
                    System.out.println("Clic molette Coordonnées");
                    for(Connection c : G.Liste_Connection){
                        System.out.println(c.g1.getName() + " <->" + c.g2.getName());
                        System.out.println(c.g1.x + " " + c.g1.y + " " + c.g2.x + " " + c.g2.y);
                        System.out.println(c.x1 + " " + c.y1 + " " + c.x2 + " " + c.y2);
                    }
                }
                if (e.getButton() == MouseEvent.BUTTON1) {

                    if (tb.getState().equals("Ajouter") && mouse_ajoute_enable) {

                        mouse_ajoute_enable = false;
                        Sommet Sommet_Temp = G.addSommet("", e.getX(), e.getY(), Color.blue);
                        repaint();

                        JButton Bouton_Couleur_Panel = new JButton("Couleur");
                        Bouton_Couleur_Panel.setBounds(100, 50, 100, 30);
                        Bouton_Couleur_Panel.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Couleur_Sommet = JColorChooser.showDialog(null, "Choissisez une Couleur", Color.blue); // on ouvre la fenetre de choix de couleur
                            }
                        });

                        JLabel Label_Nom_Sommet = new JLabel("Nom :");
                        Label_Nom_Sommet.setBounds(230, 50, 100, 30);
                        Label_Nom_Sommet.setVisible(true);

                        JLabel Label_Enter_Validation = new JLabel("Faites Enter pour valider");
                        Label_Enter_Validation.setBounds(200, 75, 100, 150);
                        Label_Enter_Validation.setVisible(true);

                        JTextField Saisie_Nom_Sommet = new JTextField();
                        Saisie_Nom_Sommet.setBounds(265, 50, 100, 30);
                        Saisie_Nom_Sommet.setVisible(true);

                        panel.add(Saisie_Nom_Sommet);
                        panel.add(Label_Nom_Sommet);
                        panel.add(Label_Enter_Validation);
                        panel.add(Bouton_Couleur_Panel);

                        Saisie_Nom_Sommet.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent x) {


                                Saisie_Nom_Sommet.setVisible(false);
                                Bouton_Couleur_Panel.setVisible(false);
                                Label_Nom_Sommet.setVisible(false);
                                Label_Enter_Validation.setVisible(false);

                                Sommet_Temp.ChangeName(Saisie_Nom_Sommet.getText());

                                Sommet_Temp.setCouleur(Couleur_Sommet);

                                repaint();
                                mouse_ajoute_enable = true;

                            }
                        });

                    } else if (tb.getState().equals("Supprimer")) {
                        G.deleteSommet(e);
                        repaint();

                    } else if (tb.getState().equals("Modifier_Position")) {
                        if (G.Find_Sommet(e.getX(), e.getY()) != null) {
                            G1 = G.Find_Sommet(e.getX(), e.getY());
                            est_maintenue = true;
                        }

                    } else if (tb.getState().equals("Connect")) {
                        if (G.Find_Sommet(e.getX(), e.getY()) != null) {
                            if (G1_Connection == null){
                                G1_Connection = G.Find_Sommet(e.getX(), e.getY());
                            } else if (G2_Connection == null){
                                G2_Connection = G.Find_Sommet(e.getX(), e.getY());
                            }
                            if (G1_Connection != null && G2_Connection != null) {
                                G.addConnection(G1_Connection, G2_Connection);
                                G1_Connection = null;
                                G2_Connection = null;
                                repaint();
                            }
                        }
                    } else if (tb.getState().equals("Edit_Connect")) {
                        if (G.Find_Connection(e.getX(), e.getY()) != null) {
                            G.deleteConnection(e);
                            repaint();
                        }


                    } else if (tb.getState().equals("Add_Text_Connect")) {
                        if (G.Find_Connection(e.getX(), e.getY()) != null) {
                            System.out.println(G.Find_Connection(e.getX(), e.getY()));
                            /*
                            JTextField textField = new JTextField();
                            int x = (G.Find_Connection(e.getX(), e.getY()).getG1().x + G.Find_Connection(e.getX(), e.getY()).getG2().x) / 2;
                            int y = ((G.Find_Connection(e.getX(), e.getY()).getG1().y + G.Find_Connection(e.getX(), e.getY()).getG2().y) / 2) + 35;
                            textField.setBounds(x, y, 100, 30);
                            textField.setVisible(true);

                            panel.add(textField);

                            textField.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent x) {
                                    String text = textField.getText();
                                    textField.setVisible(false);
                                    G.Find_Connection(e.getX(), e.getY()).setName(text);
                                    repaint();
                                }
                            });
                            repaint();*/
                        } else if (tb.getState().equals("Change_Color_Connect")) {
                            for (int i = 0; i < G.getConnection().size(); i++) {
                                if (e.getX() >= G.getConnection().get(i).getG1().x && e.getX() <= G.getConnection().get(i).getG2().x + 20 * 2) {
                                    if (e.getY() >= G.getConnection().get(i).g1.y && e.getY() <= G.getConnection().get(i).getG2().y + 20 * 2) {
                                        JButton Bouton_Couleur_Panel = new JButton("Couleur");
                                        Bouton_Couleur_Panel.setBounds(100, 50, 100, 30);
                                        final int index = i;
                                        Bouton_Couleur_Panel.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                Color Couleur_Arete = JColorChooser.showDialog(null, "Choissisez une Couleur", Color.blue);
                                                G.getConnection().get(index).setCouleur(Couleur_Arete);
                                                Bouton_Couleur_Panel.setVisible(false);
                                                repaint();
                                            }
                                        });
                                        panel.add(Bouton_Couleur_Panel);
                                        Bouton_Couleur_Panel.setVisible(true);
                                        repaint();
                                    }
                                }

                            }
                        }
                    }
                }

            }


            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && G1 != null) {
                    est_maintenue = false;
                    G1 = null;
                }
            }
        });

        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                if (est_maintenue && G1 != null) {
                    if (e.getY() < 0) {

                        G1.setCoor(e.getX() - 20, Dimension_MenuBar.height + taille_sommet);
                        repaint();
                    } else {

                        G1.setCoor(e.getX() - 20, e.getY() - 20);
                        repaint();
                    }
                }
            }
        });


    }

    public static void main(String[] args) {

        new GraphDisplay();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < this.G.getConnection().size(); i++) {
            this.G.getConnection().get(i).Create(g, tb.getSize().height, taille_sommet);
        }
        for (int i = 0; i < this.G.getSommets().size(); i++) {
            this.G.getSommets().get(i).Create(g, tb.getSize().height, taille_sommet);

        }


    }

}
