import javax.swing.*;
import javax.swing.plaf.PanelUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Graph_ToolBar extends JToolBar {

    private static final String ADD_STATE = "Ajouter";
    private static final String MODIFY_STATE = "Deplacer";
    private static final String RENAME_STATE = "Renommer";
    private static final String DELETE_STATE = "Supprimer";
    private static final String CONNECT_STATE = "Relier";
    private static final String COLOR_STATE = "Couleur";




    File file = null;
    XML_Graph xml = null;
    private String state = ADD_STATE;

    public Graph_ToolBar() {
        setFloatable(false);




    }

    public void Init_Button() {
        addSeparator();

        JButton[] SommetButton = {
                addButton(new ImageIcon("png/ajouter.png"), "Ajouter un sommet", e -> setState(ADD_STATE)),
                addButton(new ImageIcon("png/deplacer.png"), "Modifier un sommet", e -> setState(MODIFY_STATE)),
                addButton(new ImageIcon("png/renommer.png"), "Renommer un sommet", e -> setState(RENAME_STATE)),
                addButton(new ImageIcon("png/couleur.png"), "Changer la couleur d'un sommet", e -> setState(COLOR_STATE)),
                addButton(new ImageIcon("png/effacer.png"), "Supprimer un sommet", e -> setState(DELETE_STATE)),
        };

        addSeparator();

        JButton[] connectButtons = {
                addButton(new ImageIcon("png/lien-connecte.png"), "Relier deux sommets", e -> setState(CONNECT_STATE)),

        };

        for (JButton button : SommetButton) {
            if (button.getToolTipText() == null) {
                button.setEnabled(false);
            }
        }

        for (JButton button : connectButtons) {
            if (button.getToolTipText() == null) {
                button.setEnabled(false);
            }
        }
    }

    public JButton addButton(ImageIcon icon, String tooltip, ActionListener actionListener) {
        JButton button = new JButton(icon);
        button.setToolTipText(tooltip);
        if (actionListener != null) {
            button.addActionListener(actionListener);
        }
        add(button);
        return button;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }


}