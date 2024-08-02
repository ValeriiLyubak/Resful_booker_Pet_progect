import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static spec.RestfulSpec.baseRequestSpecification;


@Epic("API tests for restful-booker")
@Feature("API health check")
public class HealthTest {
    @Test(description = "Health check endpoint to confirm the API is up")
    public void healthTestReturns201() {
        makeHealthTestRequestAndVerifyStatusCode();
    }

    @Step("Make health check request and verify it returns status 201")
    public void makeHealthTestRequestAndVerifyStatusCode() {
        int statusCode = given(baseRequestSpecification)
                .when()
                .get("/ping")
                .then()
                .extract().statusCode();
        assertEquals(statusCode, 201, "Status code should be 201");
    }
}
