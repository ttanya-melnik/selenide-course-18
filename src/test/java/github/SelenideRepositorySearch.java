package github; // название пакета

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SelenideRepositorySearch {

  @BeforeAll
  static void configure() {
    Configuration.timeout = 15000;           // 15 секунд — GitHub иногда медленно грузится
    Configuration.browserSize = "1440x900";  // задали размер окна браузера

  }

  @Test
  void shouldFindSelenideRepositoryAtTheTop() {

    // открыть главную страницу
    open("https://github.com/");
    // кликнуть на поле поиска
    $(".header-search-button").click();
    // ввести selenide и нажать enter
    // кликнуть на первый репозиторий из списка найденных
    // проверка: заголовок selenide/selenide

  }
}





