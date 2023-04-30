import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Graph {
    ArrayList <Sommet> sommets = new ArrayList <Sommet>();
    ArrayList <Aretes> aretes = new ArrayList <Aretes>();

    int Taille = Main.TAILLE_SOMMET;
    Dimension Dimension_MenuBar;


    public Graph(Dimension Dimension_MenuBar){
        this.Dimension_MenuBar = Dimension_MenuBar;


    }
    public Graph(){
        this.Dimension_MenuBar = null;

    }

    public void setGraph(){
        sommets.clear();
        aretes.clear();

        this.sommets = new ArrayList <Sommet>();

    }

    public Sommet addSommet(String Name, int x, int y, Color Couleur){
        Sommet s = new Sommet();
        for (int i = 0; i < sommets.size(); i++) {
            if (sommets.get(i).getName().equals(Name)) {
                Name = Name + "1";
            }
        }
        s.ChangeName(Name);
        s.setCoor(x, y);
        s.setCouleur(Couleur);
        sommets.add(s);
        return sommets.get(sommets.size()-1);
    }

    public void deleteSommet(MouseEvent e){
        ArrayList <Sommet> Liste = new ArrayList <Sommet>();
        ArrayList <Aretes> temp_aretes = new ArrayList <Aretes>();

        for (int i = 0; i < sommets.size(); i++) {
            if (e.getX() >= sommets.get(i).x + 10 && e.getX() <= sommets.get(i).x + Taille + 10) {
                if (e.getY() + Dimension_MenuBar.height + (Taille - 10) >= sommets.get(i).y && e.getY() <= sommets.get(i).y + Taille) {

                    if (aretes.size() > 0) {
                        for (int j = 0; j < aretes.size(); j++) {
                            if (aretes.get(j).g1 != sommets.get(i) && aretes.get(j).g2 != sommets.get(i)) {
                                temp_aretes.add(aretes.get(j));
                            }
                        }
                        aretes.clear();
                        aretes.addAll(temp_aretes);
                        temp_aretes.clear();
                    }
                    sommets.remove(i);
                }

            }
        }
    }

    public void deleteAretes(Aretes c){
        aretes.remove(c);
    }

    public Sommet Find_Sommet_Name(String Name){
        for (int i = 0; i < sommets.size(); i++) {
            if (sommets.get(i).getName().equals(Name)) {
                return sommets.get(i);
            }
        }
        return null;
    }



    public Sommet Find_Sommet(int x, int y){
        for (int i = 0; i < this.sommets.size(); i++) {
            if (x >= this.sommets.get(i).getX() + 10 && x <= this.sommets.get(i).getX() + Taille + 10) {
                if (y + Dimension_MenuBar.height + (Taille - 10) >= this.sommets.get(i).getY() && y <= this.sommets.get(i).getY() + Taille) {
                    return this.sommets.get(i);

                }
            }

        }
        return null;
    }

    public Boolean Already_Exist_Aretes(Sommet g1, Sommet g2){
        for (int i = 0; i < aretes.size(); i++) {
            if (aretes.get(i).g1 == g1 && aretes.get(i).g2 == g2 || aretes.get(i).g1 == g2 && aretes.get(i).g2 == g1) {
                return true;
            }
        }
        return false;
    }




    public void addConnection(Sommet g1, Sommet g2) {
        Aretes c = new Aretes(g1, g2);
        aretes.add(c);

    }

    public void Clear(){
        sommets.clear();
        aretes.clear();
    }

    public ArrayList<Sommet> getSommets() {
        return sommets;
    }

    public ArrayList<Aretes> getAretes() {
        return aretes;
    }



    public void setSommets(ArrayList<Sommet> sommets) {
        this.sommets = sommets;
    }


}