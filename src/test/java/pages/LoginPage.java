package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

@Log4j2
public class LoginPage extends BasePage {

    private static final By USER = By.xpath("//input[@name='user'][@id='user']");
    private static final By PASS = By.cssSelector("[name='password']");
    private static final By SUBMIT_BUTTON = By.id("login");
    public static final By PASSWORD_ERROR = By.xpath("//div[@id='password-error']/p");
    public static final By ERROR = By.xpath("//div[@id='error']/p");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public String getError() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.visibilityOfElementLocated(ERROR));
            return driver.findElement(ERROR).getText();
        } catch (Exception e) {
            try {
                new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_ERROR));
                return driver.findElement(PASSWORD_ERROR).getText();
            } catch (NoSuchElementException elEx) {
                Assert.fail("No such element or page didn't load : " + PASSWORD_ERROR);
                return null;
            }
        }
    }

    public void authorization(String email, String password) {
        driver.findElement(USER).sendKeys(email);
        driver.findElement(PASS).sendKeys(password);
        driver.findElement(SUBMIT_BUTTON).click();
    }


}

