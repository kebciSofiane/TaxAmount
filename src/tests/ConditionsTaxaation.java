package tests;

import main.Client;
import main.Clients;

import main.ConditionTaxation;
import main.ConditionsTaxation;
import org.dom4j.DocumentException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class ConditionsTaxaation {

    @Test
    public void testGetData() throws DocumentException {
        ConditionsTaxation conditionsTaxation = new ConditionsTaxation();
        ArrayList<ConditionTaxation> conditionTaxationList = conditionsTaxation.getConditionsTaxation();

        assertEquals(conditionTaxationList.get(1).getIdClient(), "1");
        assertEquals(conditionTaxationList.get(1).getTaxePortDu(), "5");
        assertEquals(conditionTaxationList.get(2).getTaxePortPaye(), "2.5");
        assertEquals(conditionTaxationList.get(0).isUseTaxePortDuGenerale(), false);
        assertEquals(conditionTaxationList.get(2).isUseTaxePortPayeGenerale(), true);

    }
}