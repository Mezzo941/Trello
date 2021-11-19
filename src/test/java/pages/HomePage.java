package pages;

import com.sun.org.glassfish.gmbal.Description;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


@Log4j2
public class HomePage extends BasePage {

    private static final By LOG_IN_BUTTON = By.xpath("//div[@class='float-right buttons']/a[contains(text(),'Войти')]");
    private static final By SIGN_UP_BUTTON = By.cssSelector(".STHmZ");
    private static final By SIGN_UP_ITS_FREE_BUTTON = By.cssSelector(".iQKCgX");
    private static final By EMAIL_INPUT = By.name("email");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Description("Open start trello page")
    public HomePage open() {
        try {
            driver.get(BASE_URI);
            log.error(BASE_URI);
        } catch (Exception e) {
            Assert.fail("Incorrect URL :" + BASE_URI);
        }
        return this;
    }

    @Description("click to the login link")
    public LoginPage logIn() {
        driver.findElement(LOG_IN_BUTTON).click();
        return new LoginPage(driver);
    }

    @Description("Click to signup link")
    public SignUpPage signUp() {
        driver.findElement(SIGN_UP_BUTTON).click();
        return new SignUpPage(driver);
    }

    @Description("Click to the button named free sign up at the center")
    public SignUpPage freeSignUp() {
        driver.findElement(SIGN_UP_ITS_FREE_BUTTON).click();
        return new SignUpPage(driver);
    }

    @Description("Click to the button named free sign up at the center and enter email")
    public SignUpPage freeSignUp(String email) {
        WebElement button = driver.findElement(SIGN_UP_ITS_FREE_BUTTON);
        button.sendKeys(email);
        button.click();
        return new SignUpPage(driver);
    }

}
