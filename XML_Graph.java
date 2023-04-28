import java.awt.*;
import java.util.*;
import java.io.File;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class XML_Graph {

    ArrayList <Sommet> Liste = new ArrayList <Sommet>();
    File fichier = null;

    public XML_Graph(ArrayList<Sommet> Liste){
        this.Liste = Liste;
    }

    public ArrayList <Sommet> getListe(){
        return this.Liste;
    }

    public void PrintListe(ArrayList<Sommet> Liste){
        for (int i = 0; i < Liste.size(); i++){
            System.out.println("Graph " + i + " : " + Liste.get(i).getName());
        }
    }

    public void setFile(File fichier){
        this.fichier = fichier;
    }

    public File createXML(){
        try
        {
            DocumentBuilderFactory XML_Fabrique_Constructeur = DocumentBuilderFactory.newInstance();
            DocumentBuilder XML_Constructeur = XML_Fabrique_Constructeur.newDocumentBuilder();

            Document XML_Document = XML_Constructeur.newDocument();
            Element Graph = XML_Document.createElement("CreateurGraph");
            XML_Document.appendChild(Graph);

            for (int i = 0; i < Liste.size(); i++){
                Element graph = XML_Document.createElement("Graph");
                Graph.appendChild(graph);
                Attr attribut = XML_Document.createAttribute("Name");
                attribut.setValue(Liste.get(i).getName());
                graph.setAttributeNode(attribut);
                Element x = XML_Document.createElement("x");
                x.appendChild(XML_Document.createTextNode(Integer.toString(Liste.get(i).getX())));
                graph.appendChild(x);
                Element y = XML_Document.createElement("y");
                y.appendChild(XML_Document.createTextNode(Integer.toString(Liste.get(i).getY())));
                graph.appendChild(y);
                Element couleur = XML_Document.createElement("Couleur");

                couleur.appendChild(XML_Document.createTextNode(String.valueOf(Liste.get(i).getCouleur().getRGB())));
                graph.appendChild(couleur);
            }

            TransformerFactory XML_Fabrique_Transformeur = TransformerFactory.newInstance();
            Transformer XML_Transformeur = XML_Fabrique_Transformeur.newTransformer();
            DOMSource source = new DOMSource(XML_Document);
            if (fichier == null){
                fichier = new File("XML_résultat.xml");
                StreamResult resultat = new StreamResult(fichier);
                XML_Transformeur.transform(source, resultat);
            }
            else{
                StreamResult resultat = new StreamResult(fichier);
                XML_Transformeur.transform(source, resultat);
            }


            System.out.println("Le fichier XML a été généré !");
        }
        catch (ParserConfigurationException pce)
        {
            pce.printStackTrace();
        }
        catch (TransformerException tfe)
        {
            tfe.printStackTrace();
        }
        return fichier;
    }

    public void Save(File fichier){
        this.fichier = fichier;
        createXML();
    }

    public ArrayList <Sommet> Open(File fichier){

        this.Liste = new ArrayList<Sommet>();
        try
        {
            DocumentBuilderFactory XML_Fabrique_Constructeur = DocumentBuilderFactory.newInstance();
            DocumentBuilder XML_Constructeur = XML_Fabrique_Constructeur.newDocumentBuilder();
            Document XML_Document = XML_Constructeur.parse(new File(fichier.getAbsolutePath()));
            XML_Document.getDocumentElement().normalize();
            NodeList Liste_Graph = XML_Document.getElementsByTagName("Graph");
            for (int i = 0; i < Liste_Graph.getLength(); i++)
            {
                Node noeud = Liste_Graph.item(i);
                if (noeud.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element) noeud;
                    String Name = element.getAttribute("Name");
                    int x = Integer.parseInt(element.getElementsByTagName("x").item(0).getTextContent());
                    int y = Integer.parseInt(element.getElementsByTagName("y").item(0).getTextContent());
                    String Couleur = element.getElementsByTagName("Couleur").item(0).getTextContent();
                    Sommet graph = new Sommet();
                    graph.ChangeName(Name);
                    graph.setCoor(x, y);
                    Color C = Couleur.equals("0") ? Color.BLACK : new Color(Integer.parseInt(Couleur));
                    graph.setCouleur(C);
                    this.Liste.add(graph);
                    System.out.println("Graph " + i + " : " + Name + " // Coordonnées : " + x + " " + y + " // Couleur : " + C);

                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return this.Liste;

    }



}
