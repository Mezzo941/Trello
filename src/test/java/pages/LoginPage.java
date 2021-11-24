package pages;

import com.sun.org.glassfish.gmbal.Description;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementsTryCatcher;

import java.time.Duration;

@Log4j2
public class LoginPage extends BasePage {

    private static final By INPUT_LOGIN = By.xpath("//input[@name='user'][@id='user']");
    private static final By INPUT_PASS = By.cssSelector("[name='password']");
    private static final By SUBMIT_BUTTON = By.id("login");
    public static final By PASSWORD_ERROR = By.xpath("//div[@id='password-error']/p");
    public static final By ERROR = By.xpath("//div[@id='error']/p");
    private static final String TITLE = "Вход в Trello";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Description("Login trello page is opened")
    public boolean isOpened() {
        boolean status = super.isOpened(TITLE,TITLE_PATH);
        if (status) {
            log.info("Login page is opened");
            return true;
        }
        else {
            return false;
        }
    }

    public String getError() {
        log.info("get error after bad login ");
        try {
            new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.visibilityOfElementLocated(ERROR));
            return driver.findElement(ERROR).getText();
        } catch (TimeoutException e1) {
            try {
                new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_ERROR));
                return driver.findElement(PASSWORD_ERROR).getText();
            } catch (TimeoutException e2) {
                log.error("Element didn't load on the page: " + PASSWORD_ERROR);
                return null;
            }
        }
    }

    public void inputLogin(String login) {
        log.info("input login: " + login);
        WebElement element = ElementsTryCatcher.protect(driver, INPUT_LOGIN);
        element.sendKeys(login);
    }

    public void inputPassword(String pass) {
        log.info("input password: " + pass);
        WebElement element = ElementsTryCatcher.protect(driver, INPUT_PASS);
        element.sendKeys(pass);
    }

    public void clickLoginButton() {
        log.info("click to login button");
        WebElement element = ElementsTryCatcher.protect(driver, SUBMIT_BUTTON);
        element.click();
    }

}

