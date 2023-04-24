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

}
