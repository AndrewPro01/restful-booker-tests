package pl.akademiaqa.test.booking;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.akademiaqa.request.booking.GetBookingIDsRequest;

import static io.restassured.RestAssured.given;

public class GetAllBookingIDsTest {

    @Test
    void getBookingIDsTest() {

        Response response = GetBookingIDsRequest.getAllBookingIDs();
        JsonPath json = response.jsonPath();

        Assertions.assertThat(json.getList("bookingId").size()).isPositive();
    }
}
