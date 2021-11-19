package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AtlassianSteps {

    private final static By NAME_INPUT = By.id("displayName");
    private final static By SIGN_UP_BUTTON = By.id("signup-submit");

    public static void enterName(WebDriver driver, String name){
        driver.findElement(NAME_INPUT).sendKeys(name);
    }

    public static void clickRegister(WebDriver driver){
        driver.findElement(SIGN_UP_BUTTON).click();
    }

}
