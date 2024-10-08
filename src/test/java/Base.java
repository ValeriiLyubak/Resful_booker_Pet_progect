
import api.RestfulBookerApi;
import config.Configuration;
import io.qameta.allure.Step;
import models.AuthentificationRequest;
import models.AuthentificationResponse;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static io.restassured.RestAssured.given;
import spec.RestfulSpec;

public class Base {
    TestData testData = new TestData();
    Configuration configuration = ConfigFactory.create(Configuration.class, System.getProperties());
    protected String token;

    @BeforeClass
    public void checkHealthBeforeTest() {
        given(RestfulSpec.baseRequestSpecification)
                .when()
                .get("/ping")
                .then()
                .assertThat().statusCode(201);
    }

    @BeforeMethod
    @Step("Create authentication token")
    public void createAuthToken() {
        AuthentificationRequest authentificationRequest = new AuthentificationRequest(configuration.username(), configuration.password());
        AuthentificationResponse response = RestfulBookerApi.createToken(authentificationRequest);
        token = response.getToken();
        System.out.println("Generated token: " + token); // token check
    }
}
