import java.awt.*;

public class Connection {

    Graph g1 = null;
    Graph g2 = null;

    String name = null;
    Color couleur = Color.blue;

    public Connection(Graph g1 , Graph g2){
        this.g1 = g1;
        this.g2 = g2;
    }

    public void setCouleur(Color c){

        this.couleur = c;
    }

    public Color getCouleur(){

        return this.couleur;
    }

    public Graph getG1(){

        return this.g1;
    }

    public Graph getG2(){

        return this.g2;
    }

    public void setName(String name){

        this.name = name;
    }

    public String getName(){

        return this.name;
    }

    public Graphics Create(Graphics g, int Hauteur_ToolBar, int Taille){

        int x1 = this.g1.x + Taille;
        int y1 = this.g1.y + Hauteur_ToolBar + Taille  ;
        int x2 = this.g2.x + Taille;
        int y2 = this.g2.y + Hauteur_ToolBar + Taille  ;
        System.out.println("x1 : " + x1 + " y1 : " + y1 + " x2 : " + x2 + " y2 : " + y2);
        System.out.println(Hauteur_ToolBar);
        Graphics2D L1 = (Graphics2D) g;
        L1.setStroke(new BasicStroke(3));
        L1.setColor(this.getCouleur());
        L1.drawLine(x1, y1, x2, y2);
        if (this.getName() != null){
            L1.drawString(this.getName(),(x1+x2)/2,(y1+y2)/2);
        }

        return g;
    }

}
