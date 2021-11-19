package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SendLetterPage extends BasePage {

    private static final By MESSAGE = By.tagName("h5");
    private static final By EMAIL = By.xpath("//div[@id='email-sent-page']/p");
    private static final By CAPTCHA = By.cssSelector("[title='проверка recaptcha']");

    public SendLetterPage(WebDriver driver) {
        super(driver);
    }

    public String getMessage() {
        return driver.findElement(MESSAGE).getText();
    }

    public String getEmail() {
        return driver.findElement(EMAIL).getText();
    }

    public boolean isCaptchaVisible() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5)).
                    until(
                            ExpectedConditions.frameToBeAvailableAndSwitchToIt(CAPTCHA)
                    );
        } catch (NoSuchFrameException exception) {
            return false;
        }
        return true;
    }

}
