package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class Click {

    private final WebDriver driver;

    public Click(WebDriver driver) {
        this.driver = driver;
    }

    public void byAction(WebElement element) {
        Actions action = new Actions(driver);
        action.click(element);
    }

    public void byJS(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

}
