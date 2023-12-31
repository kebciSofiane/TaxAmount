package main.dataManagement;

import main.singleData.Price;
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
        File xmlFile = new File("src/data/tarif.xml");
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(xmlFile);

        Element rootElement = document.getRootElement();
        Element responseElement = rootElement.element("Response");
        Element objectElement = responseElement.element("Object");
        List<Element> objectClientElements = objectElement.elements("ObjectTarif");

        for (Element clientElement : objectClientElements) {
            int idClient = Integer.parseInt(clientElement.elementText("idClient"));
            String idClientHeritageString = clientElement.elementText("idClientHeritage");
            int idClientHeritage;
            if (!Objects.equals(idClientHeritageString, "")) {
                idClientHeritage = Integer.parseInt(idClientHeritageString);
            } else idClientHeritage = 0;

            double montant = Double.parseDouble(clientElement.elementText("montant"));
            int zone = Integer.parseInt(clientElement.elementText("zone"));
            String codeDepartement = clientElement.elementText("codeDepartement");


            Price price  =new Price(idClient,idClientHeritage,montant,zone,codeDepartement);

            prices.add(price);

        }
        return prices;
    }
}
