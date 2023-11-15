import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeTest;

public class BaseTests {
    static RequestSpecification requestSpecification;
    static ResponseSpecification responseSpecification;
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

}
