
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import models.BookingRequest;
import models.BookingResponse;
import models.CreateBookingResponse;
import org.testng.annotations.Test;

import static api.RestfulBookerApi.createBooking;
import static api.RestfulBookerApi.updateBooking;
import static io.qameta.allure.Allure.step;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.http.ContentType.JSON;
import static spec.RestfulSpec.baseRequestSpecification;
import static io.restassured.RestAssured.given;

@Epic("API tests for restful-booker")
@Feature("Create and update booking")
@Test(groups = "api")
public class CreateAndUpdateBookingTest extends Base {
    @Test(description = "Successful create a new booking")
    @Step("Successful create a new booking test.")
    public void successfulCreateNewBooking() {
        BookingRequest bookingRequest = testData.createBookingRequest();

        CreateBookingResponse response = step("Make create booking request", () ->
                createBooking(bookingRequest, token));
        step("Verify successful create new booking id", () ->
                assertThat(response.getBookingId(), is(notNullValue())));
        step("Verify successful create new booking with request data", () ->
                assertThat(response.getBookingRequest(), equalTo(bookingRequest)));
    }



    @Test(description = "Successful update booking data by id")
    @Step("Successful update booking data by id test.")
    public void successfulUpdateBooking() {
        BookingRequest bookingRequest = testData.createBookingRequest();
        int id = createBooking(bookingRequest, token).getBookingId();
        BookingRequest newBookingRequest = testData.createBookingRequest();

        BookingResponse response = step("Make update all booking data request", () ->
                updateBooking(newBookingRequest, token, id));

        step("Verify successful update firstname", () ->
                assertThat(response.getFirstName(), equalTo(newBookingRequest.getFirstname())));
        step("Verify successful update lastname", () ->
                assertThat(response.getLastName(), equalTo(newBookingRequest.getLastname())));
        step("Verify successful update total price", () ->
                assertThat(response.getTotalPrice(), equalTo(newBookingRequest.getTotalPrice())));
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
