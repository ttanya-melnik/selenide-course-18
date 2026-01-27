package github; // название пакета

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import java.time.Duration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SelenideRepositorySearch { // делает метод доступным из-за пределов класса

  SelenideElement firstElement = $$(".Link__StyledLink-sc-1syctfj-0").filterBy(Condition.exactText("selenide/selenide")).get(0);
  // firstElement - можем придумать любое название
  // $$ - ищет все элементы на странице, которые подходят под данный селектор, возвращает коллекцию
  // filterBy(Condition.exactText("selenide/selenide")) — из всех ссылок оставим те, у которых такой текст
  // get(0) — берём самый первый из отфильтрованных
  @BeforeAll
  static void configure() { // статический метод, который не возвращает значения
    Configuration.timeout = 15000;           // 15 секунд — GitHub иногда медленно грузится
    Configuration.browserSize = "1920x1080";  // задали размер окна браузера
    Configuration.holdBrowserOpen = true;  // чтобы браузер не закрывался после прогона
  }

  @Test // аннотация
  void shouldFindSelenideRepositoryAtTheTop() {

    // открыть главную страницу
    open("https://github.com/");
    // кликнуть на поле поиска
    $("[placeholder='Search or jump to...']").click(); // находит поле поиска по атрибуту и нажимает
    // ввести selenide и нажать enter
    $("#query-builder-test").setValue("selenide").pressEnter(); // находит поле ввода по id, вводит selenide и нажимает Enter
    // кликнуть на первый репозиторий из списка найденных
    firstElement.shouldBe(Condition.visible, Duration.ofSeconds(10)); // ждёт до 10 сек, пока ссылка «selenide/selenide» не станет видимой
    firstElement.click();
    // проверка: заголовок selenide/selenide
    $("#repository-container-header").shouldHave(text("selenide / selenide")); // проверяет, что в шапке репозитория есть текст

  }
}





