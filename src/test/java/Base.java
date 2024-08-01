
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static io.restassured.RestAssured.given;

public class Base {
    TestData testData = new TestData();
    AuthConfig config = ConfigFactory.create(AuthConfig.class, System.getProperties());
    protected String token;

    @BeforeClass
    public void checkHealthBeforeTest() {
        given(baseRequestSpec)
                .when()
                .get("/ping")
                .then()
                .assertThat().statusCode(201);
    }

    @BeforeMethod
    public void createAuthToken() {
        AuthRequestModel authRequestModel = new AuthRequestModel(config.username(), config.password());
        AuthResponseModel response = ApiHelpers.createToken(authRequestModel);
        token = response.getToken();
    }
}
