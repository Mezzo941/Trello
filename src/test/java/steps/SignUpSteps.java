package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpSteps {

    private static final By EMAIL_INPUT = By.id("email");
    private static final By CONTINUE_BUTTON = By.id("signup-submit");

    public static void enterEmail(WebDriver driver, String email) {
        driver.findElement(EMAIL_INPUT).sendKeys(email);
    }

    public static void clickContinue(WebDriver driver){
        driver.findElement(CONTINUE_BUTTON).click();
    }

}
