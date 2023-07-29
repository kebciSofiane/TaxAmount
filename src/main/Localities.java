package main;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Localities {
    ArrayList<Locality> localities;

    public Localities() {
        this.localities = new ArrayList<>();
    }

    public ArrayList<Locality> getLocalities() throws DocumentException {
        // Charger le fichier XML
        File xmlFile = new File("src/data/localite.xml");
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(xmlFile);

        // Récupérer l'élément racine (ServiceResponse)
        Element rootElement = document.getRootElement();

        // Récupérer l'élément Response
        Element responseElement = rootElement.element("Response");

        // Récupérer l'élément Object (Collection)
        Element objectElement = responseElement.element("Object");

        // Récupérer la liste des éléments ObjectClient
        List<Element> objectClientElements = objectElement.elements("ObjectLocalite");

        // Parcourir la liste des clients
        for (Element clientElement : objectClientElements) {
            String postalCode = clientElement.elementText("codePostal");
            String ville = clientElement.elementText("ville");
            String zone = clientElement.elementText("zone");

            Locality locality = new Locality(postalCode,ville,zone);

            localities.add(locality);

        }
        return localities;
    }
}