public class Price {
    String idClient;
    String idClientHeritage;
    String montant;
    String zone;

    public Price(String idClient, String idClientHeritage, String montant, String zone) {
        this.idClient = idClient;
        this.idClientHeritage = idClientHeritage;
        this.montant = montant;
        this.zone = zone;
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
