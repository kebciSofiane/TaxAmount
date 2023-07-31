package tests;

import main.dataManagement.Localities;
import main.singleData.Locality;
import org.dom4j.DocumentException;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

public class LocalitiesTest {

    @Test
    public void testGetData() throws DocumentException {
        Localities localities = new Localities();
        HashMap<String, Locality> localityList = localities.getLocalities();

        assertEquals(localityList.get(0).getCodePostal(), "69");
        assertEquals(localityList.get(1).getVille(), "AFFOUX");
        assertEquals(localityList.get(4).getZone(), "2");

    }
}