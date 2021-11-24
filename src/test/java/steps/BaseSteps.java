package steps;

import org.openqa.selenium.WebDriver;
import pages.AtlassianLoginPage;
import pages.LoginPage;

public abstract class BaseSteps {

    protected WebDriver driver;
    protected LoginPage loginPage;
    protected AtlassianLoginPage atlassianLoginPage;

    public BaseSteps(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(driver);
        atlassianLoginPage = new AtlassianLoginPage(driver);
    }

}
