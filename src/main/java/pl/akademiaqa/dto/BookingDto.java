package pl.akademiaqa.dto;

import org.json.JSONObject;

public class BookingDto {

    public static JSONObject getDefaultBooking() {

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");

        JSONObject booking = new JSONObject();
        booking.put("firstname", "Liuba");
        booking.put("lastname", "Moroz");
        booking.put("totalprice", 1000);
        booking.put("depositpaid", true);
        booking.put("additionalneeds", "Breakfast");
        booking.put("bookingdates", bookingDates);

        return booking;
    }
}
