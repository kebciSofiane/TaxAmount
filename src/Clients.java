import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Clients {
    ArrayList<Client> clients;

    public Clients() {
        this.clients = new ArrayList<>();
    }

    public ArrayList<Client> getClients() throws DocumentException {
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
            String postalCode = clientElement.elementText("codePostal");
            String clientId = clientElement.elementText("idClient");
            String socialRaison = clientElement.elementText("raisonSociale");
            String city = clientElement.elementText("ville");

            Client client = new Client(postalCode,clientId,socialRaison,city);
            clients.add(client);

        }
        return clients;
    }



}
