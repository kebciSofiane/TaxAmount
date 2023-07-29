package main;

public class Price {
    String idClient;
    String idClientHeritage;
    String montant;
    String zone;
    String codeDepartement;

    public Price(String idClient, String idClientHeritage, String montant, String zone, String codeDepartement) {
        this.idClient = idClient;
        this.idClientHeritage = idClientHeritage;
        this.montant = montant;
        this.zone = zone;
        this.codeDepartement=codeDepartement;
    }

    public String getCodeDepartement() {
        return codeDepartement;
    }

    public String getIdClient(){
        return idClient;
    }

    public String getIdClientHeritage() {
        return idClientHeritage;
    }

    public String getMontant() {
        return montant;
    }

    public String getZone() {
        return zone;
    }
}
