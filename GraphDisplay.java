import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
/*

Sofiane Fares
Github : github.com/FaresSofiane

*/

public class GraphDisplay extends JFrame {

    public static JPanel panel = new JPanel();
    static Graph G = null;
    int taille_sommet = Main.TAILLE_SOMMET;
    Graph_ToolBar tb = new Graph_ToolBar();
    private Sommet G1 = null;
    private Sommet G1_Connection, G2_Connection = null;
    private Boolean est_maintenue = false;
    private Boolean mouse_ajoute_enable = true;
    private Boolean mouse_arete_edit_enable = true;
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

        ActionListener SaveAs = e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to save");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier XML", "xml");
            fileChooser.addChoosableFileFilter(filter);
            int userSelection = fileChooser.showSaveDialog(GraphDisplay.this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                xml = new XML_Graph(GraphDisplay.G);
                xml.Save(new File(fileToSave + ".xml"));

                System.out.println("Save as file: " + fileToSave.getAbsolutePath());
            }
        };

        ActionListener Save = e -> {
            if (xml == null) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Specify a file to save");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier XML", "xml");
                fileChooser.addChoosableFileFilter(filter);
                int userSelection = fileChooser.showSaveDialog(GraphDisplay.this);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    xml = new XML_Graph(GraphDisplay.G);
                    xml.Save(new File(fileToSave + ".xml"));

                    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
                }
            } else {
                xml.Save(xml.getFile());
            }
        };

        ActionListener Load = e -> {

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to load");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier XML", "xml");
            fileChooser.addChoosableFileFilter(filter);
            int userSelection = fileChooser.showOpenDialog(GraphDisplay.this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToLoad = fileChooser.getSelectedFile();
                xml = new XML_Graph(GraphDisplay.G);
                xml.Open(fileToLoad);
                repaint();
                System.out.println("Load file: " + fileToLoad.getAbsolutePath());
            }
        };

        tb.addButton(new ImageIcon("png/enregistrer-sous.png"), "Enregistrer sous", SaveAs);
        tb.addButton(new ImageIcon("png/enregistrer.png"), "Enregistrer un graph", Save);
        tb.addButton(new ImageIcon("png/charger.png"), "Charger un graph", Load);
        tb.Init_Button();

        ActionListener Action_Arete_Edit = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                mouse_ajoute_enable = true;
                tb.setState("lock");


                if (mouse_arete_edit_enable && mouse_ajoute_enable) {
                    mouse_arete_edit_enable = false;
                    Aretes[] l1 = new Aretes[G.getAretes().size()];
                    for (int i = 0; i < G.getAretes().size(); i++) {
                        l1[i] = G.getAretes().get(i);
                    }

                    JList<Aretes> Liste_Connection = new JList<>(l1);
                    Liste_Connection.setCellRenderer(new DefaultListCellRenderer() {
                        @Override
                        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                            if (c instanceof JLabel && value instanceof Aretes) {
                                String name = ((Aretes) value).Nom_Connection;
                                ((JLabel) c).setText(name);
                            }
                            return c;
                        }
                    });

                    JScrollPane Scroll_Liste_Connection = new JScrollPane(Liste_Connection);
                    JButton Color_Button = new JButton("Couleur");
                    JButton Delete = new JButton("Supprimer");
                    JButton Text_Button = new JButton("Nom");
                    JButton Exit_Button = new JButton("Quitter");
                    JTextField Name_Arete = new JTextField();


                    Text_Button.addActionListener(e1 -> {
                        if (Liste_Connection.getSelectedValue() != null) {

                            int x = (Liste_Connection.getSelectedValue().getG1().getX() + Liste_Connection.getSelectedValue().getG2().getX()) / 2;
                            int y = ((Liste_Connection.getSelectedValue().getG1().getY() + Liste_Connection.getSelectedValue().getG2().getY()) / 2) + 35;
                            Name_Arete.setBounds(x, y, 100, 30);
                            Name_Arete.setText(Liste_Connection.getSelectedValue().Nom_Connection);
                            Name_Arete.setVisible(true);
                            panel.add(Name_Arete);
                            Name_Arete.addActionListener(x1 -> {
                                String text = Name_Arete.getText();
                                panel.remove(Scroll_Liste_Connection);
                                panel.remove(Color_Button);
                                panel.remove(Delete);
                                panel.remove(Name_Arete);
                                panel.remove(Text_Button);
                                panel.remove(Exit_Button);
                                Liste_Connection.getSelectedValue().setName(text);
                                repaint();
                                mouse_arete_edit_enable = true;
                            });
                        }

                    });


                    Delete.addActionListener(e12 -> {
                        if (Liste_Connection.getSelectedValue() != null) {
                            System.out.println(Liste_Connection.getSelectedValue().Nom_Connection);
                            G.deleteAretes(Liste_Connection.getSelectedValue());
                            panel.remove(Scroll_Liste_Connection);
                            panel.remove(Color_Button);
                            panel.remove(Delete);
                            panel.remove(Name_Arete);
                            panel.remove(Text_Button);
                            panel.remove(Exit_Button);
                            repaint();
                            mouse_arete_edit_enable = true;
                        }
                    });


                    Color_Button.addActionListener(e13 -> {
                        if (Liste_Connection.getSelectedValue() != null) {
                            Color Couleur_Arete = JColorChooser.showDialog(null, "Choissisez une Couleur", Color.BLUE);
                            Liste_Connection.getSelectedValue().setCouleur(Couleur_Arete);
                            panel.remove(Scroll_Liste_Connection);
                            panel.remove(Color_Button);
                            panel.remove(Delete);
                            panel.remove(Name_Arete);
                            panel.remove(Text_Button);
                            panel.remove(Exit_Button);
                            repaint();
                            mouse_arete_edit_enable = true;
                        }
                    });

                    Exit_Button.addActionListener(e14 -> {
                        panel.remove(Scroll_Liste_Connection);
                        panel.remove(Color_Button);
                        panel.remove(Delete);
                        panel.remove(Name_Arete);
                        panel.remove(Text_Button);
                        panel.remove(Exit_Button);
                        repaint();
                        mouse_arete_edit_enable = true;
                    });

                    if (G.getAretes().size() != 0) {
                        Delete.setVisible(true);
                        Color_Button.setVisible(true);
                        Text_Button.setVisible(true);
                        Scroll_Liste_Connection.setVisible(true);
                        Exit_Button.setVisible(true);
                        Liste_Connection.setVisible(true);


                        Delete.setBounds(110, 10, 80, 30);
                        Color_Button.setBounds(110, 60, 80, 30);
                        Scroll_Liste_Connection.setBounds(0, 0, 100, 200);
                        Text_Button.setBounds(110, 110, 80, 30);
                        Exit_Button.setBounds(110, 160, 80, 30);

                        panel.add(Exit_Button);
                        panel.add(Text_Button);
                        panel.add(Scroll_Liste_Connection);
                        panel.add(Delete);
                        panel.add(Color_Button);

                        Liste_Connection.repaint();

                        repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "Aucun lien n'a été créé", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                    mouse_arete_edit_enable = true;
                    tb.setState("Ajouter");

                }
            }
        };
        tb.addButton(new ImageIcon("png/arete-edit.png"), "Modifier un lien", Action_Arete_Edit);
        tb.addSeparator();

        ActionListener AllDelete = e -> {
            G.Clear();
            repaint();
        };
        tb.addButton(new ImageIcon("png/corbeille.png"), "Supprimer le graph", AllDelete);


        panel.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {

                if (e.getButton() == MouseEvent.BUTTON1) {

                    if (tb.getState().equals("Ajouter") && mouse_ajoute_enable && mouse_arete_edit_enable) {

                        mouse_ajoute_enable = false;
                        Sommet Sommet_Temp = G.addSommet("", e.getX(), e.getY(), Color.blue);
                        repaint();

                        JButton Bouton_Couleur_Panel = new JButton("Couleur");
                        Bouton_Couleur_Panel.setBounds(100, 50, 100, 30);
                        Bouton_Couleur_Panel.addActionListener(e15 -> {
                            Couleur_Sommet = JColorChooser.showDialog(null, "Choissisez une Couleur", Color.blue); // on ouvre la fenetre de choix de couleur
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

                        Saisie_Nom_Sommet.addActionListener(x -> {

                            panel.remove(Saisie_Nom_Sommet);
                            panel.remove(Bouton_Couleur_Panel);
                            panel.remove(Label_Nom_Sommet);
                            panel.remove(Label_Enter_Validation);

                            String Name = Saisie_Nom_Sommet.getText();
                            for (int i = 0; i < G.getSommets().size(); i++) {
                                if (G.getSommets().get(i).getName().equals(Saisie_Nom_Sommet.getText())) {
                                    JOptionPane.showMessageDialog(null, "Ce nom est déjà utilisé", "Erreur", JOptionPane.ERROR_MESSAGE);
                                    Name = "";
                                    G.getSommets().remove(Sommet_Temp);
                                    repaint();
                                    mouse_ajoute_enable = true;
                                    return;
                                }
                            }


                            Sommet_Temp.ChangeName(Name);
                            Sommet_Temp.setCouleur(Couleur_Sommet);

                            repaint();
                            mouse_ajoute_enable = true;

                        });

                    } else if (tb.getState().equals("Supprimer") && mouse_ajoute_enable && mouse_arete_edit_enable) {
                        G.deleteSommet(e);
                        repaint();

                    } else if (tb.getState().equals("Deplacer") && mouse_arete_edit_enable && mouse_ajoute_enable) {
                        if (G.Find_Sommet(e.getX(), e.getY()) != null) {
                            G1 = G.Find_Sommet(e.getX(), e.getY());
                            est_maintenue = true;
                        }

                    } else if (tb.getState().equals("Relier") && mouse_arete_edit_enable && mouse_ajoute_enable) {
                        if (G.Find_Sommet(e.getX(), e.getY()) != null) {
                            if (G1_Connection == null) {
                                G1_Connection = G.Find_Sommet(e.getX(), e.getY());
                            } else if (G2_Connection == null && G1_Connection != G.Find_Sommet(e.getX(), e.getY())) {
                                G2_Connection = G.Find_Sommet(e.getX(), e.getY());
                            }
                            if (G1_Connection != null && G2_Connection != null && G1_Connection != G2_Connection) {
                                if (G.Already_Exist_Aretes(G1_Connection, G2_Connection)) {
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
                    } else if (tb.getState().equals("Renommer") && mouse_ajoute_enable && mouse_arete_edit_enable) {

                        if (G.Find_Sommet(e.getX(), e.getY()) != null) {
                            Sommet Sommet_Temp = G.Find_Sommet(e.getX(), e.getY());
                            String Nom_Temp = JOptionPane.showInputDialog(null, "Entrez le nouveau nom du sommet", "Renommer", JOptionPane.QUESTION_MESSAGE);
                            if (Nom_Temp != null) {
                                Sommet_Temp.ChangeName(Nom_Temp);
                                repaint();
                                for (int i = 0; i < G.getAretes().size(); i++) {
                                    G.getAretes().get(i).update();
                                }
                            }
                        }
                    } else if (tb.getState().equals("Couleur") && mouse_ajoute_enable && mouse_arete_edit_enable) {
                        if (G.Find_Sommet(e.getX(), e.getY()) != null) {
                            Sommet Sommet_Temp = G.Find_Sommet(e.getX(), e.getY());
                            Color Couleur_Temp = JColorChooser.showDialog(null, "Choissisez une Couleur", Color.blue); // on ouvre la fenetre de choix de couleur
                            if (Couleur_Temp != null) {
                                Sommet_Temp.setCouleur(Couleur_Temp);
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

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < G.getAretes().size(); i++) {
            G.getAretes().get(i).Create(g, tb.getSize().height, taille_sommet);
        }
        for (int i = 0; i < G.getSommets().size(); i++) {
            G.getSommets().get(i).Create(g, tb.getSize().height, taille_sommet);

        }


    }

}
