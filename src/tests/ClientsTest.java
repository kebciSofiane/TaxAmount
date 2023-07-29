package tests;

import main.Client;
import main.Clients;

import org.dom4j.DocumentException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

public class ClientsTest {

    @Test
    public void testGetData() throws DocumentException {
        Clients clients = new Clients();
        HashMap<Integer, Client> clientList = clients.getClients();

        assertEquals(clientList.get(0).getIdClient(), "1");
        assertEquals(clientList.get(1).getPostalCode(), "71");
        assertEquals(clientList.get(2).getSocialRaison(), "VINS DU MACONNAIS");
        assertEquals(clientList.get(3).getCity(), "ABELCOURT");

    }
}