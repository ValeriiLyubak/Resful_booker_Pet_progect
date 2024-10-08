package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateBookingResponse {
    @JsonProperty("bookingid")
    int bookingId;

    @JsonProperty("booking")
    BookingRequest bookingRequest;
}
