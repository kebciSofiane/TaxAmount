package main.dataManagement;

import main.singleData.Client;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.File;
import java.util.HashMap;
import java.util.List;


public class Clients {
    HashMap<Integer, Client> clients;

    public Clients() {
        this.clients = new HashMap<>();
    }

    public HashMap<Integer, Client> getClients() throws DocumentException {
        File xmlFile = new File("src/data/client.xml");
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(xmlFile);
        Element rootElement = document.getRootElement();
        Element responseElement = rootElement.element("Response");
        Element objectElement = responseElement.element("Object");
        List<Element> objectClientElements = objectElement.elements("ObjectClient");

        // Parcourir la liste des clients
        for (Element clientElement : objectClientElements) {
            String postalCode = clientElement.elementText("codePostal");
            int clientId = Integer.parseInt(clientElement.elementText("idClient"));
            String socialRaison = clientElement.elementText("raisonSociale");
            String city = clientElement.elementText("ville");

            Client client = new Client(postalCode,clientId,socialRaison,city);
            clients.put(clientId,client);

        }
        return clients;
    }



}
