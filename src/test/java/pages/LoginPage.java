package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Waiter;

import java.time.Duration;

@Log4j2
public class LoginPage extends BasePage {

    private static final By INPUT_LOGIN = By.xpath("//input[@name='user'][@id='user']");
    private static final By INPUT_PASS = By.cssSelector("[name='password']");
    private static final By SUBMIT_BUTTON = By.id("login");
    public static final By PASSWORD_ERROR = By.xpath("//div[@id='password-error']/p");
    public static final By LOGIN_ERROR = By.xpath("//div[@id='error']/p");
    private static final String TITLE = "Вход в Trello";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Login trello page is opened")
    public boolean isOpened() {
        boolean status = super.isOpened(TITLE, TITLE_PATH);
        if (status) {
            log.info("Login page is opened");
            return true;
        } else {
            return false;
        }
    }

    public String getError() {
        return super.getError(LOGIN_ERROR, PASSWORD_ERROR);
    }

    @Step("Input login")
    public void inputLogin(String login) {
        log.info("input login: " + login);
        WebElement element = Waiter.waitVisibilityOfElement(driver, INPUT_LOGIN);
        element.sendKeys(login);
    }

    @Step("Input password")
    public void inputPassword(String pass) {
        log.info("input password: " + pass);
        WebElement element = Waiter.waitVisibilityOfElement(driver, INPUT_PASS);
        element.sendKeys(pass);
    }

    @Step("Submit login")
    public void clickLoginViaAtlassian() {
        log.info("click to login button");
        WebElement element = Waiter.waitElementToBeClickable(driver, SUBMIT_BUTTON);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(INPUT_PASS));
        element.click();
    }

    @Step("Submit login")
    public void clickLoginButton() {
        log.info("click to login button");
        WebElement element = Waiter.waitElementToBeClickable(driver, SUBMIT_BUTTON);
        element.click();
    }

}

