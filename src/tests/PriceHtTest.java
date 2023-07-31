package tests;

import main.dataManagement.Prices;
import main.singleData.Price;
import org.dom4j.DocumentException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static main.Main.determinePrice;

public class PriceHtTest {

    @Test
    public void testGetData() throws DocumentException {

        Prices prices = new Prices();
        ArrayList<Price> pricesList = prices.getPrices();


        double price1 = determinePrice(pricesList,1,1);
        double price2 = determinePrice(pricesList,1,2);
        double price3 = determinePrice(pricesList,4,2);
        double price4 = determinePrice(pricesList,6,2);



        Assertions.assertEquals(price1, 69.11);
        Assertions.assertEquals(price2, 69.12);
        Assertions.assertEquals(price3, 69.01);
        Assertions.assertEquals(price4, 71.61);






    }
}