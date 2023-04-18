import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class GraphToolBar extends JToolBar {
    JToolBar tb = new JToolBar();
    String state = "Ajouter";
    public GraphToolBar() {

        tb.setFloatable(false);
        JButton btnCharge = new JButton( new ImageIcon( "png/data-storage.png") );
        btnCharge.setToolTipText( "Enregistrer sous" );
        tb.add( btnCharge );

        JButton btnSave = new JButton( new ImageIcon( "png/save-data.png" ) );
        btnSave.setToolTipText( "Enregistrer" );
        tb.add( btnSave );

        JButton btnSaveAs = new JButton( new ImageIcon( "png/file-backup.png" ) );
        btnSaveAs.setToolTipText( "Charger un graph" );
        tb.add( btnSaveAs );

        tb.addSeparator();
        JButton Ajouter = new JButton( new ImageIcon( "png/creation.png") );
        Ajouter.setToolTipText( "Ajouter un sommet" );
        Ajouter.addActionListener(this::Ajouter);

        tb.add( Ajouter );

        JButton Modifier = new JButton( new ImageIcon( "png/pencil.png" ) );
        Modifier.addActionListener(this::Modifier_ae);
        Modifier.setToolTipText( "Modifier" );

        tb.add( Modifier );

        JButton Supprimer = new JButton( new ImageIcon( "png/remove.png" ) );
        Supprimer.addActionListener(this::Supprimer_ae);
        Supprimer.setToolTipText( "Supprimer" );

        tb.add( Supprimer );

        JButton ToutEffacer = new JButton( new ImageIcon( "png/delete-all.png" ) );
        ToutEffacer.setToolTipText( "Charger un graph" );
        ToutEffacer.addActionListener(this::ToutEffacer_ae);
        tb.add( ToutEffacer );


    }
    private Graph Ajouter(ActionEvent actionEvent) {
        this.state = "Ajouter";
    return null;}
    private void Modifier_ae(ActionEvent actionEvent) {
        this.state = "Modifier";    }
    private void ToutEffacer_ae(ActionEvent actionEvent) {
this.state = "ToutEffacer";    }
    private void Supprimer_ae(ActionEvent actionEvent) {
this.state = "Supprimer";
    }



    public JToolBar getToolBar() {
        return tb;
    }

    public Dimension Taille(){
        return tb.getSize();
    }
}

