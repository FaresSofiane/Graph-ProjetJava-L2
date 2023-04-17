import java.awt.*;
import java.util.ArrayList;

public class Graph {
    int x,y = 0;
    Color couleur = Color.blue;
    String Name;
    ArrayList<Graph> Connection = new ArrayList<>();



    public Graph(String n){
        this.Name = n;
    }
    public String getName(){
        return Name;
    }
    public void ChangeName(String nameb){
        this.Name = nameb;
    }

    public void AddConnection(Graph Cible) {
        if (!this.Connection.contains(Cible)) {
            this.Connection.add(Cible);
        }
    }


    public void GetConnection(){
        int size = this.Connection.size();
        if (size == 0){
            System.out.println(this.Name+" ne possede pas de connection");
        } else {
            for (int i = 0; i < size; i++) {
                System.out.println(this.Name + " est connecté a " + this.Connection.get(i).Name);
            }
        }
    }
    public void DelConnection(Graph cible){
        ArrayList<Graph> temp = new ArrayList<>();

        int size = this.Connection.size();
        if (size == 0){
            System.out.println(this.Name+" ne possede pas de connection");
        } else {
            for (int i = 0; i < size; i++) {
                if(this.Connection.get(i) != cible){
                    temp.add(this.Connection.get(i));
                }
            }
            this.Connection = temp;
        }
    }

    public void setCoor(int x, int y){

        this.x = x;
        this.y = y;

    }




}
