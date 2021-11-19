package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AtlassianPage extends BasePage{

    protected static final By NAME_INPUT = By.id("displayName");
    protected static final By REG_BUTTON = By.id("signup-submit");

    public AtlassianPage(WebDriver driver) {
        super(driver);
    }


}
