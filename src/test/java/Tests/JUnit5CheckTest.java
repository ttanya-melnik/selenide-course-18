package Tests;

import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class JUnit5CheckTest {

  @BeforeAll
  static void configureBrowser() {
    Configuration.browserSize = "1920x1080";
    Configuration.baseUrl = "https://github.com/";
    Configuration.pageLoadStrategy = "eager";
    Configuration.timeout = 5000;
  }

  @AfterAll
  static void closeBrowser() {
    closeWebDriver();
  }

  @Test
  void verifyJUnit5AssertionsTest() {
    open("/selenide/selenide");
    $("#wiki-tab").click();
    $$("#wiki-body ul li").shouldHave(itemWithText("Soft assertions"));
    $$("#wiki-body ul li a").findBy(text("Soft Assertions")).click();
    $$(".markdown-heading").findBy(text("JUnit5")).sibling(0).shouldHave(text("""
            @ExtendWith({SoftAssertsExtension.class})
            class Tests {
                @Test
                void test() {
                    Configuration.assertionMode = SOFT;
                    open("page.html");

                    $("#first").should(visible).click();
                    $("#second").should(visible).click();
                }
            }"""));
  }
}

