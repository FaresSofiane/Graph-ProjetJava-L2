import java.awt.*;
import java.util.ArrayList;

public class Graph {
    int x,y = 0;
    Color couleur = Color.blue;

    String Name;




    public Graph(String n){
        this.Name = n;
    }
    public String getName(){
        return Name;
    }
    public void ChangeName(String nameb){
        this.Name = nameb;
    }




    public void setCoor(int x, int y){

        this.x = x;
        this.y = y;

    }

    public void setCouleur(Color c){
        this.couleur = c;
    }

    public Color getCouleur(){
        return this.couleur;
    }




}
