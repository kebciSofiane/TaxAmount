package main.dataManagement;

import main.singleData.Locality;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class Localities {
    HashMap<String, Locality> localities;

    public Localities() {
        this.localities = new HashMap<>();
    }

    public HashMap<String, Locality> getLocalities() throws DocumentException {
        File xmlFile = new File("src/data/localite.xml");
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(xmlFile);
        Element rootElement = document.getRootElement();
        Element responseElement = rootElement.element("Response");
        Element objectElement = responseElement.element("Object");
        List<Element> objectClientElements = objectElement.elements("ObjectLocalite");

        for (Element clientElement : objectClientElements) {
            String postalCode = clientElement.elementText("codePostal");
            String ville = clientElement.elementText("ville");
            int zone = Integer.parseInt(clientElement.elementText("zone"));

            Locality locality = new Locality(postalCode,ville,zone);

            localities.put(ville, locality);

        }
        return localities;
    }
}
