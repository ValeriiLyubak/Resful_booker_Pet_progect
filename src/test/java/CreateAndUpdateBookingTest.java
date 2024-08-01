import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

@Epic("API tests for restful-booker")
@Feature("Create and update booking")
public class CreateAndUpdateBookingTests extends TestBase {

    @Test
    public void successfulCreateNewBooking() {
        BookingRequestModel bookingRequestModel = testData.createBookingRequestModel();

        CreateBookingResponseModel response = step("Make create booking request", () ->
                createBooking(bookingRequestModel, token));
        step("Verify successful create new booking id", () ->
                assertThat(response.getBookingId()).isNotNull());
        step("Verify successful create new booking with request data", () ->
                assertThat(response.getBookingRequestModel().equals(bookingRequestModel)));
    }

    @Test
    public void successfulUpdateBooking() {
        BookingRequestModel bookingRequestModel = testData.createBookingRequestModel();
        int id = createBooking(bookingRequestModel, token).getBookingId();
        BookingRequestModel newBookingRequestModel = testData.createBookingRequestModel();

        BookingResponseModel response = step("Make update all booking data request", () ->
                updateBooking(newBookingRequestModel, token, id));
        step("Verify successful update firstname", () ->
                assertThat(response.getFirstname()).isEqualTo(newBookingRequestModel.getFirstname()));
        step("Verify successful update lastname", () ->
                assertThat(response.getLastname()).isEqualTo(newBookingRequestModel.getLastname()));
        step("Verify successful update total price", () ->
                assertThat(response.getTotalPrice()).isEqualTo(newBookingRequestModel.getTotalPrice()));
    }

    @Test
    public void UpdateBookingWithoutAuthTokenReturns403() {
        BookingRequestModel bookingRequestModel = testData.createBookingRequestModel();

        step("Make update data request without auth token and verify it returns status code 403", () ->
                given(baseRequestSpec)
                        .body(bookingRequestModel)
                        .contentType(JSON)
                        .when()
                        .put("booking/7")
                        .then()
                        .assertThat().statusCode(403));
    }
}