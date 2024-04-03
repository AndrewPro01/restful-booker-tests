package pl.akademiaqa.test.booking;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import pl.akademiaqa.dto.BookingDto;
import pl.akademiaqa.request.booking.PostBookingRequest;

public class PostBookingTest {

    @Test
    void postBookingTest() {


        final JSONObject booking = BookingDto.getDefaultBooking();
        Response response = PostBookingRequest.postBooking(booking);

        JsonPath json = response.jsonPath();
        Assertions.assertThat(json.getString("booking.firstname")).isEqualTo("Liuba");
        Assertions.assertThat(json.getString("booking.lastname")).isEqualTo("Moroz");

    }
}
