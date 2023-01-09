package RESTAssuredTutorial;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class JsonPlaceHolder {
    static String BASE_URL = "https://jsonplaceholder.typicode.com/";
    static String END_POINT = "posts/";
    private static RequestSpecification requestSpecification;

    @BeforeClass
    public static void setUp(){
        requestSpecification = new RequestSpecBuilder().
                setBaseUri(BASE_URL).
                setContentType(ContentType.JSON).setRelaxedHTTPSValidation().build();

    }
    @Test
    public static void getByZipCode() {
        given().spec(requestSpecification).baseUri(BASE_URL).
        when().get(END_POINT + "2").
                then().statusCode(200).log().body()
                .contentType(ContentType.JSON);
    }
    @Test
    public void testGetZipCode() {
        String body = """
                {
                  "title": "foo",
                  "body": "bar",
                  "userId": 1
                }
                """;
        given().spec(requestSpecification)
                .contentType(ContentType.JSON).header("charset", ContentType.MULTIPART).body(body).
                when().post(END_POINT).
                then().log().body().
                statusCode(201).contentType(ContentType.JSON);
    }
    @Test
    public void deleteItem(){
        given().spec(requestSpecification).
        when().delete(END_POINT + "1").
                then().statusCode(200).contentType(ContentType.JSON);
    }
    @Test
    public void putItem(){
    String body = """
                {
                  "title": "foor",
                  "body": "bar",
                  "userId": 1
                }
                """;
    given()
            .spec(requestSpecification).
    when().put(END_POINT + "1")
            .then().log().all().statusCode(200).contentType(ContentType.JSON);
}

}
