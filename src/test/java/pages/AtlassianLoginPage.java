package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ElementsTryCatcher;

@Log4j2
public class AtlassianLoginPage extends BasePage {

    protected static final By LOGIN_BUTTON = By.id("login-submit");
    protected static final By INPUT_PASS = By.id("password");
    protected static final By TITLE_PATH = By.xpath("//div[contains(text(),'Войдите')]");
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

    public void inputPassword(String pass) {
        log.info("Enter atlassian password");
        WebElement element = ElementsTryCatcher.protect(driver, INPUT_PASS);
        element.sendKeys(pass);
    }

    public void clickLoginButton() {
        log.info("click login button");
        WebElement element = ElementsTryCatcher.protect(driver, LOGIN_BUTTON);
        element.click();
    }
}
