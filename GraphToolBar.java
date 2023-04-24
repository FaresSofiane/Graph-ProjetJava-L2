import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

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

        tb.addSeparator();

        JButton Connect = new JButton( new ImageIcon( "png/lien-connecte.png" ) );
        Connect.setToolTipText( "Charger un graph" );
        Connect.addActionListener(this::Connect_ae);
        tb.add( Connect );


    }
    private Graph Ajouter(ActionEvent actionEvent) {
        this.state = "Ajouter";
        return null;
    }

    private void Connect_ae(ActionEvent actionEvent) {
        this.state = "Connect";
    }

    private void Modifier_ae(ActionEvent actionEvent) {
        this.state = "Modifier";
    }

    private void ToutEffacer_ae(ActionEvent actionEvent) {
        this.state = "ToutEffacer";

    }

    private void Supprimer_ae(ActionEvent actionEvent) {
        this.state = "Supprimer";
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public JToolBar getToolBar() {
        return tb;
    }

    public Dimension Taille(){
        return tb.getSize();
    }
}

