import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RestChallengeTest {

    @Test
    public void  getCityNegative(){
        String city = "xyz ggd";
        RestAssured.baseURI = "https://restcountries.com/v3.1/capital/";
        RequestSpecification request= RestAssured.given();
        Response response = request.request(Method.GET,city);
        String responseBody = response.getBody().asString();
        System.out.println("Response body is: "+ responseBody);
        int statusCode = response.getStatusCode();
        System.out.println("status code is: "+ statusCode);
        if(statusCode<=400) {
            Assert.assertTrue(false);
        }
        else {
            Assert.assertTrue(true);
        }
    }
    @Test
    public void  TestMoscow() {
        String city = "madrid";
        RestAssured.baseURI = "https://restcountries.com/v3.1/capital/";
        RequestSpecification httprequest= RestAssured.given();
        Response response = httprequest.request(Method.GET,city);
        String responseBody = response.getBody().asString();
        Assert.assertEquals(responseBody.contains("Madrid"),true);
    }
}
