import java.awt.*;

/*

Sofiane Fares
Github : github.com/FaresSofiane

*/

public class Sommet {
    int x = 0;
    int y = 0;
    Color Couleur = Color.blue;
    String Name = "null";

    public Sommet() {
    }

    public String getName() {

        return Name;
    }

    public void ChangeName(String name) {

        this.Name = name;
    }

    public int getX() {

        return this.x;
    }

    public int getY() {

        return this.y;
    }

    public void setCoor(int x, int y) {

        this.x = x;
        this.y = y;
    }

    public Color getCouleur() {

        return this.Couleur;
    }

    public void setCouleur(Color c) {

        this.Couleur = c;
    }

    public Graphics Create(Graphics g, int Hauteur_ToolBar, int Taille) {

        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(this.getName());
        int textHeight = fm.getHeight();
        int centerX = x + Taille / 2;
        int centerY = y + Taille / 2;
        int textX = centerX - (textWidth / 2);
        int textY = centerY + (textHeight / 4);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.BLACK);
        g2d.drawOval(this.x, this.y + Hauteur_ToolBar, Taille, Taille);
        g2d.setColor(this.Couleur);
        g2d.fillOval(this.x, this.y + Hauteur_ToolBar, Taille, Taille);
        g2d.setColor(Color.black);
        g2d.setFont(new Font("Arial", Font.PLAIN, 24));
        g2d.drawString(this.getName(), textX, textY + Hauteur_ToolBar);


        return g;
    }


}
