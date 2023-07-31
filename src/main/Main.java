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
        int shipperId;
        int recipientId;
        int zone;
        int SenderOrShipperPaysFees;
        double shipperPriceHT;
        double recipientPriceHT;
        double shipperPrice;
        double recipientPrice;
        ConditionTaxation defaultConditionTaxation;

        Clients clients = new Clients();
        ConditionsTaxation conditionsTaxation  = new ConditionsTaxation();
        Localities localities= new Localities();
        Prices prices = new Prices();

        HashMap<Integer, Client> clientList = clients.getClients();
        HashMap<String, Locality> localityList = localities.getLocalities();
        ArrayList<ConditionTaxation> conditionTaxationList = conditionsTaxation.getConditionsTaxation();
        ArrayList<Price> priceList = prices.getPrices();


        ArrayList<Integer> idList = showClientsDetails(clientList);

        shipperId = getShipperId(scanner, idList);

        recipientId = getRecipientId(scanner, idList, shipperId);

        packagesNumber = getPackagesNumber(scanner);

        weight = getWeight(scanner);
        
        SenderOrShipperPaysFees=determineSenderOrShipperPaysFees(scanner);
        
        zone =determineZone(localityList, clientList, recipientId);

        shipperPriceHT = determinePrice(priceList, shipperId, zone);
        
        recipientPriceHT = determinePrice(priceList, recipientId, zone);
        
        defaultConditionTaxation= findClientConditionTaxation(conditionTaxationList,0);

        ShipperAndRecipientFees shipperAndRecipientFees = getShipperAndRecipientFees(SenderOrShipperPaysFees, conditionTaxationList, shipperId, defaultConditionTaxation, recipientId);

        shipperPrice = shipperAndRecipientFees.shipperFees() +shipperPriceHT;

        recipientPrice = shipperAndRecipientFees.recipientFees() +recipientPriceHT;

        showCalculationDetails(shipperId, recipientId, packagesNumber, weight, zone, recipientPriceHT, shipperPriceHT, recipientPrice, shipperPrice);


    }

    private static ArrayList<Integer> showClientsDetails(HashMap<Integer, Client> clientList) {
        System.out.println("Here is our clients: ");
        ArrayList<Integer> idList= new ArrayList<>();
        for (Client client : clientList.values()){
            idList.add(client.clientId);
            System.out.println("ClientId : "+client.clientId);
            System.out.println("postalCode : "+client.postalCode);
            System.out.println("socialRaison : "+client.socialRaison);
            System.out.println("city : "+client.city);
            System.out.println("---------");
        }
        return idList;
    }

    private static int getShipperId(Scanner scanner, ArrayList<Integer> idList) {
        int shipperId;
        System.out.println("Veuillez sélectionner l'id de votre expéditeur !");

        while (true) {
            try {
                shipperId = Integer.parseInt(scanner.nextLine());
                if (idList.contains(shipperId)) {
                    // Check if the shipper ID is valid
                    break; // Exit the loop if the shipper ID is valid
                } else {
                    System.out.println("L'identifiant du client n'est pas valide : " + shipperId);
                    System.out.println("Veuillez sélectionner l'id de votre expéditeur !");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erreur : Vous devez saisir un identifiant valide (entier).");
                System.out.println("Veuillez sélectionner l'id de votre expéditeur !");
            }
        }
        return shipperId;
    }

    private static int getRecipientId(Scanner scanner, ArrayList<Integer> idList, int shipperId) {
        int recipientId;
        System.out.println("Veuillez sélectionner l'id de votre destinataire !");
        while (true) {
            try {
                recipientId = Integer.parseInt(scanner.nextLine());
                if (idList.contains(recipientId) && recipientId != shipperId) {
                    // Check if the recipient ID is valid and not the same as the shipper ID
                    break; // Exit the loop if the recipient ID is valid
                } else {
                    System.out.println("L'identifiant du client n'est pas valide : " + recipientId);
                    System.out.println("Veuillez sélectionner l'id de votre destinataire !");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erreur : Vous devez saisir un identifiant valide (entier).");
                System.out.println("Veuillez sélectionner l'id de votre destinataire !");
            }
        }
        return recipientId;
    }

    private static int getPackagesNumber(Scanner scanner) {
        int packagesNumber;
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
        return packagesNumber;
    }

    private static int getWeight(Scanner scanner) {
        int weight;
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
        return weight;
    }

    private static int determineSenderOrShipperPaysFees(Scanner scanner) {
        int SenderOrShipperPaysFees;
        do {
            try {
                System.out.println("""
                        Veuillez sélectionner qui règle le transport :
                        1. Expéditeur (port payé)
                        2. Destinataire (port dû)""");
                SenderOrShipperPaysFees = Integer.parseInt(scanner.nextLine());
                if(SenderOrShipperPaysFees==1 || SenderOrShipperPaysFees==2)  break;
            } catch (NumberFormatException e) {
                System.out.println("Erreur : Vous devez saisir un entier valide.");
            }
        }
        while (true) ;

        return  SenderOrShipperPaysFees;
    }


    private static int determineZone(HashMap<String, Locality>  localityList, HashMap<Integer, Client>  clients, int recipientId) {
        String clientCity =clients.get(recipientId).city;
        return localityList.get(clientCity).zone;
    }


    private static double determinePrice(ArrayList<Price> priceList, int clientId, int zone) {
        double clientPrice ;
        for (Price price : priceList) {
            // Vérifier si l'objet Price correspond aux critères de recherche
            if (price.getIdClient()==clientId && price.getZone()==zone) {
                // Si c'est le cas, retourner le montant (tarif)
                clientPrice = price.getMontant();
                return clientPrice;
            }

        }
        if (zone - 1 != 0)
            clientPrice = determinePrice(priceList, clientId, zone - 1);
        else
            clientPrice = determinePrice(priceList, 0, zone);

        return clientPrice;
    }


    private static ConditionTaxation findClientConditionTaxation(ArrayList<ConditionTaxation> conditionTaxationsList, int clientId){
        ConditionTaxation conditionClient = null;
        for (ConditionTaxation conditionTaxation : conditionTaxationsList) {
            if (conditionTaxation.getIdClient()==clientId) {
                conditionClient = conditionTaxation;
            }
        }
        if (conditionClient==null) conditionClient=conditionTaxationsList.get(0);
        return  conditionClient;
    }

    private static ShipperAndRecipientFees getShipperAndRecipientFees(int SenderOrShipperPaysFees, ArrayList<ConditionTaxation> conditionTaxationsList, int shipperId, ConditionTaxation defaultConditionTaxation, int recipientId) {
        double shipperTax;
        double recipientTax;
        if ( SenderOrShipperPaysFees ==1 ){
            ConditionTaxation shipperConditionTaxation= findClientConditionTaxation(conditionTaxationsList, shipperId);
            shipperTax = calculateFees(SenderOrShipperPaysFees,shipperConditionTaxation, defaultConditionTaxation);
            recipientTax = 0;
        }else{
            ConditionTaxation recipientConditionTaxation= findClientConditionTaxation(conditionTaxationsList, recipientId);
            recipientTax = calculateFees(SenderOrShipperPaysFees,recipientConditionTaxation, defaultConditionTaxation);
            shipperTax = 0;
        }
        return new ShipperAndRecipientFees(shipperTax, recipientTax);
    }

    private record ShipperAndRecipientFees(double shipperFees, double recipientFees) {
    }


    private static double calculateFees(int userAnswer, ConditionTaxation conditionTaxation, ConditionTaxation defaultConditionTaxation) {

        double fees = 0.0;

        // Vérifier si l'expéditeur règle le transport
        if (userAnswer == 2) {
            if (conditionTaxation.useTaxePortDuGenerale) {
                // Si la taxe pour l'expéditeur doit être celle spécifiée dans la condition de taxation générale
                fees += defaultConditionTaxation.taxePortDu;
            } else {
                // Sinon, calculer la taxe en fonction du montant du transport multiplié par le taux spécifique au client
                fees += conditionTaxation.taxePortDu;
            }
        }
        else{
            // Vérifier si le destinataire règle le transport
            if (conditionTaxation.useTaxePortPayeGenerale) {
                // Si la taxe pour le destinataire doit être celle spécifiée dans la condition de taxation générale
                fees += defaultConditionTaxation.taxePortPaye;
            } else {
                // Sinon, calculer la taxe en fonction du montant du transport multiplié par le taux spécifique au client
                fees +=  conditionTaxation.taxePortPaye;
            }
        }

        return fees;
    }

    private static void showCalculationDetails(int shipperId, int recipientId, int packagesNumber, int weight, int zone, double recipientPriceHT, double shipperPriceHT, double recipientPrice, double shipperPrice) {
        System.out.println("Détails du calcul :\n" +
                "Id de l'expéditeur : "+ shipperId +"\n"+
                "Id de du destinataire : "+ recipientId +"\n"+
                "Nombre de colis :" + packagesNumber + "\n" +
                "Poids total de l'expédition : "+ weight +" kg\n" +
                "Zone du destinataire : "+ zone +"\n"+
                "TarifHT destinataire  : "+ recipientPriceHT +"\n" +
                "TarifHT expéditeur  : "+ shipperPriceHT +"\n" +
                "Tarif total destinataire  : "+ recipientPrice +"\n" +
                "Tarif total expéditeur  : "+ shipperPrice +"\n");
    }

}