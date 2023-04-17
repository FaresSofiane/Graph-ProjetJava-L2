import java.util.ArrayList;

public class Graph {

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

    public void AddConnection(Graph Cible){

        this.Connection.add(Cible);

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

    public static void main(String[] args) {
        Graph g1 = new Graph("A");
        Graph g2 = new Graph("B");
        Graph g3 = new Graph("C");
        Graph g4 = new Graph("D");
        Graph g5 = new Graph("E");
        g1.AddConnection(g2);
        g1.AddConnection(g3);
        g1.AddConnection(g4);
        g1.AddConnection(g5);
        g1.GetConnection();
        g1.DelConnection(g3);
        g1.GetConnection();

        g2.GetConnection();

    }
}
