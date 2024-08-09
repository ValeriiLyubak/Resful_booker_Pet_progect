package spec;

import config.Configuration;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static org.hamcrest.Matchers.notNullValue;


public class RestfulSpec {
    static Configuration config = ConfigFactory.create(Configuration.class, System.getProperties());
    public static RequestSpecification baseRequestSpecification = with()
            .filter(new io.qameta.allure.restassured.AllureRestAssured())
            .log().uri()
            .log().body()
            .baseUri(config.baseUrl());

    public static ResponseSpecification authentificationResponseSpecification = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectBody("token", notNullValue())
            .build();

    public static ResponseSpecification createBookingResponseSpecification = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectBody("bookingid", notNullValue())
            .expectBody("booking", notNullValue())
            .build();

    public static ResponseSpecification bookingResponseSpecification = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectBody("firstname", notNullValue())
            .expectBody("lastname", notNullValue())
            .expectBody("totalprice", notNullValue())
            .expectBody("depositpaid", notNullValue())
            .expectBody("bookingdates", notNullValue())
            .build();
}
