package base;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import objects.BaseObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import testdata.TestData;
import utils.Utils;

import java.util.List;

public class TestBase implements ITestBase {

    protected static Logger log = Logger.getDefault();
    private Response response;
    protected BaseObject baseObject = new BaseObject();

    @BeforeClass
    public void setup() {
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

    public void verifyStatusCode(String statusCode){
        verifyStatusCode(Integer.parseInt(statusCode));
    }

    public void verifyStatusCode(int statusCode){
        int code = getStatusCode();
        Assert.assertEquals(code, statusCode,"Unexpected Status Code!");
        log.info("Expected Status code was found: " + code);
    }

    public String contestTypeValue(String contentType){
        return contestTypeValue(CONTENT_TYPE.valueOf(contentType));
    }

    public String contestTypeValue(CONTENT_TYPE contentType){
        String cType = null;
        switch (contentType){
            case HTML:
                cType = CONTENT_TYPE_HTML;
                break;
            case JSON:
                cType = CONTENT_TYPE_JSON;
                break;
        }
        return cType;
    }

    public void verifyContentType(CONTENT_TYPE contentType){
        String expType = contestTypeValue(contentType);
        String actType = getContentType();
        Assert.assertEquals(actType, expType,"Unexpected Content Type!");
        log.info("Expected Content Type was found: " + actType);
    }

    public void verifyObject(String key, BaseObject.TYPE type){
        BaseObject expectedObject = baseObject.create(type, key);
        BaseObject actualObject = Utils.getFromJson(type, getResponse());
        Assert.assertEquals(actualObject, expectedObject, "Unexpected object!");
        log.info("Expected object was found: \n" + actualObject);
    }

    public void verifyObjectPositive(String page, String key, BaseObject.TYPE type){
        get(page);
        verifyStatusCode(STATUS_CODE_200);
        verifyContentType(CONTENT_TYPE.JSON);
        verifyObject(key, type);
    }

    public void verifyObjectNegative(String page, String contentType, String body){
        get(page);
        verifyStatusCode(STATUS_CODE_404);
        verifyContentType(Utils.isNull(contentType)?CONTENT_TYPE.JSON:CONTENT_TYPE.valueOf(contentType));
        String resp = getResponse();
        String responseBody = Utils.isNull(body)?RESPONSE_NOT_FOUND:body;
        Assert.assertTrue(resp.contains(responseBody), "Response " + responseBody + " was NOT found! Actual: " + resp);
        log.info("Expected Response Body " + responseBody + " was found");
    }

    public void verifyObjectNegative(String page){
        verifyObjectNegative(page, null, null);
    }

}
