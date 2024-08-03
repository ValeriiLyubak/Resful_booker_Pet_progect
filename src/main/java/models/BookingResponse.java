package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingResponse {
    String firstName, lastName;

    @JsonProperty("totalprice")
    int totalPrice;

    @JsonProperty("depositpaid")
    boolean depositPaid;

    @JsonProperty("bookingdates")
    BookingDates bookingDates;

    @JsonProperty("additionalneeds")
    String additionalNeeds;
}
