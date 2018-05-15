package testsuites;

import base.TestBase;
import objects.BaseObject.TYPE;
import objects.Planets;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.Utils;

public class TestPlanets extends TestBase {

    @BeforeClass
    public void beforeClass() {
        get("/planets");
        baseObject = Utils.getFromJson(TYPE.Planets, getResponse());
        log.info(TYPE.Planets.toString() + ": "+baseObject);
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
    @Parameters("json_planets_count")
    public void verifyCountValue(String expectedCount){
        Planets planets = (Planets) baseObject;
        String count = planets.getCount();
        Assert.assertEquals(count, expectedCount,"Unexpected 'count' value!");
        log.info("Expected 'count' value was found: " + count);
    }

    @Test(dependsOnMethods = "verifyCountValue")
    @Parameters("json_key_planets")
    public void verifyObjects(String key){
        Planets planets = new Planets(key);
        Assert.assertEquals(baseObject, planets,"Objects are NOT the same!");
        log.info("Expected object was found: " + planets);
    }

}
