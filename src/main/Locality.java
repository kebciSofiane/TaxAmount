package main;

public class Locality {
    String codePostal;
    String ville;
    String zone;

    public Locality(String codePostal, String ville, String zone) {
        this.codePostal = codePostal;
        this.ville = ville;
        this.zone = zone;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public String getVille() {
        return ville;
    }

    public String getZone() {
        return zone;
    }
}
