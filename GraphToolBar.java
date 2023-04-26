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
        btnCharge.addActionListener(this::SaveAs);

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
        Connect.setToolTipText( "Relié deux sommet" );
        Connect.addActionListener(this::Connect_ae);
        tb.add( Connect );

        JButton Edit_Connect = new JButton( new ImageIcon( "png/lien-delete.png" ) );
        Edit_Connect.setToolTipText( "modifier un lien" );
        Edit_Connect.addActionListener(this::Edit_Connect_ae);
        tb.add( Edit_Connect );

        JButton Add_Text_Connect = new JButton( new ImageIcon( "png/lien-add-text.png" ) );
        Add_Text_Connect.setToolTipText( "Ajouter un texte a un lien" );
        Add_Text_Connect.addActionListener(this::Add_Text_Connect_ae);
        tb.add( Add_Text_Connect );

        JButton Change_Color_Connect = new JButton( new ImageIcon( "png/lien-color.png" ) );
        Change_Color_Connect.setToolTipText( "Change la couleur d'un lien" );
        Change_Color_Connect.addActionListener(this::Change_Color_Connect_ae);
        tb.add( Change_Color_Connect );

    }

    private void SaveAs(ActionEvent actionEvent) {
        

    }

    private void Change_Color_Connect_ae(ActionEvent actionEvent) {
        this.state = "Change_Color_Connect";
    }

    private void Add_Text_Connect_ae(ActionEvent actionEvent) {
        this.state = "Add_Text_Connect";
    }

    private void Edit_Connect_ae(ActionEvent actionEvent) {
        this.state = "Edit_Connect";
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

