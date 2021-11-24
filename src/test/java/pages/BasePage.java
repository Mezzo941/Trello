package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

@Log4j2
public abstract class BasePage {

    protected final WebDriver driver;
    protected static final String BASE_URI = "https://trello.com/";
    protected static final By TITLE_PATH = By.tagName("h1");

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected boolean isOpened(String title, By titlePath) {
        WebElement element = null;
        try {
            element = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(titlePath));
        } catch (TimeoutException e) {
            Assert.fail("Time is over. Page Didn't load");
        }
        log.info("Title of the page have loaded: " + element.getText());
        return driver.findElement(titlePath).getText().equals(title) && isScriptsComplete();
    }

    private boolean isScriptsComplete() {
        boolean status = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete"));
        if (status) {
            return true;
        } else {
            log.fatal("Scripts didn't complete");
            return false;
        }
    }

}
