package main.singleData;

public class Client {
    private String postalCode;
    private int clientId;
    private String socialRaison;
    private String city;

    public Client(String postalCode, int clientId, String socialRaison, String city) {
        this.postalCode = postalCode;
        this.clientId = clientId;
        this.socialRaison = socialRaison;
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public int getIdClient() {
        return clientId;
    }

    public String getSocialRaison() {
        return socialRaison;
    }

    public String getCity() {
        return city;
    }
}
