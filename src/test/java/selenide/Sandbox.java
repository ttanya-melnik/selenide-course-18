package selenide;


import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactOwnText;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.ClipboardConditions;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.openqa.selenium.Keys;

public class Sandbox {  // класс, и класс (контейнер для методов-тестов), имя класса


  SelenideElement s = $(""); // тип объекта Selenide, s — имя переменной, поиск по селектору

  //  ЗАКРЫТИЕ COOKIE-ПОПАПА

  @Test
    // аннотация
  void closeCookiePopup() { // метод, который ничего не возвращает и имя теста
    open("https://www.otpbank.ru/retail/bank-services/"); // открыть браузер

    // withText — селектор: элемент, у которого где-то есть этот текст, он должен быть видимым
    $(withText("Продолжая просмотр сайта")).shouldBe(visible);

    // поиск тега имеющего текст
    $(byTagAndText("strong", "Закрыть")).click();

    // withText — селектор: элемент, у которого где-то есть этот текст, элемент должен быть невидимым
    $(withText("Продолжая просмотр сайта")).shouldBe(hidden);


  }
 // PAGE OBJECT
  @Test
  void pageObject() {

    // new LoginPO().loginWithUsernameAndPassword("user", "admin1234"); // методы
    // new HomePO().title.shouldHave(text("My Balance"));
    // new HomePO().showAccountBalance();

  }

// ОБХОД BASIC AUTH ЧЕРЕЗ ESC
  @Test
  void closeBasicAuth() {
    open("https://the-internet.herokuapp.com/basic_auth");

    // actions() — интерфейс Actions из Selenium (для сложных действий: drag&drop, клавиатура),
    actions().sendKeys(Keys.ESCAPE); // sendKeys(Keys.ESCAPE) — нажимает клавишу Esc
    // sleep(5000);

  }

  // ПОИСК ПО ЧАСТИ АТРИБУТА
  @Test
  void findAttributeByValue() {
    $("[name$=order]").click(); // атрибут, который заканчивается на определенное слово
  }

  // ПРОВЕРКА ОБЯЗАТЕЛЬНОГО ПОЛЯ
  @Test
  void required() {
    $("input#username").shouldHave(attribute("required")); // у элемента должен быть данный атрибут
  }

  // РАБОТА С БУФЕРОМ ОБМЕНА
  @Test

  // @DisabledIfSystemProperty — тест отключится, если selenide.remote начинается с http,
  @DisabledIfSystemProperty(named = "selenide.remote", matches = "http.*", disabledReason = "Clipboard not supported on Selenium Grid yet")


  void clipboards() {
    open("https://moskva.mts.ru/personal"); //
    Selenide.clipboard().setText("1234324234"); // записать текст в буфер обмена
    $("[name=number]").sendKeys(Keys.COMMAND + "v"); // поиск по атрибуту, вставить
    $("[name=number]").shouldHave(value("(123) 432-42-34")); // поле должно заполниться с форматированием


    open("https://github.com/selenide/selenide.git");


    // ClipboardConditions.content - проверка, что в буфере именно этот текст
    Selenide.clipboard().shouldHave(ClipboardConditions.content("https://github.com/selenide/selenide.git"));



  }
 // exactText (полное совпадение текста)
  @Test
  void ownTextTest() {
    open("https://github.com");

    // проверка точного совпадения текста
    $("#home-code h3").shouldHave(exactText("Record or rewind any change to your code to keep you and your team in sync."));

  }


 // exactOwnText (только собственный текст, без детей)
  @Test
  void ownText2() {
    open("https://www.tutorialrepublic.com/snippets/preview.php?topic=bootstrap&file=elegant-contact-form");

    // exactOwnText — проверяет только прямой текст элемента, не заглядывая внутрь дочерних тегов
    $(".alert-info").shouldHave(exactOwnText("on to learn how to customize this layout further. Bootstrap 3 version of this snippet is ."));
  }



// не тест — просто демонстрация snapshot
  void snapshotsTest() {

    ElementsCollection checkboxes = $$("checkbox").filter(visible); // находим элементы по css, оставляем только видимые
    for (int i = 0; i < 10; i++) {
      checkboxes.get(i).shouldBe(enabled);
    }
    // .snapshot() — заморозить текущее состояние коллекции
    ElementsCollection checkboxesFast = $$("checkbox").filter(visible).snapshot();
    for (int i = 0; i < 10; i++) {
      checkboxesFast.get(i).shouldBe(enabled);
    }

    ElementsCollection collection = $$("").snapshot();
    collection.get(4).shouldHave(text("abc"));
    $("button").click();
    collection.get(5).shouldHave(text("abc"));
  }


}
