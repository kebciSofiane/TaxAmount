package tests;

import main.dataManagement.ConditionsTaxation;
import main.singleData.ConditionTaxation;
import org.dom4j.DocumentException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

import static main.Main.*;

public class CalculateFeesTest {

    @Test
    public void testGetData() throws DocumentException {

        ConditionsTaxation conditionsTaxation = new ConditionsTaxation();
        HashMap<Integer, ConditionTaxation> conditionTaxationList = conditionsTaxation.getConditionsTaxation();

        ConditionTaxation defaultConditionTaxation = findClientConditionTaxation(conditionTaxationList, 0);


        double clientFees = calculateFees(1, conditionTaxationList.get(0), defaultConditionTaxation);
        double clientFees2 = calculateFees(2, conditionTaxationList.get(0), defaultConditionTaxation);
        double clientFees3 = calculateFees(2, conditionTaxationList.get(1), defaultConditionTaxation);
        double clientFees4 = calculateFees(2, conditionTaxationList.get(2), defaultConditionTaxation);


        Assertions.assertEquals(clientFees, 1.0);
        Assertions.assertEquals(clientFees2, 10.0);
        Assertions.assertEquals(clientFees3, 5.0);
        Assertions.assertEquals(clientFees4, 10.0);







    }
}