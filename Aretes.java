import java.awt.*;

/*

Sofiane Fares
Github : github.com/FaresSofiane

*/

public class Aretes {

    Sommet g1 = null;
    Sommet g2 = null;
    int x1 = 0;
    int y1 = 0;
    int x2 = 0;
    int y2 = 0;

    String Nom_Connection;


    String name = null;
    Color couleur = Color.blue;

    public Aretes(Sommet g1, Sommet g2) {
        this.g1 = g1;
        this.g2 = g2;
        this.Nom_Connection = g1.getName() + "<->" + g2.getName();
    }

    public void update() {
        this.Nom_Connection = g1.getName() + "<->" + g2.getName();

    }

    public Color getCouleur() {

        return this.couleur;
    }

    public void setCouleur(Color c) {

        this.couleur = c;
    }

    public Sommet getG1() {

        return this.g1;
    }

    public String getNom_Connection() {
        return this.Nom_Connection;
    }

    public Sommet getG2() {

        return this.g2;
    }

    public String getName() {

        return this.name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getX1() {

        return this.x1;
    }

    public int getY1() {

        return this.y1;
    }

    public int getX2() {

        return this.x2;
    }

    public int getY2() {

        return this.y2;
    }


    public Graphics Create(Graphics g, int Hauteur_ToolBar, int Taille) {

        this.x1 = this.g1.x + Taille / 2;
        this.y1 = this.g1.y + Taille / 2 + Hauteur_ToolBar;
        this.x2 = this.g2.x + Taille / 2;
        this.y2 = this.g2.y + Taille / 2 + Hauteur_ToolBar;

        Graphics2D L1 = (Graphics2D) g;
        L1.setStroke(new BasicStroke(5));


        L1.setPaint(this.getCouleur());
        L1.drawLine(this.x1, this.y1, this.x2, this.y2);
        if (this.getName() != null) {
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(this.getName());
            int textHeight = fm.getHeight();
            L1.setFont(new Font("Arial", Font.PLAIN, 14));
            int textX = (x1 + x2) / 2 - (textWidth / 2) + 25;
            int textY = (y1 + y2) / 2 + (textHeight / 4) + 25;
            L1.drawString(this.getName(), textX, textY);
        }

        return g;
    }

}
