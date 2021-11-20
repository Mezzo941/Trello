package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.AtlassianSteps;
import steps.SignUpSteps;

import java.time.Duration;

public class SignUpPage extends BasePage {

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    public SendLetterPage registrationByEmail(String email, String name) {
        SignUpSteps.enterEmail(driver, email);
        SignUpSteps.clickContinue(driver);
        new WebDriverWait(driver, Duration.ofSeconds(5)).
                until
                        (
                                ExpectedConditions.visibilityOfElementLocated(

                                        AtlassianPage.REG_BUTTON
                                )
                        );
        AtlassianSteps.enterName(driver, name);
        AtlassianSteps.clickRegister(driver);
        return new SendLetterPage(driver);
    }

}
