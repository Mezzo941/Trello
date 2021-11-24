package utils;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.testng.Assert;

@Log4j2
public class ElementsTryCatcher {

    public static WebElement protect(WebDriver driver, By webElement) {
        WebElement element = null;
        try {
            try {
                element = driver.findElement(webElement);
            } catch (InvalidSelectorException e) {
                Assert.fail("Element didn't load. Invalid selector: " + webElement);
            }
        } catch (NoSuchElementException e1) {
            Assert.fail("Can't locate element on the page: " + webElement);
        }
        return element;
    }

}
