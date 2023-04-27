import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class GraphDisplay extends JFrame {
    ArrayList<Graph> Liste = new ArrayList<>();
    ArrayList<Connection> Liste_Connection = new ArrayList<>();
    ArrayList<Connection> temp_Liste_Connection = new ArrayList<>();
    Graph G1 = null;
    int Taille = 40;
    Graph G1_Connection, G2_Connection = null;
    boolean est_maintenu = false;
    String state = "Ajouter";
    JTextField textField = null;
    JButton b;
    Boolean mouse_ajoute_enable = true;
    Color c = Color.blue;
    Color c2 = Color.blue;
    JPanel panel = new JPanel();
    JToolBar tb = new JToolBar();

    public GraphDisplay() {

        super("Graph Display");
        this.add(panel);
        this.add(tb, BorderLayout.NORTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setVisible(true);

        Dimension Dimension_MenuBar = tb.getSize();
        tb.setFloatable(false);
        JButton btnCharge = new JButton( new ImageIcon( "png/data-storage.png") );
        btnCharge.setToolTipText( "Enregistrer sous" );
        tb.add(btnCharge);
        JButton btnSave = new JButton( new ImageIcon( "png/save-data.png" ) );
        btnSave.setToolTipText( "Enregistrer" );
        tb.add(btnSave);
        JButton btnSaveAs = new JButton( new ImageIcon( "png/file-backup.png" ) );
        btnSaveAs.setToolTipText( "Charger un graph" );
        tb.add(btnSaveAs);
        tb.addSeparator();
        JButton ajouter = new JButton(new ImageIcon("png/creation.png"));
        ajouter.setToolTipText("Ajouter un sommet");
        ajouter.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                state = "Ajouter";
            }
        });
        tb.add(ajouter);
        JButton Modifier = new JButton( new ImageIcon( "png/pencil.png" ) );
        Modifier.setToolTipText( "Modifier" );
        Modifier.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                state = "Modifier";
            }
        });
        tb.add(Modifier);
        JButton Supprimer = new JButton( new ImageIcon( "png/remove.png" ) );
        Supprimer.setToolTipText( "Supprimer" );
        Supprimer.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                state = "Supprimer";
            }
        });
        tb.add(Supprimer);
        JButton ToutEffacer = new JButton( new ImageIcon( "png/delete-all.png" ) );
        ToutEffacer.setToolTipText( "Charger un graph" );
        ToutEffacer.addActionListener(  e -> {
            state = "ToutEffacer";
            Liste.clear();
            Liste_Connection.clear();
            temp_Liste_Connection.clear();
            repaint();
                }
        );
        tb.add(ToutEffacer);
        tb.addSeparator();
        JButton Connect = new JButton( new ImageIcon( "png/lien-connecte.png" ) );
        Connect.setToolTipText( "Relié deux sommet" );
        Connect.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                state = "Connect";
            }
        });
        tb.add(Connect);
        JButton Edit_Connect = new JButton( new ImageIcon( "png/lien-delete.png" ) );
        Edit_Connect.setToolTipText( "modifier un lien" );
        Edit_Connect.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                state = "Edit_Connect";
            }
        });
        tb.add(Edit_Connect);
        JButton Add_Text_Connect = new JButton( new ImageIcon( "png/lien-add-text.png" ) );
        Add_Text_Connect.setToolTipText( "Ajouter un texte a un lien" );
        Add_Text_Connect.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                state = "Add_Text_Connect";
            }
        });
        tb.add(Add_Text_Connect);
        JButton Change_Color_Connect = new JButton( new ImageIcon( "png/lien-color.png" ) );
        Change_Color_Connect.setToolTipText( "Change la couleur d'un lien" );
        Change_Color_Connect.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                state = "Change_Color_Connect";
            }
        });
        tb.add(Change_Color_Connect);


        panel.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        if (state.equals("Ajouter")) {
                            if (mouse_ajoute_enable) { // verifie si on a déja cliqué sur le bouton ajouter
                                mouse_ajoute_enable = false; // on désactive le bouton ajouter
                                Graph g = new Graph(); // on crée un nouveau point
                                g.setCoor(e.getX(), e.getY());  // on lui donne les coordonnées du clique
                                g.setCouleur(c); // couleur de base bleu
                                Liste.add(g); // on l'ajoute a la liste
                                repaint(); // on actualise la page afin d'afficher le point mais sans nom il sera rajouté aprés avec la possibilité de changer la couleur
                                b = new JButton("Couleur");// creation du bouton pour choisir la couleur
                                b.setBounds(100, 50, 100, 30); // position du bouton
                                b.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        c = JColorChooser.showDialog(null, "Choissisez une Couleur", Color.blue); // on ouvre la fenetre de choix de couleur
                                    }
                                });
                                JLabel Text = new JLabel("Nom :");
                                Text.setBounds(230, 50, 100, 30);
                                Text.setVisible(true);
                                panel.add(Text);
                                JLabel Text2 = new JLabel("Faites Enter pour valider");
                                Text2.setBounds(200, 75, 100, 150);
                                Text2.setVisible(true);
                                textField = new JTextField();
                                textField.setBounds(265, 50, 100, 30);
                                textField.setVisible(true);
                                panel.add(textField);
                                panel.add(Text2);
                                panel.add(b);
                                textField.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent x) {
                                        String text = textField.getText();
                                        textField.setVisible(false);
                                        b.setVisible(false);
                                        Text.setVisible(false);
                                        Text2.setVisible(false);
                                        for (int z = 0; z<Liste.size(); z++){
                                            if (Liste.get(z) == g){
                                                Liste.get(z).ChangeName(text);
                                                Liste.get(z).setCouleur(c);
                                            }
                                        }


                                        repaint();
                                        mouse_ajoute_enable = true;
                                    }
                                });

                            }
                        }
                        else if (state.equals("Supprimer")) {
                                state = "Supprimer";
                                ArrayList<Graph> temp = new ArrayList<>();
                                for (int i = 0; i < Liste.size(); i++) {
                                    if (e.getX() >= Liste.get(i).x + 10 && e.getX() <= Liste.get(i).x + Taille + 10) {
                                        if (e.getY() + Dimension_MenuBar.height + (Taille - 10) >= Liste.get(i).y && e.getY() <= Liste.get(i).y + Taille ) {


                                            if (Liste_Connection.size() > 0) {
                                                for (int j = 0; j < Liste_Connection.size(); j++) {
                                                    if (Liste_Connection.get(j).g1 != Liste.get(i) && Liste_Connection.get(j).g2 != Liste.get(i)) {
                                                        temp_Liste_Connection.add(Liste_Connection.get(j));
                                                    }
                                                }
                                                Liste_Connection.clear();
                                                Liste_Connection.addAll(temp_Liste_Connection);
                                                temp_Liste_Connection.clear();

                                            }
                                            Liste.remove(i);
                                        }

                                }
                            }
                            repaint();
                        } else if (state.equals("Modifier")) {
                                state = "Modifier";
                                System.out.println("Taille toolbar :" + tb.getSize());
                                for (int i = 0; i < Liste.size(); i++) {
                                    if (e.getX() >= Liste.get(i).x + 10 && e.getX() <= Liste.get(i).x + Taille + 10) {
                                        if (e.getY() + Dimension_MenuBar.height + (Taille - 10) >= Liste.get(i).y && e.getY() <= Liste.get(i).y + Taille ) {
                                            G1 = Liste.get(i);
                                            est_maintenu = true;
                                        }
                                    }

                            }
                        } else if (state.equals("Connect")){

                            for (int i = 0; i < Liste.size(); i++) {
                                if (e.getX() >= Liste.get(i).x + 10 && e.getX() <= Liste.get(i).x + Taille + 10) {
                                    if (e.getY() + Dimension_MenuBar.height + (Taille - 10) >= Liste.get(i).y && e.getY() <= Liste.get(i).y + Taille ) {
                                        if (G1_Connection == null){
                                            G1_Connection = Liste.get(i);
                                        }
                                        else if (G2_Connection == null){
                                            G2_Connection = Liste.get(i);
                                        }

                                        if (G1_Connection != null && G2_Connection != null){
                                            Connection C = new Connection(G1_Connection,G2_Connection);
                                            Liste_Connection.add(C);
                                            G1_Connection = null;
                                            G2_Connection = null;
                                            repaint();
                                        }
                                    }
                                }
                            }
                        }
                        else if (state.equals("Edit_Connect")){
                            for (int i = 0; i < Liste_Connection.size(); i++) {
                                if (e.getX() >= Liste_Connection.get(i).getG1().x && e.getX() <= Liste_Connection.get(i).getG2().x + 20 * 2) {
                                    if (e.getY() >= Liste_Connection.get(i).g1.y && e.getY() <= Liste_Connection.get(i).getG2().y + 20 * 2) {
                                        Liste_Connection.remove(i);
                                        repaint();
                                    }
                                }
                            }
                        } else if (state.equals("Add_Text_Connect")) {
                                state = "Add_Text_Connect";
                                for (int i = 0; i < Liste_Connection.size(); i++) {
                                    if (e.getX() >= Liste_Connection.get(i).getG1().x && e.getX() <= Liste_Connection.get(i).getG2().x + 20 * 2) {
                                        if (e.getY() >= Liste_Connection.get(i).g1.y && e.getY() <= Liste_Connection.get(i).getG2().y + 20 * 2) {
                                            textField = new JTextField();
                                            textField.setBounds(265, 50, 100, 30);
                                            textField.setVisible(true);
                                            panel.add(textField);
                                            final int index = i;
                                            textField.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent x) {
                                                    String text = textField.getText();
                                                    textField.setVisible(false);
                                                    Liste_Connection.get(index).setName(text);
                                                    repaint();
                                                }
                                            });
                                            repaint();
                                        }
                                    }

                            }

                        } else if (state.equals("Change_Color_Connect")){
                                state = "Change_Color_Connect";
                                for (int i = 0; i < Liste_Connection.size(); i++) {
                                    if (e.getX() >= Liste_Connection.get(i).getG1().x && e.getX() <= Liste_Connection.get(i).getG2().x + 20 * 2) {
                                        if (e.getY() >= Liste_Connection.get(i).g1.y && e.getY() <= Liste_Connection.get(i).getG2().y + 20 * 2) {
                                            b = new JButton("Couleur");
                                            b.setBounds(100, 50, 100, 30);
                                            final int index = i;
                                            b.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    c2 = JColorChooser.showDialog(null, "Choissisez une Couleur", Color.blue);
                                                    Liste_Connection.get(index).setCouleur(c2);
                                                    b.setVisible(false);
                                                    repaint();
                                                }
                                            });
                                            panel.add(b);
                                            b.setVisible(true);
                                            repaint();
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
                System.out.println("X : " + e.getX() + " Y : " + e.getY());
                if (est_maintenu && G1 != null){
                    if  (e.getY() <0){

                G1.setCoor(e.getX() - 20,  0 + Dimension_MenuBar.height + Taille);
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
        for (int i = 0; i < this.Liste_Connection.size(); i++) {
            Liste_Connection.get(i).Create(g, tb.getSize().height, Taille);
        }
        for (int i = 0; i < Liste.size(); i++) {
            Liste.get(i).Create(g, tb.getSize().height , Taille);
        }





    }


    public static void main(String[] args) {

        new GraphDisplay();
    }
}
