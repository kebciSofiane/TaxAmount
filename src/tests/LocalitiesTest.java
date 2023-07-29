package tests;

import main.Localities;
import main.Locality;
import org.dom4j.DocumentException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class LocalitiesTest {

    @Test
    public void testGetData() throws DocumentException {
        Localities localities = new Localities();
        ArrayList<Locality> clientList = localities.getLocalities();

        assertEquals(clientList.get(0).getCodePostal(), "69");
        assertEquals(clientList.get(1).getVille(), "AFFOUX");
        assertEquals(clientList.get(4).getZone(), "2");

    }
}