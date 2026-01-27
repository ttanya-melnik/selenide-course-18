package github;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.Configuration;
import java.time.Duration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BestContributorToSelenide {

  @BeforeAll
  static void setup() {
    Configuration.browserSize = "1920x1080"; // Делаем окно большим, чтобы ничего не съезжало
    Configuration.pageLoadStrategy = "eager"; // Тесты запускаются быстрее
    Configuration.timeout = 15000;           // 15 секунд
    Configuration.holdBrowserOpen = true;  // После выполнения теста, браузер не закрывается автоматически.
  }

  @Test
  void andreiSolntsevShouldBeTheFirstContributor1() {

    // открыть страницу репозитория селенида
    open("https://github.com/selenide/selenide");

    // подвести мышку к первой фотографии из блока contributors
    $("a[href='https://github.com/asolntsev']").shouldBe(visible)
        .hover(); // Находим элемент <a> с точным href на профиль Андрея и наводим на него мышку

    // Ждём появления попапа и проверяем, что в нём есть нужный текст
    $("div[class='Popover js-hovercard-content position-absolute']").shouldBe(visible, Duration.ofSeconds(5));

    // проверка: во всплывающем окне есть текст "Andrei Solntsev"
    $("a[class='Link--secondary no-underline ml-1']").shouldHave(text("Andrei Solntsev"));
  }
}
