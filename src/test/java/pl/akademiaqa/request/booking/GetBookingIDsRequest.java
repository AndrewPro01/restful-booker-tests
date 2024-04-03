package pl.akademiaqa.request.booking;

import io.restassured.response.Response;
import pl.akademiaqa.url.BookingUrl;

import static io.restassured.RestAssured.given;

public class GetBookingIDsRequest {

    public static Response getAllBookingIDs(){

        return given()
                .when()
                .get(BookingUrl.BASE_URL + BookingUrl.BOOKING)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }
}
