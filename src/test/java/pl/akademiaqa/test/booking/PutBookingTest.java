package pl.akademiaqa.test.booking;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.akademiaqa.dto.AuthDto;
import pl.akademiaqa.dto.BookingDto;
import pl.akademiaqa.request.auth.PostAuthRequest;
import pl.akademiaqa.request.booking.PutBookingRequest;
import pl.akademiaqa.request.booking.PostBookingRequest;

public class PutBookingTest {

    private static String token;

    @BeforeAll
    public static void setUp() {
        JSONObject defaultAuth = AuthDto.getDefaultAuth();
        token = PostAuthRequest.createToken(defaultAuth);
    }

    @Test
    void putBookingTest() {

        // creating new booking
        JSONObject defaultBooking = BookingDto.getDefaultBooking();
        Response createResponse = PostBookingRequest.postBooking(defaultBooking);
        String bookingId = createResponse.jsonPath().getString("bookingid");

        // object with new data
        JSONObject putBookingDates = new JSONObject();
        putBookingDates.put("checkin", "2020-01-01");
        putBookingDates.put("checkout", "2021-01-01");

        JSONObject putBooking = new JSONObject();
        putBooking.put("firstname", "Andrew");
        putBooking.put("lastname", "Pro");
        putBooking.put("totalprice", 2000);
        putBooking.put("depositpaid", false);
        putBooking.put("additionalneeds", "Lunch");
        putBooking.put("bookingdates", putBookingDates);

        // sending PUT to booking
        Response putResponse = PutBookingRequest.putBooking(bookingId, putBooking, token);
        JsonPath jsonPath = putResponse.jsonPath();

        Assertions.assertThat(jsonPath.getString("firstname")).isEqualTo("Andrew");
        Assertions.assertThat(jsonPath.getString("lastname")).isEqualTo("Pro");
        Assertions.assertThat(jsonPath.getString("totalprice")).isEqualTo("2000");
        Assertions.assertThat(jsonPath.getBoolean("depositpaid")).isFalse();
        Assertions.assertThat(jsonPath.getString("additionalneeds")).isEqualTo("Lunch");
        Assertions.assertThat(jsonPath.getString("bookingdates.checkin")).isEqualTo("2020-01-01");
        Assertions.assertThat(jsonPath.getString("bookingdates.checkout")).isEqualTo("2021-01-01");

        System.out.println(putResponse.asString());
    }
}
