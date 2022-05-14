package testclass;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;

import dataclass.Users;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static dataclass.DataGenerator.generateDate;
import static dataclass.DataGenerator.generateRequestCard;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.Keys.chord;

public class DeliveryCardTest{

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }


    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void test() {
        Users registrationInfo = generateRequestCard("ru");
        String planningDate = generateDate(4, 1);
        $x("//input[@type='text']").setValue(registrationInfo.getCity());
        $("[placeholder='Дата встречи']").sendKeys(chord(Keys.SHIFT, Keys.HOME), Keys.DELETE, planningDate);
        $x("//input[@name='name']").setValue(registrationInfo.getName());
        $x("//input[@name='phone']").setValue(registrationInfo.getPhone());
        $("[data-test-id='agreement']").click();
        $(withText("Запланировать")).click();
        $("[data-test-id='success-notification'] .notification__title").should(visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification'] .notification__content")
                .should(Condition.text("Встреча успешно запланирована на " + planningDate), Duration.ofSeconds(15));
        String secondDate = generateDate(5, 4);
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(secondDate);
        $(withText("Запланировать")).click();
        $("[data-test-id='success-notification'] .notification__content")
                .should(visible, ofSeconds(15))
                .shouldHave(exactText("Встреча успешно запланирована на  " + secondDate));
    }

}
