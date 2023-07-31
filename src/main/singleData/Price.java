package main.singleData;

public class Price {
    private final int idClient;
    private final int idClientHeritage;
    private final double montant;
    private final int zone;
    String codeDepartement;

    public Price(int idClient, int idClientHeritage, double montant, int zone, String codeDepartement) {
        this.idClient = idClient;
        this.idClientHeritage = idClientHeritage;
        this.montant = montant;
        this.zone = zone;
        this.codeDepartement = codeDepartement;
    }

    public int getIdClient() {
        return idClient;
    }

    public int getIdClientHeritage() {
        return idClientHeritage;
    }

    public double getMontant() {
        return montant;
    }

    public int getZone() {
        return zone;
    }

    public String getCodeDepartement() {
        return codeDepartement;
    }
}
