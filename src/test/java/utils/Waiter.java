package utils;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


@Log4j2
public class Waiter {

    public static WebElement waitVisibilityOfElement(WebDriver driver, By locator) {
        WebElement element = null;
        try {
            element = new WebDriverWait(driver, Duration.ofSeconds(25)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            Assert.fail("Element didn't load: " + locator);
            e.printStackTrace();
        }
        return element;
    }

    public static WebElement waitElementToBeClickable(WebDriver driver, By locator){
        WebElement element = null;
        try {
            element = new WebDriverWait(driver, Duration.ofSeconds(25)).until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            Assert.fail("Element isn't clickable: " + locator);
            e.printStackTrace();
        }
        return element;
    }

}
