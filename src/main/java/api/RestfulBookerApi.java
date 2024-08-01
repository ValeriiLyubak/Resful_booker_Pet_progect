package api;

import models.*;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static spec.RestfulSpec.*;

public class RestfulBookerApi {
    public static AuthentificationResponse createToken(AuthentificationRequest authentificationRequest) {
        return given(baseRequestSpecification)
                .contentType(JSON)
                .body(authentificationRequest)
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .spec(authentificationResponseSpecification)
                .extract().as(AuthentificationResponse.class);
    }

    public static CreateBookingResponse createBooking(BookingRequest bookingRequest, String token) {
        return given(baseRequestSpecification)
                .header("Cookie", "token" + token)
                .contentType(JSON)
                .body(bookingRequest)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .spec(createBookingResponseSpecification)
                .extract().as(CreateBookingResponse.class);
    }

    public static BookingResponse updateBooking(BookingRequest bookingRequest, String token, int id) {
        return given(baseRequestSpecification)
                .header("Cookie", "token" + token)
                .contentType(JSON)
                .body(bookingRequest)
                .when()
                .put("/booking" + id)
                .then()
                .statusCode(200)
                .spec(bookingRequestSpecification)
                .extract().as(BookingResponse.class);
    }

}
