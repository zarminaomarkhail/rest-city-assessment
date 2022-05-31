import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import static io.restassured.RestAssured.when;

public class RestChallenge {
    public static void main(String[] args) {

            RestAssured.baseURI = "https://restcountries.com";
            RestAssured.basePath = "/v3.1/";

            Scanner scanner = new Scanner(System.in);

            while (true){
                Response response;
                System.out.println("ENTER COUNTRY NAME OR CODE OR ENTER Q to QUIT: ");
                System.out.print("OPTION: ");
                String country = scanner.nextLine().toLowerCase(Locale.ROOT);

                if (country.toLowerCase(Locale.ROOT).equals("q") || country.toLowerCase(Locale.ROOT).equals("quit")) {
                    System.out.println("THANKS FOR USING OUR APP!\n");
                    break;
                }
                String url = "";
                if (country.length()  < 4 && country.length()>1){
                    url = "alpha?codes="+country;
                }else if (country.length() >= 4){
                    url = "/name/"+country;
                }else{
                    System.out.println("PLEASE ENTER CORRECT COUNTRY NAME OR CODE");
                    continue;
                }
                try {
                    response =
                            when().
                                    get(url).
                                    then().
                                    contentType(ContentType.JSON).
                                    extract().response();

                    int statusCode = response.getStatusCode();

                    if (statusCode == 200){
                        System.out.println("SUCCESS");
                        List<String> capital = response.path("capital");
                        List<String> official = response.path("name.official");
                        List<String> language = response.path("languages");
                        System.out.println("Full Country Name: " + official);
                        System.out.println("Capital: " +   capital);
                        System.out.println("Official Languages: " + language+"\n");
                    }else{
                        System.out.println("FAILURE:");
                        System.out.println("Status Code returned: " + statusCode + "\n");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
}
