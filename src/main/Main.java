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

        double shipperPrice = determinePrice(priceList, shipperId, zone);
        double recipientPrice = determinePrice(priceList, recipientId, zone);


        System.out.println(shipperPrice);
        System.out.println(recipientPrice);
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
}