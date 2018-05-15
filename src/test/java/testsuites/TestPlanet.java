package testsuites;

import base.TestBase;
import objects.BaseObject.TYPE;
import objects.Planet;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.Utils;

public class TestPlanet extends TestBase {

    @BeforeClass
    public void beforeClass() {
        get("/planets/1");
        baseObject = Utils.getFromJson(TYPE.Planet, getResponse());
        log.info(TYPE.Planet.toString() + ": "+baseObject);
    }

    @Test
    @Parameters("statusCode")
    public void verifyStatusCode(String statusCode){
        int code = getStatusCode();
        Assert.assertEquals(code, Integer.parseInt(statusCode),"Status code is not as expected!");
        log.info("Expected Status code was found: " + code);
    }

    @Test(dependsOnMethods = "verifyStatusCode")
    @Parameters("contentType")
    public void verifyContentType(String contentType){
        String cType = getContentType();
        Assert.assertEquals(cType, contentType,"Content Type is not as expected!");
        log.info("Expected Content Type was found: " + cType);
    }

    @Test(dependsOnMethods = "verifyContentType")
    @Parameters("json_key_planet")
    public void verifyObjects(String key){
        Planet planet = new Planet(key);
        Assert.assertEquals(baseObject, planet,"Objects are NOT the same!");
        log.info("Expected object was found: " + planet);
    }

    @DataProvider(name = "Planet")
    public static Object[][] planet() {
        return new Object[][] { { "1", "planet1", "" }, { "2", "planet2", "" }, { "3", "planet3", "" }, { "4", "planet4", "" },
                { "5", "planet5", "" }, { "6", "planet6", "" }, { "7", "planet7", "" }, { "8", "planet8", "" },
                { "9", "planet9", "" }, { "10", "planet10", "" }, { "11", "planet11", "" }, { "12", "planet12", "" },
                { "13", "planet13", "" }, { "14", "planet14", "" }, { "15", "planet15", "" }, { "0", "", "Not found" },
                { "10000", "", "Not found" }, { "a", "", "Not found" }};
    }

    @Test(dataProvider = "Planet", dependsOnMethods = "verifyObjects")
    public void getPlanetById(String id, String key, String errorMessage){
        get("/planets/" + id);
        Planet planet = null;
        Planet expectedPlanet = null;

        if(Utils.isNull(errorMessage)){
            planet = (Planet) Utils.getFromJson(TYPE.Planet, getResponse());
            if(!Utils.isNull(key)){
                expectedPlanet = new Planet(key);
                log.info(TYPE.Planet.toString() + ": "+planet);
            } else{
                log.exception("Unable to create Planet with empty key!");
            }
            Assert.assertEquals(expectedPlanet, planet, "Unexpected planet!");
            log.info("Expected object was found by id " + id);
        } else {
            Assert.assertTrue(getResponse().contains(errorMessage), "Error message " + errorMessage + " was NOT found in the response!");
            log.info("Error message " + errorMessage + " was found");
        }
    }
}
