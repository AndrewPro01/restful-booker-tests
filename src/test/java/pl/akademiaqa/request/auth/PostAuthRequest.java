package pl.akademiaqa.request.auth;

import io.restassured.http.ContentType;
import org.json.JSONObject;
import pl.akademiaqa.url.BookingUrl;

import static io.restassured.RestAssured.given;

public class PostAuthRequest {

    public static String createToken(JSONObject json) {

        return given()
                .contentType(ContentType.JSON)
                .body(json.toString())
                .when()
                .post(BookingUrl.BASE_URL + BookingUrl.AUTH)
                .then()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath()
                .getString("token");


    }
}
