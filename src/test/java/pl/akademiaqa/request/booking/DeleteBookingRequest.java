package pl.akademiaqa.request.booking;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pl.akademiaqa.url.BookingUrl;

import static io.restassured.RestAssured.given;

public class DeleteBookingRequest {

    public static Response deleteBooking(String bookingId, String token) {

        return given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .when()
                .delete(BookingUrl.BASE_URL + BookingUrl.BOOKING + "/" + bookingId)
                .then()
                .extract()
                .response();

    }
}
