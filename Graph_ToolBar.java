import javax.swing.*;
import javax.swing.plaf.PanelUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Graph_ToolBar extends JToolBar {

    private static final String ADD_STATE = "Ajouter";
    private static final String MODIFY_STATE = "Modifier_Position";
    private static final String DELETE_STATE = "Supprimer";
    private static final String CLEAR_STATE = "ToutEffacer";
    private static final String CONNECT_STATE = "Connect";
    private static final String EDIT_CONNECT_STATE = "Edit_Connect";
    private static final String ADD_TEXT_CONNECT_STATE = "Add_Text_Connect";
    private static final String CHANGE_COLOR_CONNECT_STATE = "Change_Color_Connect";


    File file = null;
    XML_Graph xml = null;
    private String state = ADD_STATE;

    public Graph_ToolBar() {
        setFloatable(false);




    }

    public void Init_Button() {
        addSeparator();

        JButton[] SommetButton = {
                addButton(new ImageIcon("png/creation.png"), "Ajouter un sommet", e -> setState(ADD_STATE)),
                addButton(new ImageIcon("png/pencil.png"), "Modifier", e -> setState(MODIFY_STATE)),
                addButton(new ImageIcon("png/remove.png"), "Supprimer", e -> setState(DELETE_STATE)),
                addButton(new ImageIcon("png/delete-all.png"), "Tout effacer", e -> setState(CLEAR_STATE))
        };

        addSeparator();

        JButton[] connectButtons = {
                addButton(new ImageIcon("png/lien-connecte.png"), "Relier deux sommets", e -> setState(CONNECT_STATE)),
                addButton(new ImageIcon("png/lien-delete.png"), "Modifier un lien", e -> setState(EDIT_CONNECT_STATE)),
                addButton(new ImageIcon("png/lien-add-text.png"), "Ajouter un texte à un lien", e -> setState(ADD_TEXT_CONNECT_STATE)),
                addButton(new ImageIcon("png/lien-color.png"), "Changer la couleur d'un lien", e -> setState(CHANGE_COLOR_CONNECT_STATE))
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

    private void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
/*
    private void SaveAs(){
        System.out.println("SaveAs");
    }

private ActionListener SaveAs = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

                if (file == null) {
                    xml = new XML_Graph(GraphDisplay.G.getSommets());
                    xml.createXML();
                } else {
                    System.out.println("Save");
                }
            }

    };

   private ActionListener Save = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (file == null) {
                xml = new XML_Graph(GraphDisplay.G.getSommets());
                xml.createXML();
            } else {
                System.out.println("Save");
            }
        }
    };

    public ActionListener LoadAs = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("LoadAs");




        }
    };
    */


}