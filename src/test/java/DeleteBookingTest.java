import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import models.BookingRequest;
import org.testng.annotations.Test;

import static api.RestfulBookerApi.createBooking;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static spec.RestfulSpec.baseRequestSpecification;


@Epic("API tests for restful-booker")
@Feature("Delete booking")
public class DeleteBookingTest extends Base{
    @Test(description = "Delete request returns status 201")
    public void deleteBookingReturns201() {
        BookingRequest bookingRequest = testData.createBookingRequest();
        int id = createBooking(bookingRequest, token).getBookingId();
        step("Make booking delete request and verify it returns status code 201", () ->
                given(baseRequestSpecification)
                        .header("Cookie", "token=" + token)
                        .when()
                        .delete("/booking/" + id)
                        .then()
                        .assertThat().statusCode(201));
    }
}
