package steps;

import org.openqa.selenium.WebDriver;
import pages.AtlassianLoginPage;
import pages.LoginPage;
import pages.TrelloWorkingSpacePage;

public abstract class BaseSteps {

    protected final WebDriver driver;
    protected LoginPage loginPage;
    protected AtlassianLoginPage atlassianLoginPage;
    protected TrelloWorkingSpacePage trelloWorkingSpacePage;

    public BaseSteps(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(this.driver);
        atlassianLoginPage = new AtlassianLoginPage(this.driver);
        trelloWorkingSpacePage = new TrelloWorkingSpacePage(this.driver);
    }

}
