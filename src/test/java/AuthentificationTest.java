import api.RestfulBookerApi;
import config.Configuration;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import models.AuthentificationRequest;
import models.AuthentificationResponse;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.qameta.allure.Allure.step;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@Epic("API tests for restful-booker")
@Feature("Authentification token")
public class AuthentificationTest {

    private Configuration configuration;

    @BeforeClass
    public void setUp() {
        configuration = ConfigFactory.create(Configuration.class, System.getProperties());
        System.out.println("Base URL: " + configuration.baseUrl());
        System.out.println("Username: " + configuration.username());
        RestAssured.baseURI = configuration.baseUrl();
        RestAssured.filters(new AllureRestAssured());
    }

    @Test
    @Step("Create authentication token test.")
    public void createAuthentificationToken() {
        AuthentificationRequest authentificationRequest = new AuthentificationRequest(
                configuration.username(),
                configuration.password()
        );

        AuthentificationResponse response = step("Make token request with user data", () ->
                RestfulBookerApi.createToken(authentificationRequest));
        step("Verify successful create token", () ->
                assertThat(response.getToken(), is(notNullValue())));
    }
}
