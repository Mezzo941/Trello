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
    protected static final String BASE_URL = "https://trello.com/ru/home";
    protected static final By TITLE_PATH = By.tagName("h1");

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected boolean isOpened(String title, By titlePath) {
        WebElement element = null;
        boolean status = false;
        try {
            status = isScriptComplete();
            element = new WebDriverWait(driver, Duration.ofSeconds(25)).until(ExpectedConditions.visibilityOfElementLocated(titlePath));
        } catch (Exception e) {
            Assert.fail("Time is over. Page Didn't load");
        }
        log.info("Title of the page was loaded: " + element.getText() + ". Script's status is " + status);
        return element.getText().equals(title) && status;
    }

    protected boolean isOpened(By uniquePagesLocator) {
        WebElement element = null;
        boolean status = false;
        try {
            status = isScriptComplete();
            element = new WebDriverWait(driver, Duration.ofSeconds(25)).until(ExpectedConditions.visibilityOfElementLocated(uniquePagesLocator));
        } catch (TimeoutException e) {
            Assert.fail("Time is over. Page Didn't load");
        }
        log.info("Unique page's element is loaded. Script's status is " + status);
        return element.isDisplayed() && status;
    }

    private boolean isScriptComplete() {
        boolean status = new WebDriverWait(driver, Duration.ofSeconds(25))
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete"));
        if (status) {
            return true;
        } else {
            log.fatal("Scripts didn't complete");
            return false;
        }
    }

    public String getError(By loginError, By passError) {
        WebElement element = null;
        try {
            element = new WebDriverWait(driver, Duration.ofSeconds(25)).until(ExpectedConditions.visibilityOfElementLocated(loginError));
            log.info("get error after bad login. Error details: " + element.getText());
        } catch (TimeoutException e1) {
            try {
                element = new WebDriverWait(driver, Duration.ofSeconds(25)).until(ExpectedConditions.visibilityOfElementLocated(passError));
                log.info("get error after bad password. Error details: " + element.getText());
            } catch (TimeoutException e2) {
                Assert.fail("Error didn't load on the page: " + passError);
            }
        }
        return element.getText();
    }

    public WebDriver getDriver() {
        return driver;
    }

}
