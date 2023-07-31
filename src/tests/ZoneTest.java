package tests;

import main.Main;
import main.dataManagement.Localities;
import main.singleData.Client;
import main.dataManagement.Clients;

import main.singleData.Locality;
import org.dom4j.DocumentException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class ZoneTest {

    @Test
    public void testGetData() throws DocumentException {

        Localities localities = new Localities();
        Clients clients = new Clients();

        HashMap<String, Locality> localityList = localities.getLocalities();
        HashMap<Integer, Client> clientList = clients.getClients();

        int zone1 = Main.determineZone(localityList,clientList,1);
        int zone2 = Main.determineZone(localityList,clientList,2);
        int zone3 = Main.determineZone(localityList,clientList,3);
        int zone4 = Main.determineZone(localityList,clientList,4);
        int zone5 = Main.determineZone(localityList,clientList,5);
        int zone6 = Main.determineZone(localityList,clientList,6);


        Assertions.assertEquals(zone1, 1);
        Assertions.assertEquals(zone2, 2);
        Assertions.assertEquals(zone3, 1);
        Assertions.assertEquals(zone4, 2);
        Assertions.assertEquals(zone5, 1);
        Assertions.assertEquals(zone6, 2);





    }
}