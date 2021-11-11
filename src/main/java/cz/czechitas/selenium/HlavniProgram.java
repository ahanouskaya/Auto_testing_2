package cz.czechitas.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class HlavniProgram {
    WebDriver prohlizec;

    @BeforeEach
    public void setup() {
//        System.setProperty("webdriver.gecko.driver", System.getProperty("user.home") + "/Java-Training/Selenium/geckodriver");
        System.setProperty("webdriver.gecko.driver", "C:\\Java-Training\\Selenium\\geckodriver.exe");
        prohlizec = new FirefoxDriver();
        prohlizec.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }


    @Test  // TODO: Zkontroluj, ze 7 == seznamZvirat.size()
    public void zviratmusiByt7(){
        prejdiNaTabulku();

        List<WebElement> seznamZvirat = prohlizec.findElements(By.xpath("//table[contains(@class, 'qa-animals')]//td[1]"));

        Assertions.assertEquals(7, seznamZvirat.size(), "Pocet zvirat nesedi ");
    }

    @Test   // TODO: Zkontroluj, ze "Kočka" == prvniZvire
    public void prvniZvireJeKocka() {
        prejdiNaTabulku();

        List<WebElement> seznamZvirat = prohlizec.findElements(By.xpath("//table[contains(@class, 'qa-animals')]//td[1]"));

        WebElement elementPrvnihoZvirete = seznamZvirat.get(0);
        String prvniZvire = elementPrvnihoZvirete.getText();

        Assertions.assertEquals("Kočka", prvniZvire, "Prvni zvire takove jako cekas ");
    }


    public void prejdiNaTabulku() {
        prohlizec.navigate().to("https://automation-playground.czechitas.repl.co/");
        WebElement zalozkaTabulka = prohlizec.findElement(By.id("table"));
        zalozkaTabulka.click();
    }

    @AfterEach
    public void tearDown() {

        prohlizec.quit();
    }
}
