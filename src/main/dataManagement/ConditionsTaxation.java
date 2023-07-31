package main.dataManagement;

import main.singleData.ConditionTaxation;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ConditionsTaxation {
    HashMap<Integer, ConditionTaxation> conditionTaxationList;

    public ConditionsTaxation() {
        this.conditionTaxationList = new HashMap<>();
    }

    public HashMap<Integer, ConditionTaxation> getConditionsTaxation() throws DocumentException {
        File xmlFile = new File("src/data/conditiontaxation.xml");
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(xmlFile);
        Element rootElement = document.getRootElement();
        Element responseElement = rootElement.element("Response");
        Element objectElement = responseElement.element("Object");
        List<Element> objectClientElements = objectElement.elements("ObjectConditionTaxation");

        for (Element clientElement : objectClientElements) {
            double taxePortDu = Double.parseDouble(clientElement.elementText("taxePortDu"));
            String idClientString = clientElement.elementText("idClient");
            int idClient;
            if (!Objects.equals(idClientString, "")) {
                 idClient = Integer.parseInt(idClientString);
            } else idClient = 0;

            double taxePortPaye = Double.parseDouble(clientElement.elementText("taxePortPaye"));
            boolean useTaxePortDuGenerale = Boolean.parseBoolean(clientElement.elementText("useTaxePortDuGenerale"));
            boolean useTaxePortPayeGenerale = Boolean.parseBoolean(clientElement.elementText("useTaxePortPayeGenerale"));

            ConditionTaxation conditionTaxation= new ConditionTaxation(taxePortDu,idClient,
                    taxePortPaye, useTaxePortDuGenerale,useTaxePortPayeGenerale);
            conditionTaxationList.put(idClient,conditionTaxation);
        }
        return conditionTaxationList;
    }
}
