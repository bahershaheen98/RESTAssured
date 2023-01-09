package RESTAssuredTutorial;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequest {
    // what will be provided at creation time
    String uri;
    String endpoint;
    RequestSpecification requestSpecification;


// what will be provided at the run time

    public PostRequest(String uri, String endpoint) {
        this.uri = uri;
        this.endpoint = endpoint;
        requestSpecification = RestAssured.given().baseUri(uri).contentType(ContentType.JSON);
    }

    public void addQueryParameter(String key,String value){
        //parameter user on request
        requestSpecification.param(key,value);
    }
    public void addSpecification(RequestSpecification specification){
        requestSpecification.spec(specification);
    }
    public void addBody(String body){
      requestSpecification.body(body);
    }
    public Response send(){
        return requestSpecification.post(endpoint);
    }
}
