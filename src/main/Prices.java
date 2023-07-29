package main;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Prices {

    ArrayList<Price> prices;

    public Prices() {
        this.prices = new ArrayList<>();
    }

    public ArrayList<Price> getPrices() throws DocumentException {
        // Charger le fichier XML
        File xmlFile = new File("src/data/tarif.xml");
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(xmlFile);

        // Récupérer l'élément racine (ServiceResponse)
        Element rootElement = document.getRootElement();

        // Récupérer l'élément Response
        Element responseElement = rootElement.element("Response");

        // Récupérer l'élément Object (Collection)
        Element objectElement = responseElement.element("Object");

        // Récupérer la liste des éléments ObjectClient
        List<Element> objectClientElements = objectElement.elements("ObjectTarif");

        // Parcourir la liste des clients
        for (Element clientElement : objectClientElements) {
            int idClient = Integer.parseInt(clientElement.elementText("idClient"));
            String idClientHeritageString = clientElement.elementText("idClientHeritage");
            int idClientHeritage;
            if (!Objects.equals(idClientHeritageString, "")) {
                idClientHeritage = Integer.parseInt(idClientHeritageString);
            } else {
                idClientHeritage = 0;
            }
            double montant = Double.parseDouble(clientElement.elementText("montant"));
            int zone = Integer.parseInt(clientElement.elementText("zone"));
            String codeDepartement = clientElement.elementText("codeDepartement");


            Price price  =new Price(idClient,idClientHeritage,montant,zone,codeDepartement);

            prices.add(price);

        }
        return prices;
    }
}
