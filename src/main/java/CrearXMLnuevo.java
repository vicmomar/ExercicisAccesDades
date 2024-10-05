import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class CrearXMLnuevo {
    public static void main(String[] args) throws Exception{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbf.newDocumentBuilder();

        Document document = docBuilder.newDocument();

        Element rootElement = document.createElement("game_of_thrones");
        document.appendChild(rootElement);

        Element familias = document.createElement("familias");
        rootElement.appendChild(familias);

        Element familia = document.createElement("familia");
        familia.setAttribute("nombre","Stark");
        familias.appendChild(familia);

        Element personaje = document.createElement("personaje");
        personaje.setAttribute("id","1");
        familia.appendChild(personaje);

        Element nonbre = document.createElement("nombre");
        nonbre.setTextContent("Eddard Stark");
        personaje.appendChild(nonbre);
        Element titulo = document.createElement("titulo");
        titulo.setTextContent("Lord of Winter");
        personaje.appendChild(titulo);

        Element relaciones = document.createElement("relaciones");
        personaje.appendChild(relaciones);

        Element esposo = document.createElement("esposo");
        esposo.setTextContent("Catelyn Stark");
        relaciones.appendChild(esposo);

        Element hijos = document.createElement("hijos");
        relaciones.appendChild(hijos);
        Element hijo = document.createElement("hijo");
        hijo.setTextContent("Rob Stark");
        Element hija = document.createElement("hija");
        hija.setTextContent("Sansa Stark");
        Element hijo2 = document.createElement("hijo");
        hijo2.setTextContent("Bran Stark");
        Element hija2 = document.createElement("hija");
        hija2.setTextContent("Arya Stark");
        Element hijo3 = document.createElement("hijo");
        hijo2.setTextContent("Arya Stark");

        hijos.appendChild(hijo);
        hijos.appendChild(hija);
        hijos.appendChild(hijo2);
        hijos.appendChild(hija2);
        hijos.appendChild(hijo3);



        //Serializar el árbol DOM utilizando API JAXP
        TransformerFactory transFactory = TransformerFactory.newInstance();
        Transformer idTransform = transFactory.newTransformer();
        idTransform.setOutputProperty(OutputKeys.METHOD, "xml");
        idTransform.setOutputProperty(OutputKeys.INDENT, "yes");
        Source input = new DOMSource(document);
        Result output = new StreamResult(new File("./src/main/resources/gameOfThrones.xml"));
        idTransform.transform(input, output);
        System.out.println("Archivo creado satisfactoriamente");
    }
}
