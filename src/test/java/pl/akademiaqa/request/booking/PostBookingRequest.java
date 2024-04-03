package pl.akademiaqa.request.booking;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import pl.akademiaqa.url.BookingUrl;

import static io.restassured.RestAssured.given;

public class PostBookingRequest {

    public static Response postBooking(JSONObject booking){

        return given()
                .contentType(ContentType.JSON)
                .body(booking.toString())
                .when()
                .post(BookingUrl.BASE_URL + BookingUrl.BOOKING)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }
}
