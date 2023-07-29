package main;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConditionsTaxation {
    ArrayList<ConditionTaxation> conditionTaxationList;

    public ConditionsTaxation() {
        this.conditionTaxationList = new ArrayList<>();
    }

    public ArrayList<ConditionTaxation> getConditionsTaxation() throws DocumentException {
        // Charger le fichier XML
        File xmlFile = new File("src/data/conditiontaxation.xml");
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(xmlFile);

        // Récupérer l'élément racine (ServiceResponse)
        Element rootElement = document.getRootElement();

        // Récupérer l'élément Response
        Element responseElement = rootElement.element("Response");

        // Récupérer l'élément Object (Collection)
        Element objectElement = responseElement.element("Object");

        // Récupérer la liste des éléments ObjectClient
        List<Element> objectClientElements = objectElement.elements("ObjectConditionTaxation");

        // Parcourir la liste des clients
        for (Element clientElement : objectClientElements) {
            String taxePortDu = clientElement.elementText("taxePortDu");
            String idClient = clientElement.elementText("idClient");
            String taxePortPaye = clientElement.elementText("taxePortPaye");
            String useTaxePortDuGenerale = clientElement.elementText("useTaxePortDuGenerale");
            String useTaxePortPayeGenerale = clientElement.elementText("useTaxePortPayeGenerale");

            ConditionTaxation conditionTaxation= new ConditionTaxation(taxePortDu,idClient,
                    taxePortPaye, useTaxePortDuGenerale,useTaxePortPayeGenerale);
            conditionTaxationList.add(conditionTaxation);

        }
        return conditionTaxationList;
    }
}