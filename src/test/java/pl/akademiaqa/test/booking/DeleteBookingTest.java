package pl.akademiaqa.test.booking;

import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.akademiaqa.dto.AuthDto;
import pl.akademiaqa.dto.BookingDto;
import pl.akademiaqa.request.auth.PostAuthRequest;
import pl.akademiaqa.request.booking.DeleteBookingRequest;
import pl.akademiaqa.request.booking.PostBookingRequest;

public class DeleteBookingTest {

    private static String token;

    @BeforeAll
    public static void setUp() {
        JSONObject defaultAuth = AuthDto.getDefaultAuth();
        token = PostAuthRequest.createToken(defaultAuth);
    }

    @Test
    void deleteBookingTest() {

        // creating new booking
        JSONObject defaultBooking = BookingDto.getDefaultBooking();
        Response createResponse = PostBookingRequest.postBooking(defaultBooking);
        String bookingId = createResponse.jsonPath().getString("bookingid");

        // deleting booking
        Response deleteResponse = DeleteBookingRequest.deleteBooking(bookingId, token);
        Assertions.assertThat(deleteResponse.getStatusCode()).isEqualTo(201);
    }
}
