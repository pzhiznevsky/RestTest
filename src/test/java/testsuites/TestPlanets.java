package testsuites;

import base.TestBase;
import objects.BaseObject.TYPE;
import objects.Planets;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.Utils;

public class TestPlanets extends TestBase {

    public static String planetCount;

    @BeforeClass
    public void beforeClass() {
        get("/planets");
        baseObject = Utils.getFromJson(TYPE.Planets, getResponse());
        planetCount = ((Planets) baseObject).getCount();
    }

    @Test(groups = {"Positive", "P1"}, alwaysRun = true)
    @Parameters({"page", "key"})
    public void verifyPlanetsPositive(String page, String key){
        verifyObjectPositive(page, key, TYPE.Planets);
    }

    @Test(groups = {"Negative", "P2"}, alwaysRun = true)
    @Parameters({"pageNegative", "keyNegative", "bodyNegative"})
    public void verifyPlanetsNegative(String page, String key, String body){
        verifyObjectNegative(page, key, body);
    }

}
