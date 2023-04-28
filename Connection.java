import java.awt.*;

public class Connection {

    Sommet g1 = null;
    Sommet g2 = null;
    int x1 = 0;
    int y1 = 0;
    int x2 = 0;
    int y2 = 0;


    String name = null;
    Color couleur = Color.blue;

    public Connection(Sommet g1 , Sommet g2){
        this.g1 = g1;
        this.g2 = g2;
    }

    public void setCouleur(Color c){

        this.couleur = c;
    }

    public Color getCouleur(){

        return this.couleur;
    }

    public Sommet getG1(){

        return this.g1;
    }

    public Sommet getG2(){

        return this.g2;
    }

    public void setName(String name){

        this.name = name;
    }

    public String getName(){

        return this.name;
    }
    public int getX1(){

        return this.x1;
    }

    public int getY1(){

        return this.y1;
    }

    public int getX2(){

        return this.x2;
    }

    public int getY2(){

        return this.y2;
    }


    public Graphics Create(Graphics g, int Hauteur_ToolBar, int Taille){

        int x1 = this.g1.x ;
        int y1 = this.g1.y ;
        int x2 = this.g2.x ;
        int y2 = this.g2.y ;


        Graphics2D L1 = (Graphics2D) g;
        L1.setStroke(new BasicStroke(5));

        this.x1 = x1 + Taille/2;
        this.y1 = y1 + Taille/2 + Hauteur_ToolBar;
        this.x2 = x2 + Taille/2;
        this.y2 = y2 + Taille/2 + Hauteur_ToolBar;


        L1.setPaint(this.getCouleur());
        L1.drawLine(x1 + Taille/2, y1+Hauteur_ToolBar + Taille/2, x2 + Taille/2, y2 + Hauteur_ToolBar + Taille/2);
        if (this.getName() != null){
            L1.drawString(this.getName(),(x1+x2)/2,(y1+y2)/2);
        }

        return g;
    }

}
