package testsuites;

import base.TestBase;
import objects.BaseObject.TYPE;
import objects.Planets;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.Utils;

import java.util.Random;

public class TestPlanet extends TestBase {

    private static int rand;

    @BeforeClass
    public void beforeClass() {
        if(Utils.isNull(TestPlanets.planetCount)){
            get("/planets");
            baseObject = Utils.getFromJson(TYPE.Planets, getResponse());
            if(baseObject != null)
                TestPlanets.planetCount = ((Planets) baseObject).getCount();
        }
        //The rand value is supposed to get in a range [2..PlanetCount-1],
        // however in this case we have to place all the objects in data.json.
        // I placed just 15 planets and will take the rand value in range [2..15]
        //rand = new Random().nextInt(Integer.valueOf(TestPlanets.planetCount))+1;
        rand = new Random().nextInt(14)+2;
    }

    @DataProvider(name = "PlanetPositive")
    public static Object[][] planetPositive() {
        return new Object[][] {
                { String.valueOf(rand), "planet"+rand},
                { "1", "planet1"},
                { "61", "planet61"}
        };
    }

    @Test(groups = {"Positive", "P1"}, dataProvider = "PlanetPositive", alwaysRun = true)
    public void verifyPlanetPositive(String id, String key){
        verifyObjectPositive("/planets/" + id, key, TYPE.Planet);
    }

    @DataProvider(name = "PlanetNegative")
    public static Object[][] planetNegativeP2() {
        return new Object[][] {
                { "0" },
                { "10000" },
                { "a" }
        };
    }

    @Test(groups = {"Negative", "P2"}, dataProvider = "PlanetNegative", dependsOnGroups = "Positive", alwaysRun = true)
    public void verifyPlanetNegative(String id){
        verifyObjectNegative("/planets/" + id);
    }
}

