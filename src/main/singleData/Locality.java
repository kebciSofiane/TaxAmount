package main.singleData;

public class Locality {
    private final String codePostal;
    private final String ville;
    private final int zone;

    public Locality(String codePostal, String ville, int zone) {
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

    public int getZone() {
        return zone;
    }
}
