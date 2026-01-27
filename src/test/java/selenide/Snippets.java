package selenide;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.CollectionCondition.exactTextsCaseSensitiveInAnyOrder;
import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.CollectionCondition.sizeLessThan;
import static com.codeborne.selenide.CollectionCondition.sizeLessThanOrEqual;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.CollectionCondition.textsInAnyOrder;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.attributeMatching;
import static com.codeborne.selenide.Condition.checked;
import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exactTextCaseSensitive;
import static com.codeborne.selenide.Condition.exactValue;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.textCaseSensitive;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selectors.withTagAndText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.Selenide.element;
import static com.codeborne.selenide.Selenide.elements;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.AuthenticationType;
import com.codeborne.selenide.BasicAuthCredentials;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.DownloadOptions;
import com.codeborne.selenide.FileDownloadMode;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import org.openqa.selenium.Keys;
import org.openqa.selenium.bidi.network.Cookie;

public class Snippets {

  // КОМАНДЫ БРАУЗЕРА

    void browser_command_examples() {
      open("https://google.com"); // открыли страницу
      open("/customer/orders"); // перешли на конкретный раздел
      open("/", AuthenticationType.BASIC,
          new BasicAuthCredentials("", "user", "password"));

      Selenide.back(); // возвращает браузер к предыдущей странице
      Selenide.refresh(); // перезагрузка страницы

      Selenide.clearBrowserCookies(); // очистит все куки
      Selenide.clearBrowserLocalStorage(); // удалит LocalStorage
      executeJavaScript("sessionStorage.clear();"); // очистит sessionStorage

      Selenide.confirm(); // нажимает кнопку 'OK' в выпадающем окне
      Selenide.dismiss(); //  нажимает кнопку 'cancel' в выпадающем окне

      Selenide.closeWindow(); // закрыть окно
      Selenide.closeWebDriver(); // закрыть все окна браузера

      Selenide.switchTo().frame("new"); // прешли в фрейм ('фрейм'-независимое дом-дерево)
      Selenide.switchTo().defaultContent(); // 'defaultContent' - перешли к главному дереву страницы

      Selenide.switchTo().window("The Internet"); // перейти в предыдущее окно

//      var cookie = new Cookie("foo", "bar"); // определили куки
//      WebDriverRunner.getWebDriver().manage().addCookie(cookie); // getWebDriver - достает объект WebDriver


    }
    // СЕЛЕКТОРЫ
    void selectors_examples() {
      $("div").click(); // стандартный поиск по css - селектору
      element("div").click(); // $ и element - одно и то же

      $("div", 2).click(); // ищет второй div

      $x("//h1/div").click(); // поиск по XPath
      $(byXpath("//h1/div")).click();

      $(byText("full text")).click(); // поиск по полному тексту
      $(withText("ull tex")).click(); // поиск по частичному схождению

      $(byTagAndText("div", "full text")); // поиск по тегу, имеющему определенный текст
      $(withTagAndText("div", "ull text")); // поиск по тегу, имеющему некоторый текст

      $("").parent(); // родитель элемента (один уровень вверх)
      $("").sibling(1); // сосед снизу
      $("").preceding(1); // сосед сверху
      $("").closest("div"); // вверх, пока не найдет подходящий элемент
      $("").ancestor("div"); //
      $("div:last-child"); // ищет самого последнего ребенка

      $("div").$("h1").find(byText("abc")).click();
    // находим сначала div потом h1 и в этом теге ищем текст и кликаем по нему

      $(byAttribute("abc", "x")).click();
      $("[abc=x]").click(); // поиск по атрибуту

      $(byId("mytext")).click();
      $("#mytext").click(); // поиск по id (id может начинаться с цифры)

      $(byClassName("red")).click();
      $(".red").click(); // поиск по классу
    }
    // ДЕЙСТВИЯ
    void actions_examples() {
      $("").click(); // клик
      $("").doubleClick(); // двойной клик
      $("").contextClick(); // правый клик

      $("").hover(); // поднести мышку но не нажимать

      $("").setValue("text"); // сотрёт текст, напишет новый
      $("").append("text"); // добавит текст, не стирая
      $("").clear(); // удаляет текст (может работать неправильно)
      $("").setValue(""); // удаляет текст

      $("div").sendKeys("c"); // ?
      actions().sendKeys("c").perform();
      actions().sendKeys(Keys.chord(Keys.CONTROL, "f")).perform();
      $("html").sendKeys(Keys.chord(Keys.CONTROL, "f"));

      $("").pressEnter(); // нажать Enter
      $("").pressEscape(); // нажать на Escape
      $("").pressTab(); // нажать на Tab



      actions().moveToElement($("div")).clickAndHold().moveByOffset(300, 200).release().perform();


      $("").selectOption("dropdown_option"); // работа с дропдаунами
      $("").selectRadio("radio_options"); // работа с радио-боксами

    }
    // ПРОВЕРКИ
    void assertions_examples() {
      $("").shouldBe(visible); // должен быть виден элемент
      $("").shouldNotBe(visible); // должен исчезнуть
      $("").shouldHave(text("abc")); // должен содержать текст abc
      $("").shouldNotHave(text("abc")); // текст abc- должен исчезнуть
      $("").should(appear); // появился на экране
      $("").shouldNot(appear); // не должен появиться



      $("").shouldBe(visible, Duration.ofSeconds(30)); // установили таймаут

    }
    // ЧТО ИМЕННО ПРОВЕРЯЕМ
    void conditions_examples() {
      $("").shouldBe(visible); // (содержит condition) проверка на видимость
      $("").shouldBe(hidden); // (содержит condition) должен быть не видим


      $("").shouldHave(text("abc")); // (содержит condition) должен содержать текст
      $("").shouldHave(exactText("abc")); // (содержит condition) должен содержать abc, и ничего больше
      $("").shouldHave(textCaseSensitive("abc")); // (содержит condition) если нужно проверить кейс
      $("").shouldHave(exactTextCaseSensitive("abc")); // (содержит condition) если нужно проверить кейс
      $("").should(matchText("[0-9]abc$")); // (содержит condition) проверяем формат


      $("").shouldHave(cssClass("red")); // (содержит condition) проверка, что есть класс с названием red
      $("").shouldHave(cssValue("font-size", "12")); // (содержит condition), проверяем во вкладке Computed


      $("").shouldHave(value("25")); // (содержит condition) ищет подстроку
      $("").shouldHave(exactValue("25")); // (содержит condition) ищет полностью строку
      $("").shouldBe(empty); // (содержит condition) проверка, что поле пустое


      $("").shouldHave(attribute("disabled")); // првоерка наличия атрибута
      $("").shouldHave(attribute("name", "example")); // проверка значения атрибута
      $("").shouldHave(attributeMatching("name", "[0-9]abc$")); //

      $("").shouldBe(checked); // проверка чекбокса


      $("").should(exist); // проверяет наличие элемента в доме


      $("").shouldBe(disabled); // элемент неактивен, с ним запрещено взаимодействовать
      $("").shouldBe(enabled); // элемент активен, и с ним можно взаимодействовать
    }
    // КОЛЛЕКЦИИ
    void collections_examples() {

      $$("div"); // найти все div

      $$x("//div"); // найти все div  с использованием XPath


      $$("div").filterBy(text("123")).shouldHave(size(1));
      // из всех div выбираем текст, где есть 123
      $$("div").excludeWith(text("123")).shouldHave(size(1));
      // из всех div останутся элементы, не имеющие 123

      $$("div").first().click(); // перейти в первый элемент
      elements("div").first().click();

      $$("div").last().click(); // перейти в последний элемент
      $$("div").get(1).click(); // перейти во 2 элемент, так как начинается с 0
      $("div", 1).click(); // перейти во 2 элемент, так как начинается с 0
      $$("div").findBy(text("123")).click(); // фильтруем элементы


      $$("").shouldHave(size(0)); // проверка размера
      $$("").shouldBe(CollectionCondition.empty);

      $$("").shouldHave(texts("Alfa", "Beta", "Gamma")); // проверка количества данных текстов
      $$("").shouldHave(exactTexts("Alfa", "Beta", "Gamma")); // проверка количеста текста и точное написание

      $$("").shouldHave(textsInAnyOrder("Beta", "Gamma", "Alfa")); // текст может быть в любом порядке
      $$("").shouldHave(exactTextsCaseSensitiveInAnyOrder("Beta", "Gamma", "Alfa"));

      $$("").shouldHave(itemWithText("Gamma"));// проверяет что в коллекции есть элемент с данным текстом

      $$("").shouldHave(sizeGreaterThan(0)); // проверяем, что больше 0 элементов
      $$("").shouldHave(sizeGreaterThanOrEqual(1)); // проверяем, что больше 1 элемента
      $$("").shouldHave(sizeLessThan(3)); // првоерка, что меньше чем 3 элемента
      $$("").shouldHave(sizeLessThanOrEqual(2)); // првоерка, что меньше или равно 2 элементам


    }
    // ОПЕРАЦИИ С ФАЙЛАМИ
    void file_operation_examples() throws FileNotFoundException {

      File file1 = $("a.fileLink").download(); //
      File file2 = $("div").download(DownloadOptions.using(FileDownloadMode.FOLDER));

      File file = new File("src/test/resources/readme.txt");
      $("#file-upload").uploadFile(file);
      $("#file-upload").uploadFromClasspath("readme.txt");

      $("uploadButton").click();
    }

    void javascript_examples() {
      executeJavaScript("alert('selenide')");
      executeJavaScript("alert(arguments[0]+arguments[1])", "abc", 12);
      long fortytwo = executeJavaScript("return arguments[0]*arguments[1];", 6, 7);

    }
  }

