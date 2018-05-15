package base;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import objects.BaseObject;
import testdata.TestData;
import org.testng.annotations.BeforeClass;
import utils.Utils;

import java.util.List;

public class TestBase {

    protected static Logger log = Logger.getDefault();
    private Response response;
    protected BaseObject baseObject;

    @BeforeClass
    public void setup() {
        System.setProperty("https.proxyHost", "149.28.133.203");
        System.setProperty("https.proxyPort", "8080");
        RestAssured.baseURI = "https://swapi.co/api/";
        log.info("Base URI: " + RestAssured.baseURI);
        TestData.readTestData();
    }

    protected void get(String page) {
        long startTime = System.currentTimeMillis();
        response = RestAssured.get(page);
        log.performance("Get page " + page, startTime);

        log.info("get page: " + page);
        if(Utils.isNull(response.getContentType()) || !response.getContentType().contains("application/json")){
            log.warning("Content type is not application/json! Response: " + response.asString());
        }
    }

    protected String getResponse(){
        return response.asString();
    }

    public List<Header> getHeaders(){
        return response.getHeaders().asList();
    }

    public int getStatusCode(){
        return response.getStatusCode();
    }

    public String getBody(){
        return response.getBody().asString();
    }

    public String getContentType(){
        return response.getContentType();
    }

}
