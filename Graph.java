import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Graph {
    ArrayList <Sommet> sommets = new ArrayList <Sommet>();
    ArrayList <Connection> Liste_Connection = new ArrayList <Connection>();

    int Taille = Main.TAILLE_SOMMET;
    Dimension Dimension_MenuBar;


    public Graph(Dimension Dimension_MenuBar){
        this.Dimension_MenuBar = Dimension_MenuBar;


    }
    public Graph(){
        this.Dimension_MenuBar = null;

    }

        public Sommet addSommet(String Name, int x, int y, Color Couleur){
            Sommet s = new Sommet();
            s.ChangeName(Name);
            s.setCoor(x, y);
            s.setCouleur(Couleur);
            sommets.add(s);
        return sommets.get(sommets.size()-1);
        }

        public void deleteSommet(MouseEvent e){
            ArrayList <Sommet> Liste = new ArrayList <Sommet>();
            ArrayList <Connection> temp_Liste_Connection = new ArrayList <Connection>();

            for (int i = 0; i < sommets.size(); i++) {
                if (e.getX() >= sommets.get(i).x + 10 && e.getX() <= sommets.get(i).x + Taille + 10) {
                    if (e.getY() + Dimension_MenuBar.height + (Taille - 10) >= sommets.get(i).y && e.getY() <= sommets.get(i).y + Taille) {

                        if (Liste_Connection.size() > 0) {
                            for (int j = 0; j < Liste_Connection.size(); j++) {
                                if (Liste_Connection.get(j).g1 != sommets.get(i) && Liste_Connection.get(j).g2 != sommets.get(i)) {
                                    temp_Liste_Connection.add(Liste_Connection.get(j));
                                }
                            }
                            Liste_Connection.clear();
                            Liste_Connection.addAll(temp_Liste_Connection);
                            temp_Liste_Connection.clear();
                        }
                        sommets.remove(i);
                    }

                }
            }
        }

        public void deleteConnection(Connection c){
            Liste_Connection.remove(c);
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

        public Boolean Already_Exist_Connection(Sommet g1, Sommet g2){
            for (int i = 0; i < Liste_Connection.size(); i++) {
                if (Liste_Connection.get(i).g1 == g1 && Liste_Connection.get(i).g2 == g2 || Liste_Connection.get(i).g1 == g2 && Liste_Connection.get(i).g2 == g1) {
                    return true;
                }
            }
            return false;
        }




        public void addConnection(Sommet g1, Sommet g2) {
            Connection c = new Connection(g1, g2);
            Liste_Connection.add(c);

        }

        public void Clear(){
            sommets.clear();
            Liste_Connection.clear();
        }

        public ArrayList<Sommet> getSommets() {
            return sommets;
        }

        public ArrayList<Connection> getConnection() {
            return Liste_Connection;
        }



        public void setSommets(ArrayList<Sommet> sommets) {
            this.sommets = sommets;
        }


}