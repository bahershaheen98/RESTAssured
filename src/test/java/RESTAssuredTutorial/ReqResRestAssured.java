package RESTAssuredTutorial;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ReqResRestAssured {
    static String BASE_URL = "https://reqres.in/api/";
    String postBody = """
            {
                "name": "morpheus",
                "job": "leader",
                "id": "517",
                "createdAt": "2022-12-26T13:34:52.518Z"
            }
            """;

    private static RequestSpecification requestSpecification;

    @BeforeClass
    public void setUp(){
        requestSpecification = new RequestSpecBuilder().
                setBaseUri(BASE_URL).
                setContentType(ContentType.JSON).setRelaxedHTTPSValidation().build();
    }

    @Test
    public void getUser(){
        GetRequest getCorrectUser = new GetRequest("https://reqres.in/api/","users/2");
        Response response = getCorrectUser.send();

    }
    @Test
    public void getWrongUser(){
        GetRequest getWrongUser = new GetRequest("https://reqres.in/api/","users/a");
        Response response = getWrongUser.send();
        Assert.assertEquals(404, response.statusCode());
    }

    @Test
    public void deleteUser(){
        DeleteRequest deleteUser = new DeleteRequest("https://reqres.in/api/","users/2");
        Response response = deleteUser.send();
        Assert.assertEquals(204, response.statusCode());
    }

    @Test
    public void postUser(){
        PostRequest postUser = new PostRequest("https://reqres.in/api/","users");
        postUser.addBody(postBody);
        Response response = postUser.send();
    }



    @Test
    public void createUser(){
        String body = """
                    {
                    "name": "Baher",
                    "job": "Software Tester",
                    "id": 594,
                    "createdAt": "2022-12-26T09:24:55.845Z"
                    }
                """;
        given().spec(requestSpecification).contentType(ContentType.JSON)
                .header("charset", ContentType.JSON).body(body).
                 when().post("users").
                then().statusCode(201).contentType(ContentType.JSON);
    }

   //* @Test
  /*  public void updateUser(){
        String bodyOfUser = """
                {
                    "name": "Mostafa",
                    "job": "Sami",
                    "updatedAt": "2022-12-26T09:25:28.213Z"
                }
                """;
        given().spec(requestSpecification)
                .when().put("user/2")
                .then().
    }*/

}
