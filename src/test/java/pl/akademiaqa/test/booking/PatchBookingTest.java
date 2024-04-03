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
import pl.akademiaqa.request.booking.PatchBookingRequest;
import pl.akademiaqa.request.booking.PostBookingRequest;

public class PatchBookingTest {

    private static String token;

    @BeforeAll
    public static void setUp() {
        JSONObject defaultAuth = AuthDto.getDefaultAuth();
        token = PostAuthRequest.createToken(defaultAuth);
    }

    @Test
    void partialUpdateBookingTest() {

        // creating new booking
        JSONObject defaultBooking = BookingDto.getDefaultBooking();
        Response createResponse = PostBookingRequest.postBooking(defaultBooking);
        String bookingId = createResponse.jsonPath().getString("bookingid");

        // object with new data
        JSONObject patchBooking = new JSONObject();
        patchBooking.put("firstname", "Andrew");
        patchBooking.put("lastname", "Pro");


        // sending PATCH to booking
        Response patchResponse = PatchBookingRequest.patchBooking(bookingId, patchBooking, token);
        JsonPath jsonPath = patchResponse.jsonPath();

        Assertions.assertThat(jsonPath.getString("firstname")).isEqualTo("Andrew");
        Assertions.assertThat(jsonPath.getString("lastname")).isEqualTo("Pro");

        System.out.println(patchResponse.asString());
    }
}
