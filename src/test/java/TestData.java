import com.github.javafaker.Faker;
import lombok.Setter;
import models.BookingDates;
import models.BookingRequest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class TestData {
    final Faker faker = new Faker();

    @Setter
    String firstName, lastName, additionalNeeds, checkin, checkout;
    String[] needs = new String[]{"Breakfast", "Pets are allowed", "Twin beds", "Free parking", "None"};
    @Setter
    int totalPrice;
    @Setter
    boolean depositPaid;

    public LocalDate generateRandomDate() {
        int year = faker.number().numberBetween(2020, 2050);
        int month = faker.number().numberBetween(1, 12);
        int day = faker.number().numberBetween(1, 28);
        return LocalDate.of(year, month, day);
    }

    public void getDates () {
        LocalDate checkInDate = generateRandomDate();
        LocalDate checkoutDate = checkInDate.plusDays(faker.number().numberBetween(1, 30));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        setCheckin(checkInDate.format(dateTimeFormatter));
        setCheckout(checkoutDate.format(dateTimeFormatter));
    }

    public String getRandomItemFromArray(String [] items) {
        return faker.options().option(items);
    }

    public void generateData () {
        setFirstName(faker.name().firstName());
        setLastName(faker.name().lastName());
        setAdditionalNeeds(getRandomItemFromArray(needs));
        setTotalPrice(faker.number().numberBetween(100, 1000));
        setDepositPaid(faker.bool().bool());
    }

    BookingRequest createBookingRequest() {
        generateData();
        getDates();

        return BookingRequest.builder()
                .firstName(firstName)
                .lastName(lastName)
                .totalPrice(totalPrice)
                .depositPaid(depositPaid)
                .bookingDates(new BookingDates(checkin, checkout))
                .additionalNeeds(additionalNeeds)
                .build();
    }


}
