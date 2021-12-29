package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Waiter;

@Log4j2
public class AtlassianLoginPage extends BasePage {

    protected static final By LOGIN_BUTTON = By.id("login-submit");
    protected static final By INPUT_PASS = By.id("password");
    protected static final By INPUT_EMAIL = By.id("username");
    protected static final By FOOTER_ATLASSIAN_LOGO = By.cssSelector("[aria-label='Atlassian']");
    protected static final By LOGIN_ERROR = By.id("login-error");
    protected static final By PASSWORD_ERROR = By.id("password-error");

    public AtlassianLoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpened() {
        boolean status = super.isOpened(FOOTER_ATLASSIAN_LOGO);
        if (status) {
            log.info("Atlassian page is opened");
            return true;
        } else {
            return false;
        }
    }

    public String getError() {
        return super.getError(LOGIN_ERROR, PASSWORD_ERROR);
    }

    @Step("Enter password")
    public void enterPassword(String pass) {
        log.info("Enter atlassian password: " + pass);
        WebElement element = Waiter.waitVisibilityOfElement(driver, INPUT_PASS);
        element.sendKeys(pass);
    }

    @Step("click login button")
    public void clickLoginButton() {
        log.info("click login button");
        WebElement element = Waiter.waitElementToBeClickable(driver, LOGIN_BUTTON);
        element.click();
    }

    @Step("Enter email")
    public void enterEmail(String email) {
        log.info("Enter email: " + email);
        WebElement element = Waiter.waitElementToBeClickable(driver, INPUT_EMAIL);
        element.sendKeys(email);
    }

}
