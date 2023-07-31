package main;

import main.dataManagement.Clients;
import main.dataManagement.ConditionsTaxation;
import main.dataManagement.Localities;
import main.dataManagement.Prices;
import main.singleData.Client;
import main.singleData.ConditionTaxation;
import main.singleData.Locality;
import main.singleData.Price;
import org.dom4j.DocumentException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws DocumentException {
        // Initialisation des variables pour les entrées utilisateur
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

        // Initialisation des classes pour la gestion des données
        Clients clients = new Clients();
        ConditionsTaxation conditionsTaxation = new ConditionsTaxation();
        Localities localities = new Localities();
        Prices prices = new Prices();

        // Récupération des listes de données
        HashMap<Integer, Client> clientList = clients.getClients();
        HashMap<String, Locality> localityList = localities.getLocalities();
        HashMap<Integer, ConditionTaxation> conditionTaxationList = conditionsTaxation.getConditionsTaxation();
        ArrayList<Price> priceList = prices.getPrices();

        // Afficher les détails des clients disponibles et obtenir une liste d'identifiants de clients
        ArrayList<Integer> idList = showClientsDetails(clientList);

        // Obtenir l'ID de l'expéditeur à partir de l'entrée utilisateur
        shipperId = getShipperId(scanner, idList);

        // Obtenir l'ID du destinataire à partir de l'entrée utilisateur
        recipientId = getRecipientId(scanner, idList, shipperId);

        // Obtenir le nombre de colis à partir de l'entrée utilisateur
        packagesNumber = getPackagesNumber(scanner);

        // Obtenir le poids total de l'expédition à partir de l'entrée utilisateur
        weight = getWeight(scanner);

        // Déterminer qui règle les frais d'expédition (l'expéditeur ou le destinataire)
        SenderOrShipperPaysFees = determineSenderOrShipperPaysFees(scanner);

        // Déterminer la zone du destinataire à partir des données de localités
        zone = determineZone(localityList, clientList, recipientId);

        // Déterminer le tarif HT de l'expéditeur pour la zone spécifiée
        shipperPriceHT = determinePrice(priceList, shipperId, zone);

        // Déterminer le tarif HT du destinataire pour la zone spécifiée
        recipientPriceHT = determinePrice(priceList, recipientId, zone);

        // Trouver la condition de taxation par défaut pour les clients n'ayant pas de condition spécifique
        defaultConditionTaxation = findClientConditionTaxation(conditionTaxationList, 0);

        // Obtenir les frais d'expédition de l'expéditeur et du destinataire
        ShipperAndRecipientFees shipperAndRecipientFees = getShipperAndRecipientFees(SenderOrShipperPaysFees, conditionTaxationList, shipperId, defaultConditionTaxation, recipientId);

        // Calculer les tarifs totaux de l'expéditeur et du destinataire en ajoutant les frais d'expédition aux tarifs HT
        shipperPrice = shipperAndRecipientFees.shipperFees() + shipperPriceHT;
        recipientPrice = shipperAndRecipientFees.recipientFees() + recipientPriceHT;

        // Afficher les détails du calcul
        showCalculationDetails(shipperId, recipientId, packagesNumber, weight, zone, recipientPriceHT, shipperPriceHT, recipientPrice, shipperPrice);
    }

    private static ArrayList<Integer> showClientsDetails(HashMap<Integer, Client> clientList) {
        System.out.println("Here is our clients: ");
        ArrayList<Integer> idList= new ArrayList<>();
        for (Client client : clientList.values()){
            idList.add(client.getIdClient());
            System.out.println("ClientId : "+client.getIdClient());
            System.out.println("postalCode : "+client.getPostalCode());
            System.out.println("socialRaison : "+client.getSocialRaison());
            System.out.println("city : "+client.getCity());
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
                    // vérifier que l'id est valide
                    break;
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
                // vérifier que l'id est valide
                if (idList.contains(recipientId) && recipientId != shipperId) {
                    break;
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
                // vérifier que le nombre est valide
                if(SenderOrShipperPaysFees==1 || SenderOrShipperPaysFees==2)  break;
            } catch (NumberFormatException e) {
                System.out.println("Erreur : Vous devez saisir un entier valide.");
            }
        }
        while (true) ;

        return  SenderOrShipperPaysFees;
    }


    public static int determineZone(HashMap<String, Locality>  localityList, HashMap<Integer, Client>  clients, int recipientId) {
        String clientCity =clients.get(recipientId).getCity();
        return localityList.get(clientCity).getZone();
    }


    public static double determinePrice(ArrayList<Price> priceList, int clientId, int zone) {
        double clientPrice ;
        for (Price price : priceList) {
            // Vérifier si l'objet Price correspond aux critères de recherche
            if (price.getIdClient()==clientId && price.getZone()==zone) {
                clientPrice = price.getMontant();
                return clientPrice;
            }

        }
        //Si un tarif n’existe pas dans la zone déterminée, alors on utilise le tarif de la zone z-1.
        if (zone - 1 != 0)
            clientPrice = determinePrice(priceList, clientId, zone - 1);
        //Si le client ne possède pas de tarif pour ce département, alors on utilise le tarif général.
        else
            clientPrice = determinePrice(priceList, 0, zone);

        return clientPrice;
    }


    public static ConditionTaxation findClientConditionTaxation(HashMap<Integer, ConditionTaxation> conditionTaxationList, int clientId){
        ConditionTaxation conditionClient = conditionTaxationList.get(clientId);
        if (conditionClient==null) conditionClient=conditionTaxationList.get(0);
        return  conditionClient;
    }

    private static ShipperAndRecipientFees getShipperAndRecipientFees(int SenderOrShipperPaysFees, HashMap<Integer, ConditionTaxation>  conditionTaxationList, int shipperId, ConditionTaxation defaultConditionTaxation, int recipientId) {
        double shipperTax;
        double recipientTax;
        if ( SenderOrShipperPaysFees == 1 ){ // Expéditeur paie les frais de transport
            ConditionTaxation shipperConditionTaxation= findClientConditionTaxation(conditionTaxationList, shipperId);
            shipperTax = calculateFees(SenderOrShipperPaysFees,shipperConditionTaxation, defaultConditionTaxation);
            recipientTax = 0;
        }else{ //Destinataire paie les frais de transport
            ConditionTaxation recipientConditionTaxation= findClientConditionTaxation(conditionTaxationList, recipientId);
            recipientTax = calculateFees(SenderOrShipperPaysFees,recipientConditionTaxation, defaultConditionTaxation);
            shipperTax = 0;
        }
        return new ShipperAndRecipientFees(shipperTax, recipientTax);
    }

    private record ShipperAndRecipientFees(double shipperFees, double recipientFees) {
    }


    public static double calculateFees(int userAnswer, ConditionTaxation conditionTaxation, ConditionTaxation defaultConditionTaxation) {

        double fees = 0.0;

        // Vérifier si l'expéditeur règle le transport
        if (userAnswer == 2) {
            if (conditionTaxation.isUseTaxePortDuGenerale()) {
                // Si la taxe pour l'expéditeur doit être celle spécifiée dans la condition de taxation générale
                fees += defaultConditionTaxation.getTaxePortDu();
            } else {
                // Sinon, calculer la taxe en fonction du montant du transport multiplié par le taux spécifique au client
                fees += conditionTaxation.getTaxePortDu();
            }
        }
        else{
            // Vérifier si le destinataire règle le transport
            if (conditionTaxation.isUseTaxePortPayeGenerale()) {
                // Si la taxe pour le destinataire doit être celle spécifiée dans la condition de taxation générale
                fees += defaultConditionTaxation.getTaxePortPaye();
            } else {
                // Sinon, calculer la taxe en fonction du montant du transport multiplié par le taux spécifique au client
                fees +=  conditionTaxation.getTaxePortPaye();
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