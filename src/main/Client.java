package main;

public class Client {
    String postalCode;
    String clientId;
    String socialRaison;
    String city;

    public Client(String postalCode, String clientId, String socialRaison, String city) {
        this.postalCode = postalCode;
        this.clientId = clientId;
        this.socialRaison = socialRaison;
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getIdClient() {
        return clientId;
    }

    public String getSocialRaison() {
        return socialRaison;
    }

    public String getCity() {
        return city;
    }
}
