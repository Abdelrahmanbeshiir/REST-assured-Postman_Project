import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Chapter2Test {
    private static RequestSpecification requestSpecification;
    private static ResponseSpecification responseSpecification;
    @BeforeTest
    public static void createRequestSpecification() {

        requestSpecification = new RequestSpecBuilder().
                setBaseUri("http://api.zippopotam.us").
                build();
    }
    @BeforeTest
    public static void CreateResponseSpecification(){
        responseSpecification=new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).build();

    }
    @Test
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills_withRequestSpec() {

        given().
                spec(requestSpecification).
                when().
                get("us/90210").
                then().spec(responseSpecification).and().
                body("places[0].'place name'", equalTo("Beverly Hills"));

              String name= given().spec(requestSpecification).
                when().
                get("us/90210").
                then().spec(responseSpecification).extract().path("places[0].'place name'");
        System.out.println(name);
        Assert.assertEquals(name,"Beverly Hills");
    }
}
