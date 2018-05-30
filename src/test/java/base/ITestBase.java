package base;

interface ITestBase {

    int STATUS_CODE_200 = 200;
    int STATUS_CODE_404 = 404;
    String CONTENT_TYPE_JSON = "application/json";
    String CONTENT_TYPE_HTML = "text/html; charset=utf-8";
    String RESPONSE_NOT_FOUND = "{\"detail\":\"Not found\"}";

    public enum CONTENT_TYPE{JSON, HTML}
}
