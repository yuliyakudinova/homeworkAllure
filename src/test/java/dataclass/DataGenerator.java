package dataclass;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    public static class Registration {
        private Registration() {
        }

        public static Users generateInfo(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return new Users(faker.name().fullName(),
                    faker.phoneNumber().phoneNumber(),
                    faker.address().cityName());
        }
    }
    public static String chooseDate(int days) {
        return
                LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
