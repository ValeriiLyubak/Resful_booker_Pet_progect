import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import models.BookingRequest;
import models.BookingResponse;
import models.CreateBookingResponse;
import org.testng.annotations.Test;

import static api.RestfulBookerApi.createBooking;
import static api.RestfulBookerApi.updateBooking;
import static io.qameta.allure.Allure.step;
import static org.testng.AssertJUnit.*;
import static io.restassured.http.ContentType.JSON;
import static spec.RestfulSpec.baseRequestSpecification;
import static io.restassured.RestAssured.given;

@Epic("API tests for restful-booker")
@Feature("Create and update booking")
@Test(groups = "api")
public class CreateAndUpdateBookingTest extends Base {
    @Test(description = "Successful create a new booking")
    public void successfulCreateNewBooking() {
        BookingRequest bookingRequest = testData.createBookingRequest();

        CreateBookingResponse response = step("Make create booking request", () ->
                createBooking(bookingRequest, token));
        step("Verify successful create new booking id", () ->
                assertNotNull(response.getBookingId()));
        step("Verify successful create new booking with request data", () ->
                assertTrue(response.getBookingRequest().equals(bookingRequest)));
    }

    @Test(description = "Successful update booking data by id")
    public void successfulUpdateBooking() {
        BookingRequest bookingRequest = testData.createBookingRequest();
        int id = createBooking(bookingRequest, token).getBookingId();
        BookingRequest newBookingRequest = testData.createBookingRequest();

        BookingResponse response = step("Make update all booking data request", () ->
                updateBooking(newBookingRequest, token, id));
        step("Verify successful update firstname", () ->
                // Использую правильные методы для получения имени и фамилии
                assertEquals(response.getFirstName(), newBookingRequest.getFirstName()));
        step("Verify successful update lastname", () ->
                assertEquals(response.getLastName(), newBookingRequest.getLastName()));
        step("Verify successful update total price", () ->
                assertEquals(response.getTotalPrice(), newBookingRequest.getTotalPrice()));
    }

    @Test(description = "Unsuccessful update booking without auth token")
    public void UpdateBookingWithoutAuthTokenReturns403() {
        BookingRequest bookingRequest = testData.createBookingRequest();

        step("Make update data request without auth token and verify it returns status code 403", () ->
                given(baseRequestSpecification)
                        .body(bookingRequest)
                        .contentType(JSON)
                        .when()
                        .put("booking/7")
                        .then()
                        .assertThat().statusCode(403));


    }
}
