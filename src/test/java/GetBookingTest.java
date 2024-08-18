import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import models.BookingResponse;
import org.testng.annotations.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static spec.RestfulSpec.baseRequestSpecification;
import static org.testng.Assert.assertNotNull;
import static spec.RestfulSpec.bookingResponseSpecification;

@Epic("API tests for restful-booker")
@Feature("Get booking")
public class GetBookingTest {
    @Test(description = "Get all booking ids returns status 200")
    @Step("Get all booking ids test.")
    public void getAllBookingsReturns200() {
        step("Make get all booking request and verify it returns status code 200", () ->
                given(baseRequestSpecification)
                        .when()
                        .get("/booking")
                        .then()
                        .assertThat().statusCode(200));
    }

    @Test(description = "Get booking request returns not null data")
    public void getBookingReturnsNotNullData() {
        BookingResponse response = step("Make get data request by id", () ->
                given(baseRequestSpecification)
                        .contentType(JSON)
                        .when()
                        .get("booking/8")
                        .then()
                        .statusCode(200)
                        .spec(bookingResponseSpecification)
                        .extract().as(BookingResponse.class));
        step("Verify successful get data request", () ->
                assertNotNull(response.getFirstName(), "Firstname should not be null"));
    }
}
