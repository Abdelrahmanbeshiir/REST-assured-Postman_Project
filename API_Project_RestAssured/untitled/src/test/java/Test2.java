import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class Test2 {
    @Test
    public void Header()
    {
        given().baseUri("https://todo.qacart.com/")
                .header("language","NONE").header("type","MANUAL")
                .when().get("api/v1/info/courses").then().log().all()
                .assertThat().statusCode(200);
    }
    @Test
    public void Headers()
    {
        Header header=new Header("type","MANUAL");
        Header header2=new Header("language","NONE");
        Headers headers=new Headers(header,header2);
        given().baseUri("https://todo.qacart.com/")
                .headers(headers)
                .when().get("api/v1/info/courses").then().log().all()
                .assertThat().statusCode(200);
    }
    @Test
    public void HashMAp()
    {
        HashMap<String ,String> Info=new HashMap<>();
        Info.put("type","MANUAL");
        Info.put("language","NONE");
        given().baseUri("https://todo.qacart.com/")
                .headers(Info)
                .when().get("api/v1/info/courses").then().log().all()
                .assertThat().statusCode(200);
    }
    @Test
    public void Queryparam()
    {

        given().baseUri("https://todo.qacart.com/").
                queryParam("mode","VIDEO")
                .queryParam("type","PAID")
                .when().get("api/v1/info/lectures").then().log().all()
                .assertThat().statusCode(200);
    }
    @Test
    public void Queryparams()
    {

        given().baseUri("https://todo.qacart.com/").
                queryParams("mode","VIDEO","type","PAID")
                .when().get("api/v1/info/lectures").then().log().all()
                .assertThat().statusCode(200);
    }
    @Test
    public void Shouldbeabletologin()
    {
        String data="Hi";
        given().baseUri("https://todo.qacart.com/").contentType(ContentType.JSON).
                body(data).
                when().post("api/v1/students/login").then().log().all()
                .assertThat().statusCode(200);
    }
    @Test
    public void File()
    {
        File body=new File("src/test/resources/login.json");
        given().baseUri("https://todo.qacart.com/").contentType(ContentType.JSON).
                body(body).
                when().post("api/v1/students/login").then().log().all()
                .assertThat().statusCode(200);
    }
    @Test
    public void BodyHashmap()
    {

        HashMap<String ,String> body=new HashMap<>();
        body.put("email","hatem@example.com");
        body.put("password","123456");

        given().baseUri("https://todo.qacart.com/").contentType(ContentType.JSON).
                body(body).
                when().post("api/v1/students/login").then().log().all()
                .assertThat().statusCode(200);
    }

}
