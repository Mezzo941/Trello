package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.AtlassianLoginSteps;
import steps.SignUpSteps;

import java.time.Duration;

public class SignUpPage extends BasePage {

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    public void registrationByEmail(String email, String name) {

    }

}
