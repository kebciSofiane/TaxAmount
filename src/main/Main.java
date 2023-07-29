package main;

import org.dom4j.DocumentException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws DocumentException {
        Scanner scanner = new Scanner(System.in);
        int packagesNumber;
        int weight;
        String shipperId;
        String recipientId;
        String clientId;




        Clients clients = new Clients();
        ConditionsTaxation conditionsTaxation  = new ConditionsTaxation();
        Localities localities= new Localities();
        Prices prices = new Prices();

        HashMap<String, Client> clientList = clients.getClients();
        ArrayList<ConditionTaxation> conditionTaxationsList = conditionsTaxation.getConditionsTaxation();
        HashMap<String, Locality> localityList = localities.getLocalities();
        ArrayList<Price> priceList = prices.getPrices();


        System.out.println("Here is our clients: ");
        ArrayList<String> idList= new ArrayList<>();

        for (Client client : clientList.values()){
            idList.add(client.clientId);
            System.out.println("ClientId : "+client.clientId);
            System.out.println("postalCode : "+client.postalCode);
            System.out.println("socialRaison : "+client.socialRaison);
            System.out.println("city : "+client.city);
            System.out.println("---------");
        }

        System.out.println("Veuillez sélectionner l'id de votre expéditeur !");
        shipperId = scanner.nextLine();
        while (!idList.contains(shipperId)){
            System.out.println("L'identifiant du client n'est pas valide : " + shipperId);
            System.out.println("Veuillez sélectionner l'id de votre expéditeur !");
            shipperId = scanner.nextLine();
        }

        System.out.println("Veuillez sélectionner l'id de votre destinataire !");
        recipientId = scanner.nextLine();
        while (!idList.contains(shipperId)){
            System.out.println("L'identifiant du client n'est pas valide : " + recipientId);
            System.out.println("Veuillez sélectionner l'id de votre destinataire !");
            recipientId = scanner.nextLine();
        }

        do {
            try {
                System.out.println("Veuillez sélectionner le nombre de colis !");
                packagesNumber = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Erreur : Vous devez saisir un entier valide.");
            }
        }
        while (true) ;


        


        do {
            try {
                System.out.println("Veuillez sélectionner le poids des colis !");
                weight  = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Erreur : Vous devez saisir un entier valide.");
            }
        }
        while (true) ;


        int userAnswer;

        do {
            try {
                System.out.println("Veuillez sélectionner qui règle le transport :\n" +
                        "1. Expéditeur (port payé)\n" +
                        "2. Destinataire (port dû)");
                userAnswer = Integer.parseInt(scanner.nextLine());
                if(userAnswer==1 || userAnswer==2)  break;
            } catch (NumberFormatException e) {
                System.out.println("Erreur : Vous devez saisir un entier valide.");
            }
        }
        while (true) ;

        if (userAnswer==1){
            clientId =shipperId;
        }else {
            clientId=recipientId;
        }

        int zone =determineZone(localityList, clientList, recipientId);

        double shipperPriceHT = determinePrice(priceList, shipperId, zone);
        double recipientPriceHT = determinePrice(priceList, recipientId, zone);

        double shipperTax;
        double recipientTax;
        ConditionTaxation defaultConditionTaxation= findClientConditionTaxation(conditionTaxationsList,null);

        if ( userAnswer==1 ){
            ConditionTaxation shipperConditionTaxation= findClientConditionTaxation(conditionTaxationsList,shipperId);
            shipperTax = calculateTaxes(userAnswer,shipperConditionTaxation,defaultConditionTaxation);
            recipientTax = 0;
        }else{
            ConditionTaxation recipientConditionTaxation= findClientConditionTaxation(conditionTaxationsList,recipientId);
            recipientTax = calculateTaxes(userAnswer,recipientConditionTaxation,defaultConditionTaxation);
            shipperTax = 0;
        }


        System.out.println(shipperTax);
        System.out.println(recipientTax);

        System.out.println(shipperPriceHT);
        System.out.println(recipientPriceHT);
        System.out.println(zone);
        System.out.println(weight);
        System.out.println(packagesNumber);
        System.out.println(shipperId);
        System.out.println(recipientId);
        System.out.println(clientId);



    }

    private static double determinePrice(ArrayList<Price> priceList, String clientId, int zone) {
        double clientPrice = -1;
        for (Price price : priceList) {
            // Vérifier si l'objet Price correspond aux critères de recherche
            if (price.getIdClient().equals(clientId) && price.getZone().equals(String.valueOf(zone))) {
                // Si c'est le cas, retourner le montant (tarif)
                clientPrice = Double.parseDouble(price.getMontant());
                return clientPrice;
            }

        }
        if (zone - 1 != 0)
            clientPrice = determinePrice(priceList, clientId, zone - 1);
        else
            clientPrice = determinePrice(priceList, "0", zone);

        return clientPrice;
    }

    private static int determineZone(HashMap<String, Locality>  localityList, HashMap<String, Client>  clients, String recipientId) {
        String clientCity =clients.get(recipientId).city;
        String zone = localityList.get(clientCity).zone;
        return Integer.parseInt(zone);
    }

    private static ConditionTaxation findClientConditionTaxation(ArrayList<ConditionTaxation> conditionTaxationsList, String clientId){
        ConditionTaxation conditionClient = null;
        for (ConditionTaxation conditionTaxation : conditionTaxationsList) {
            if (conditionTaxation.getIdClient().equals(clientId)) {
                conditionClient = conditionTaxation;
            }
        }
        if (conditionClient==null) conditionClient=conditionTaxationsList.get(0);
        return  conditionClient;
    }
    private static double calculateTaxes(int userAnswer, ConditionTaxation conditionTaxation, ConditionTaxation defaultConditionTaxation) {

        double taxesAPayer = 0.0;

        // Vérifier si l'expéditeur règle le transport
        if (userAnswer == 2) {
            if (conditionTaxation.useTaxePortDuGenerale.equals("true")) {
                // Si la taxe pour l'expéditeur doit être celle spécifiée dans la condition de taxation générale
                taxesAPayer += Double.parseDouble(defaultConditionTaxation.taxePortDu);
            } else {
                // Sinon, calculer la taxe en fonction du montant du transport multiplié par le taux spécifique au client
                taxesAPayer += Double.parseDouble(conditionTaxation.taxePortDu);
            }
        }
    else{
        // Vérifier si le destinataire règle le transport
            if (conditionTaxation.useTaxePortPayeGenerale.equals("true")) {
                // Si la taxe pour le destinataire doit être celle spécifiée dans la condition de taxation générale
                taxesAPayer += Double.parseDouble(defaultConditionTaxation.taxePortPaye);
            } else {
                // Sinon, calculer la taxe en fonction du montant du transport multiplié par le taux spécifique au client
                taxesAPayer +=  Double.parseDouble(conditionTaxation.taxePortPaye);
            }
        }

        return taxesAPayer;
    }

}