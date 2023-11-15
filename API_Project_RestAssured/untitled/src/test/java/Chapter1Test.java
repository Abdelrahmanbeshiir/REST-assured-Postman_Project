import io.restassured.http.ContentType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class Chapter1Test {
    @BeforeTest

   @Test
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills() {

        given().
                when().
                get("http://zippopotam.us/us/90210").
                then().
               assertThat().statusCode(200).assertThat().
               body("places[0].'place name'", equalTo("Beverly Hills"));
    }
    @Test
    public void requestUsZipCode90210_prettyprint() {

        given().
                when().
                get("http://zippopotam.us/us/90210").prettyPrint();

    }
    @Test
    public void requestUsZipCode90210_CheckStatusCode() {

        given().
                when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().statusCode(200);

    }
    @Test
    public void requestUsZipCode90210_HasItem() {

        given().
                when().
                get("http://zippopotam.us/us/90210").
                then().
              assertThat().body("places.state",hasItem("California"));
    }
    @Test
    public void requestUsZipCode90210_HasSize() {

        given().
                when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().body("places.state",hasSize(1));
    }
    @Test
    public void requestUsZipCode90210_checkContentType_expectApplicationJson() {

        given().
                when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().
                contentType("application/json");
    }
    @Test
    public void requestUsZipCode90210_checkContentType_expectApplicationJson2() {

        given().
                when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().
                contentType(ContentType.JSON);
    }
    @DataProvider
    public static Object[][] zipCodesAndPlaces() {
        return new Object[][] {
                { "us", "90210", "Beverly Hills" },
                { "us", "12345", "Schenectady" },
                { "ca", "B2R", "Waverley"}
        };
    }

    @Test(dataProvider = "zipCodesAndPlaces")

    public void requestZipCodesFromCollection_checkPlaceNameInResponseBody_expectSpecifiedPlaceName(String countryCode, String zipCode, String expectedPlaceName) {

        given().
                pathParam("countryCode", countryCode).pathParam("zipCode", zipCode).
                when().
                get("http://zippopotam.us/{countryCode}/{zipCode}").
                then().log().all().and().
                assertThat().
                body("places[0].'place name'", equalTo(expectedPlaceName));
    }

}
