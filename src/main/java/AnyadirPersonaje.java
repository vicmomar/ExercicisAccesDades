import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Scanner;

public class AnyadirPersonaje {

    static Scanner scn = new Scanner(System.in);
    public static void main(String[] args) throws Exception {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
        Document document = documentBuilder.parse(new File("./src/main/resources/gameOfThrones.xml"));
        document.getDocumentElement().normalize();


        Element rootElement = document.getDocumentElement();

        NodeList familias = document.getElementsByTagName("familias");
        Element newFamilia = (Element) familias.item(0);
        Element familia = document.createElement("familia");
        familia.setAttribute("nombre","Lannister");
        newFamilia.appendChild(familia);
//
        Element personaje = document.createElement("personaje");
        personaje.setAttribute("id","2");
        familia.appendChild(personaje);
//
        Element nonbre = document.createElement("nombre");
        nonbre.setTextContent("Tyrion Lannister");
        personaje.appendChild(nonbre);
        Element titulo = document.createElement("titulo");
        titulo.setTextContent("Mother of Dragons");
        personaje.appendChild(titulo);
//
        Element relaciones = document.createElement("relaciones");
        personaje.appendChild(relaciones);

        System.out.print("Número de relaciones del personaje: ");
        int numRelaciones = scn.nextInt();
        scn.nextLine();
        for (int i=0; i<numRelaciones; i++) {
            System.out.print("Tipo de relación: ");
            String rel = scn.nextLine().toLowerCase();

            Element relacion =  document.createElement(rel);
            System.out.print("Nombre: ");
            String nomRel = scn.nextLine();
            relacion.setTextContent(nomRel);

            if (rel.contains("hij")){
                if (relaciones.getElementsByTagName("hijos").item(0) != null) {
                    relaciones.getElementsByTagName("hijos").item(0).appendChild(relacion);
                    System.out.println("primer if");
                } else {
                    Element hijos = document.createElement("hijos");
                    relaciones.appendChild(hijos);
                    hijos.appendChild(relacion);
                }
            } else {
                relaciones.appendChild(relacion);
            }
            System.out.println("Relación " + (i + 1) + ": " + rel + ", se llama " + nomRel);
        }

        //Serializar el árbol DOM utilizando API JAXP
//        Transformer idTransforn = TransformerFactory.newInstance(new Transformer())

        TransformerFactory transFactory = TransformerFactory.newInstance();
        Transformer idTransform = transFactory.newTransformer();
        idTransform.setOutputProperty(OutputKeys.METHOD, "xml");
        idTransform.setOutputProperty(OutputKeys.INDENT, "yes");
        Source input = new DOMSource(document);
        Result output = new StreamResult(new File("./src/main/resources/gameOfThrones.xml"));
        idTransform.transform(input, output);
        System.out.println("Archivo actualizado");

    }
}
