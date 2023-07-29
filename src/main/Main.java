package main;

import org.dom4j.DocumentException;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws DocumentException {
        Scanner scanner = new Scanner(System.in);
        int packagesNumber;
        int weight;
        String shipperId;
        String recipientId;



        Clients clients = new Clients();
        ConditionsTaxation conditionsTaxation  = new ConditionsTaxation();
        Localities localities= new Localities();
        Prices prices = new Prices();

        ArrayList<Client> clientList = clients.getClients();
        ArrayList<ConditionTaxation> conditionTaxationsList = conditionsTaxation.getConditionsTaxation();
        ArrayList<Locality> localityList = localities.getLocalities();
        ArrayList<Price> priceList = prices.getPrices();


        System.out.println("Here is our clients: ");
        ArrayList<String> idList= new ArrayList<>();

        for (Client client : clientList){
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

        System.out.println(weight);
        System.out.println(packagesNumber);
        System.out.println(shipperId);
        System.out.println(recipientId);



    }
}