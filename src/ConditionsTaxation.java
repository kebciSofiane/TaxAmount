import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConditionsTaxation {
    ArrayList<ConditionTaxation> ConditionTaxationList;

    public ConditionsTaxation() {
        this.ConditionTaxationList = new ArrayList<>();
    }

    public ArrayList<ConditionTaxation> getConditionsTaxation() throws DocumentException {
        // Charger le fichier XML
        File xmlFile = new File("src/data/client.xml");
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(xmlFile);

        // Récupérer l'élément racine (ServiceResponse)
        Element rootElement = document.getRootElement();

        // Récupérer l'élément Response
        Element responseElement = rootElement.element("Response");

        // Récupérer l'élément Object (Collection)
        Element objectElement = responseElement.element("Object");

        // Récupérer la liste des éléments ObjectClient
        List<Element> objectClientElements = objectElement.elements("ObjectClient");

        // Parcourir la liste des clients
        for (Element clientElement : objectClientElements) {
            String taxePortDu = clientElement.elementText("taxePortDu");
            String idClient = clientElement.elementText("idClient");
            String raisonSociale = clientElement.elementText("raisonSociale");
            String useTaxePortDuGenerale = clientElement.elementText("useTaxePortDuGenerale");
            String useTaxePortPayeGenerale = clientElement.elementText("useTaxePortPayeGenerale");

            ConditionTaxation conditionTaxation= new ConditionTaxation(taxePortDu,idClient,
                    raisonSociale, useTaxePortDuGenerale,useTaxePortPayeGenerale);
            ConditionTaxationList.add(conditionTaxation);

        }
        return ConditionTaxationList;
    }
}
