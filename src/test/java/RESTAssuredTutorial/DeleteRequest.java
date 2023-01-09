package RESTAssuredTutorial;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteRequest {
    // what will be provided at creation time
    String uri;
    String endpoint;
    RequestSpecification requestSpecification;


// what will be provided at the run time

    public DeleteRequest(String uri, String endpoint) {
        this.uri = uri;
        this.endpoint = endpoint;
        requestSpecification = RestAssured.given().baseUri(uri).contentType(ContentType.JSON);
    }


    public void addSpecification(RequestSpecification specification){
        requestSpecification.spec(specification);
    }
    public Response send(){

        return requestSpecification.delete(endpoint);
    }
}
