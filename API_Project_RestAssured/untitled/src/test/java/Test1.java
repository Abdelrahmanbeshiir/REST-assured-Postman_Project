import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class Test1 {
   @Test
   public void Test10(){
       given().baseUri("https://reqres.in/api")
               .when().get("users")
               .then().log().all()
               .assertThat().statusCode(200)
               .assertThat().body("data[0].email",is(equalTo("george.bluth@reqres.in"))).
               body("data.avatar",hasItem("https://reqres.in/img/faces/2-image.jpg"));
   }
    @Test
    public void Test2(){
        given().baseUri("https://654e45c8cbc325355742a76e.mockapi.io/api/v1/")
                .when().get("users")
                .then().log().all()
                .assertThat().body("[0].name",equalTo("Patty Turner")
                ,"name",hasItem("Allan Mosciski II"));

    }
    @Test
    public void contains(){
        given().baseUri("https://654e45c8cbc325355742a76e.mockapi.io/api/v1/")
                .when().get("users")
                .then().log().all()
                .assertThat().body("[0].name",equalTo("Patty Turner"));

//contains need to put all elements in the same order
    }
    @Test
    public void HasSize(){
        given().baseUri("https://654e45c8cbc325355742a76e.mockapi.io/api/v1/")
                .when().get("users")
                .then().log().all()
                .assertThat().body("name.size()",equalTo(31)
                        ,"name",hasSize(31));

    }
    @Test
    public void EveryItemStartAt(){
        given().baseUri("https://654e45c8cbc325355742a76e.mockapi.io/api/v1/")
                .when().get("users")
                .then().log().all()
                .assertThat().body("createdAt",everyItem(startsWith("2023")));

    }
    @Test
    public void HasKey(){
        given().baseUri("https://654e45c8cbc325355742a76e.mockapi.io/api/v1/")
                .when().get("users")
                .then().log().all()
                .assertThat().body("[0]",hasKey("country"));

    }
    @Test
    public void Extract(){
       Response response= given().baseUri("https://654e45c8cbc325355742a76e.mockapi.io/api/v1/")
                .when().get("users")
                .then().extract().response();
       // System.out.println(response.asString());
        JsonPath path=new JsonPath(response.asString());
        String name=path.getString("[0].name");
        System.out.println(name);
         JsonPath.from(response.asString()).getString("[0].name");

    }
    @Test
    public void IfValidationFails(){
        given().baseUri("https://654e45c8cbc325355742a76e.mockapi.io/api/v1/")
                .when().get("users")
                .then().log().ifValidationFails()
                .body("[0].name",is(equalTo("ahmed")));


    }

}
