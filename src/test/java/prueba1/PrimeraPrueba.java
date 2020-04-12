package prueba1;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;


public class PrimeraPrueba {

    @Test
    public void testing(){
        System.out.println("Binvenidos al curso de laserants-fail");
        Assert.fail("La prueba fallo.");
    }

    @Test
    public void primerPruebaRestAssured(){
        Response response = given()
                        .header("Content-Type","application/json")
                        .when()
                        .get("http://localhost:5000/api/users")
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .body("size()", greaterThan(2))
                        .body("size()", greaterThanOrEqualTo(12))
                        .extract()
                .response();
        System.out.println(response.asString());
    }

}
