package main;

public class Client {
    String postalCode;
    int clientId;
    String socialRaison;
    String city;

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
