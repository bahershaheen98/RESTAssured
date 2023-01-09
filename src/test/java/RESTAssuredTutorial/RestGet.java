package RESTAssuredTutorial;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.net.URI;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RestGet {

    @Test
    public void getByZipCode() {
        when().get("http://api.zippopotam.us/us/90210").
                then().statusCode(200).log().body()
                .contentType(ContentType.JSON).body("places[0].state",equalTo("California"));
    }
    @Test
    public void testGetZipCode() {
        String body= """
                {
                  "title": "foo",
                  "body": "bar",
                  "userId": 1
                }
                """;
        given()
                .contentType(ContentType.JSON).header("charset", ContentType.MULTIPART).body(body).
                when().post("https://jsonplaceholder.typicode.com/posts").
                     then().log().body().
                statusCode(201);
    }
    @Test
    public void listAll(){
        given()
                .contentType(ContentType.JSON).
                when().get("https://jsonplaceholder.typicode.com/posts")
                .then().log().body().body("",hasSize(100));
    }

}
