import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
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


        this.add(tb, BorderLayout.NORTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(800, 600));
        this.setSize(Main.WIDTH_WINDOWS, Main.HEIGHT_WINDOWS);
        this.setVisible(true);
        Dimension Dimension_MenuBar = tb.getSize();
        G = new Graph(Dimension_MenuBar);

        tb.addButton(new ImageIcon("png/data-storage.png"), "Enregistrer sous", e -> {xml = new XML_Graph(GraphDisplay.G.getSommets());
            GraphDisplay.G.setSommets(xml.Open(new File("XML_résultat.xml")));repaint();});
        tb.Init_Button();

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection l1[] = new Connection[G.Liste_Connection.size()];
                for (int i = 0; i < G.Liste_Connection.size(); i++) {
                    l1[i] = G.Liste_Connection.get(i);
                }

                JList<Connection> Liste_Connection = new JList<Connection>(l1);
                Liste_Connection.setCellRenderer(new DefaultListCellRenderer() {
                    @Override
                    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                        if (c instanceof JLabel && value instanceof Connection) {
                            String name = ((Connection) value).Nom_Connection;
                            ((JLabel) c).setText(name);
                        }
                        return c;
                    }
                });

                JScrollPane Scroll_Liste_Connection = new JScrollPane(Liste_Connection);
                JButton Color_Button=new JButton("Couleur");
                JButton Delete=new JButton("Supprimer");
                JButton Text_Button=new JButton("Nom");
                JButton Exit_Button=new JButton("Quitter");
                JTextField Name_Arete = new JTextField();

                Text_Button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(Liste_Connection.getSelectedValue()!=null){

                            int x = (Liste_Connection.getSelectedValue().getG1().getX() + Liste_Connection.getSelectedValue().getG2().getX()) / 2;
                            int y = ((Liste_Connection.getSelectedValue().getG1().getY() + Liste_Connection.getSelectedValue().getG2().y) / 2) + 35;
                            Name_Arete.setBounds(x, y, 100, 30);
                            Name_Arete.setText(Liste_Connection.getSelectedValue().Nom_Connection);
                            Name_Arete.setVisible(true);
                            panel.add(Name_Arete);
                            Name_Arete.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent x) {
                                    String text = Name_Arete.getText();
                                    panel.remove(Scroll_Liste_Connection);
                                    panel.remove(Color_Button);
                                    panel.remove(Delete);
                                    panel.remove(Name_Arete);
                                    panel.remove(Text_Button);
                                    panel.remove(Exit_Button);
                                    Liste_Connection.getSelectedValue().setName(text);
                                    repaint();
                                }
                            });
                        }
                    }
                });


                Delete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(Liste_Connection.getSelectedValue()!=null){
                            System.out.println(Liste_Connection.getSelectedValue().Nom_Connection);
                            G.deleteConnection(Liste_Connection.getSelectedValue());
                            panel.remove(Scroll_Liste_Connection);
                            panel.remove(Color_Button);
                            panel.remove(Delete);
                            panel.remove(Name_Arete);
                            panel.remove(Text_Button);
                            panel.remove(Exit_Button);
                            repaint();
                        }
                    }
                });



                Color_Button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(Liste_Connection.getSelectedValue()!=null){
                            Color Couleur_Arete = JColorChooser.showDialog(null, "Choissisez une Couleur", Color.BLUE);
                            Liste_Connection.getSelectedValue().setCouleur(Couleur_Arete);
                            panel.remove(Scroll_Liste_Connection);
                            panel.remove(Color_Button);
                            panel.remove(Delete);
                            panel.remove(Name_Arete);
                            panel.remove(Text_Button);
                            panel.remove(Exit_Button);
                            repaint();
                        }
                    }
                });

                Exit_Button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panel.remove(Scroll_Liste_Connection);
                        panel.remove(Color_Button);
                        panel.remove(Delete);
                        panel.remove(Name_Arete);
                        panel.remove(Text_Button);
                        panel.remove(Exit_Button);
                        repaint();
                    }
                });

                if (G.Liste_Connection.size() != 0) {
                    Delete.setVisible(true);
                    Color_Button.setVisible(true);
                    Text_Button.setVisible(true);
                    Scroll_Liste_Connection.setVisible(true);

                    Delete.setBounds(200,100,80,30);
                    Color_Button.setBounds(200,150,80,30);
                    Scroll_Liste_Connection.setBounds(0, 0, 100, 200);
                    Text_Button.setBounds(200,200,80,30);
                    Exit_Button.setBounds(200,250,80,30);

                    panel.add(Exit_Button);
                    panel.add(Text_Button);
                    panel.add(Scroll_Liste_Connection);
                    panel.add(Delete);
                    panel.add(Color_Button);

                    repaint();
                }

            }
        };
        tb.addButton(new ImageIcon("png/lien-delete.png"), "Modifier un lien",actionListener);


        panel.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {

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

                                panel.remove(Saisie_Nom_Sommet);
                                panel.remove(Bouton_Couleur_Panel);
                                panel.remove(Label_Nom_Sommet);
                                panel.remove(Label_Enter_Validation);

                                Sommet_Temp.ChangeName(Saisie_Nom_Sommet.getText());
                                Sommet_Temp.setCouleur(Couleur_Sommet);

                                repaint();
                                mouse_ajoute_enable = true;

                            }
                        });

                    } else if (tb.getState().equals("Supprimer")) {
                        G.deleteSommet(e);
                        repaint();

                    } else if (tb.getState().equals("Modifier")) {
                        if (G.Find_Sommet(e.getX(), e.getY()) != null) {
                            G1 = G.Find_Sommet(e.getX(), e.getY());
                            est_maintenue = true;
                        }

                    } else if (tb.getState().equals("Relier")) {
                        if (G.Find_Sommet(e.getX(), e.getY()) != null) {
                            if (G1_Connection == null) {
                                G1_Connection = G.Find_Sommet(e.getX(), e.getY());
                            } else if (G2_Connection == null && G1_Connection != G.Find_Sommet(e.getX(), e.getY())) {
                                G2_Connection = G.Find_Sommet(e.getX(), e.getY());
                            }
                            if (G1_Connection != null && G2_Connection != null) {
                                if (G.Already_Exist_Connection(G1_Connection, G2_Connection)) {
                                    G1_Connection = null;
                                    G2_Connection = null;
                                    repaint();
                                    return;
                                }
                                G.addConnection(G1_Connection, G2_Connection);
                                G1_Connection = null;
                                G2_Connection = null;
                                repaint();
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
