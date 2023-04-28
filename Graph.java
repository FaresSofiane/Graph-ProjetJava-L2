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

        public void deleteConnection(MouseEvent e){
            ArrayList <Connection> temp_Liste_Connection = new ArrayList <Connection>();

            for (int i = 0; i < Liste_Connection.size(); i++) {
                int x1 = Liste_Connection.get(i).g1.getX() ;
                int y1 = Liste_Connection.get(i).g1.getY() ;
                int x2 = Liste_Connection.get(i).g2.getX() ;
                int y2 = Liste_Connection.get(i).g2.getY() ;

                double distance = Math.abs((y2 - y1) * e.getX() - (x2 - x1) * e.getY() + x2 * y1 - y2 * x1) / Math.sqrt(Math.pow(y2 - y1, 3) + Math.pow(x2 - x1, 3));
                if (distance <= 1){
                    temp_Liste_Connection.add(Liste_Connection.get(i));
                }
            }
            Liste_Connection.removeAll(temp_Liste_Connection);
            temp_Liste_Connection.clear();
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
        public Boolean Find_Connection(int x, int y) {
            for (int i = 0; i < this.Liste_Connection.size(); i++) {
                int x1 = this.Liste_Connection.get(i).getX1();
                int y1 = this.Liste_Connection.get(i).getY1() ;
                int x2 = this.Liste_Connection.get(i).getX2();
                int y2 = this.Liste_Connection.get(i).getY2() ;
                int lineWidth = 5;




                double distance = Math.abs((y2-y1)*x - (x2-x1)*y + x2*y1 - y2*x1) / Math.sqrt((y2-y1)*(y2-y1) + (x2-x1)*(x2-x1));

                // Vérifier si la distance est inférieure ou égale à une tolérance de 1.5 pixels
                if (distance <= 2.5) {
                    // Vérifier si les coordonnées du point (x,y) sont situées entre les coordonnées des points (x1,y1) et (x2,y2) le long de l'axe horizontal et vertical
                    if (Math.min(x1,x2)-2.5 <= x && x <= Math.max(x1,x2)+2.5 && Math.min(y1,y2)-2.5 <= y && y <= Math.max(y1,y2)+2.5) {
                        System.out.println("true");

                        return true;
                    }
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