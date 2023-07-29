package tests;

import main.Client;
import main.Clients;

import main.Price;
import main.Prices;
import org.dom4j.DocumentException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class pricesTest {

    @Test
        public void testGetData() throws DocumentException {
        Prices prices = new Prices();
        ArrayList<Price> priceList = prices.getPrices();

        assertEquals(priceList.get(0).getIdClient(), "0");
        assertEquals(priceList.get(1).getCodeDepartement(), "69");
        assertEquals(priceList.get(2).getZone(), "1");
        assertEquals(priceList.get(3).getMontant(), "70.02");


    }
}