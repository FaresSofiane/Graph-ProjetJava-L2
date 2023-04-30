import java.awt.*;
import java.util.*;
import java.io.File;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class XML_Graph {

    Graph G = null;
    File fichier = null;

    public XML_Graph(Graph G){
        this.G = G;
    }

    public ArrayList <Sommet> getListe(){
        return this.G.getSommets();
    }



    public void setFile(File fichier){
        this.fichier = fichier;
    }

    public File getFile(){
        if (fichier == null){
            fichier = new File("XML_résultat.xml");
        }
        return this.fichier;
    }

    public void createXML(){
        try
        {

            DocumentBuilderFactory XML_Fabrique_Constructeur = DocumentBuilderFactory.newInstance();
            DocumentBuilder XML_Constructeur = XML_Fabrique_Constructeur.newDocumentBuilder();

            Document XML_Document = XML_Constructeur.newDocument();
            Element Graph = XML_Document.createElement("CreateurGraph");
            XML_Document.appendChild(Graph);

            for (int i = 0; i < this.G.getSommets().size(); i++){
                Element graph = XML_Document.createElement("Graph");
                Graph.appendChild(graph);
                Attr attribut = XML_Document.createAttribute("Name");
                attribut.setValue(this.G.getSommets().get(i).getName());
                graph.setAttributeNode(attribut);
                Element x = XML_Document.createElement("x");
                x.appendChild(XML_Document.createTextNode(Integer.toString(this.G.getSommets().get(i).getX())));
                graph.appendChild(x);
                Element y = XML_Document.createElement("y");
                y.appendChild(XML_Document.createTextNode(Integer.toString(this.G.getSommets().get(i).getY())));
                graph.appendChild(y);
                Element couleur = XML_Document.createElement("Couleur");

                couleur.appendChild(XML_Document.createTextNode(String.valueOf(this.G.getSommets().get(i).getCouleur().getRGB())));
                graph.appendChild(couleur);

            }

            for (int i = 0; i<this.G.getAretes().size();i++){

                Element arete = XML_Document.createElement("Arete");
                Graph.appendChild(arete);
                Attr attribut = XML_Document.createAttribute("Name-Connection");
                attribut.setValue(this.G.getAretes().get(i).getNom_Connection());
                arete.setAttributeNode(attribut);
                Element Name = XML_Document.createElement("Name");
                Name.appendChild(XML_Document.createTextNode(this.G.getAretes().get(i).getName()));
                arete.appendChild(Name);
                Element g1 = XML_Document.createElement("g1");
                g1.appendChild(XML_Document.createTextNode(this.G.getAretes().get(i).getG1().getName()));
                arete.appendChild(g1);
                Element g2 = XML_Document.createElement("g2");
                g2.appendChild(XML_Document.createTextNode(this.G.getAretes().get(i).getG2().getName()));
                arete.appendChild(g2);
                Element couleur = XML_Document.createElement("Couleur");
                couleur.appendChild(XML_Document.createTextNode(String.valueOf(this.G.getAretes().get(i).getCouleur().getRGB())));
                arete.appendChild(couleur);


            }

            TransformerFactory XML_Fabrique_Transformeur = TransformerFactory.newInstance();
            Transformer XML_Transformeur = XML_Fabrique_Transformeur.newTransformer();
            DOMSource source = new DOMSource(XML_Document);
            if (fichier == null){
                System.out.println("fichier null");
                fichier = new File("XML_résultat.xml");
                StreamResult resultat = new StreamResult(fichier);
                XML_Transformeur.transform(source, resultat);
            }
            else{
                StreamResult resultat = new StreamResult(fichier);
                XML_Transformeur.transform(source, resultat);
            }

        }
        catch (ParserConfigurationException pce)
        {
            pce.printStackTrace();
        }
        catch (TransformerException tfe)
        {
            tfe.printStackTrace();
        }
    }

    public void Save(File fichier){
        this.fichier = fichier;
        System.out.println("Enregistrement du fichier : "+fichier.getName()+" ..." + fichier.getAbsolutePath());
        createXML();
    }

    public void Open(File fichier){
        this.G.Clear();
        try
        {
            DocumentBuilderFactory XML_Fabrique_Constructeur = DocumentBuilderFactory.newInstance();
            DocumentBuilder XML_Constructeur = XML_Fabrique_Constructeur.newDocumentBuilder();

            Document XML_Document = XML_Constructeur.parse(fichier);
            XML_Document.getDocumentElement().normalize();
            NodeList Liste_Graph = XML_Document.getElementsByTagName("Graph");
            for (int i = 0; i < Liste_Graph.getLength(); i++)
            {
                Node noeud = Liste_Graph.item(i);
                System.out.println("\nCurrent Element :" + noeud.getNodeName());
                if (noeud.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element) noeud;
                    String Name = element.getAttribute("Name");
                    int x = Integer.parseInt(element.getElementsByTagName("x").item(0).getTextContent());
                    int y = Integer.parseInt(element.getElementsByTagName("y").item(0).getTextContent());
                    String Couleur = element.getElementsByTagName("Couleur").item(0).getTextContent();


                    G.addSommet(Name, x, y, new Color(Integer.parseInt(Couleur)));


                }
            }


            NodeList Liste_Arete = XML_Document.getElementsByTagName("Arete");
            for (int i = 0; i < Liste_Arete.getLength(); i++)
            {
                Node noeud = Liste_Arete.item(i);
                System.out.println("\nCurrent Element :" + noeud.getNodeName());
                if (noeud.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element) noeud;
                    String g1 = element.getElementsByTagName("g1").item(0).getTextContent();
                    String g2 = element.getElementsByTagName("g2").item(0).getTextContent();
                    String Couleur = element.getElementsByTagName("Couleur").item(0).getTextContent();
                    String Name = element.getElementsByTagName("Name").item(0).getTextContent();

                    Aretes arete = new Aretes(G.Find_Sommet_Name(g1), G.Find_Sommet_Name(g2));
                    arete.setCouleur(Couleur.equals("0") ? Color.BLACK : new Color(Integer.parseInt(Couleur)));
                    if (!Name.equals("")){
                        arete.setName(Name);
                    }

                    this.G.getAretes().add(arete);




                }
            }
            this.fichier = fichier;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }



}
