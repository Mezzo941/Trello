package steps;

import org.openqa.selenium.WebDriver;
import pages.AtlassianLoginPage;
import pages.LoginPage;
import pages.TrelloAreaPage;

public abstract class BaseSteps {

    protected WebDriver driver;
    protected LoginPage loginPage;
    protected AtlassianLoginPage atlassianLoginPage;
    protected TrelloAreaPage trelloAreaPage;

    public BaseSteps(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(this.driver);
        atlassianLoginPage = new AtlassianLoginPage(this.driver);
        trelloAreaPage = new TrelloAreaPage(this.driver);
    }

}
