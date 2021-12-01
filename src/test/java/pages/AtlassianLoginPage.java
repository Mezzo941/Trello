package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementsTryCatcher;

import java.time.Duration;

@Log4j2
public class AtlassianLoginPage extends BasePage {

    protected static final By LOGIN_BUTTON = By.id("login-submit");
    protected static final By INPUT_PASS = By.id("password");
    protected static final By TITLE_PATH = By.xpath("//div[contains(text(),'Войдите')]");
    protected static final By LOGIN_ERROR = By.id("login-error");
    protected static final By PASSWORD_ERROR = By.id("password-error");
    protected static final String TITLE = "Войдите, чтобы перейти далее:";

    public AtlassianLoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpened() {
        boolean status = super.isOpened(TITLE, TITLE_PATH);
        if (status) {
            log.info("Atlassian page is opened");
            return true;
        } else {
            return false;
        }
    }

    public String getError() {
        WebElement element;
        try {
            element = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(LOGIN_ERROR));
            log.info("get error after bad password. Error details: " + element.getText());
            return driver.findElement(LOGIN_ERROR).getText();
        } catch (TimeoutException e) {
            element = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_ERROR));
            log.info("get error after bad password. Error details: " + element.getText());
            return driver.findElement(PASSWORD_ERROR).getText();
        }
    }

    public void inputPassword(String pass) {
        log.info("Enter atlassian password: " + pass);
        WebElement element = ElementsTryCatcher.protect(driver, INPUT_PASS);
        element.sendKeys(pass);
    }

    public void clickLoginButton() {
        log.info("click login button");
        WebElement element = ElementsTryCatcher.protect(driver, LOGIN_BUTTON);
        element.click();
    }
}
