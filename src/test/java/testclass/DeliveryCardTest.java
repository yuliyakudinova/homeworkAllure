package testclass;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import dataclass.DataGenerator;
import dataclass.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryCardTest {
    private Faker faker;
    Users info = DataGenerator
            .Registration
            .generateInfo("ru");

    @BeforeEach
    void setUpAll() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSendFormWithDate() {
        Configuration.holdBrowserOpen = true;
        String chooseDate = DataGenerator.chooseDate(5);
        $("[placeholder='Город']").setValue(info.getCityName());
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(chooseDate);
        $("[data-test-id='name'] input").setValue(info.getName());
        $("[data-test-id='phone'] input").setValue(info.getPhoneNumber());
        $("[data-test-id='agreement']").click();
        $(".grid-col button").click();
        $(withText("Успешно!")).shouldBe(visible);
        $("[data-test-id='success-notification'] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + chooseDate), Duration.ofSeconds(15));

        $(".grid-col button").click();
        $("[data-test-id='replan-notification'] .notification__content")
                .should(Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $("[data-test-id='replan-notification'] .button").click();
        $("[data-test-id='success-notification'] .notification__content")
                .should(Condition.text("Встреча успешно запланирована на " + chooseDate), Duration.ofSeconds(15));
    }

}
