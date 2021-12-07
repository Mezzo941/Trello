package steps;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.AtlassianLoginPage;
import pages.TrelloWorkingSpacePage;

public class AtlassianLoginSteps {

    private final AtlassianLoginPage atlassianLoginPage;

    public AtlassianLoginSteps(AtlassianLoginPage atlassianLoginPage) {
        this.atlassianLoginPage = atlassianLoginPage;
    }

    public void validLogin(String pass) {
        WebDriver driver = atlassianLoginPage.getDriver();
        atlassianLoginPage.inputPassword(pass);
        atlassianLoginPage.clickLoginButton();
        Assert.assertTrue(new TrelloWorkingSpacePage(driver).isOpened());
    }

    public void invalidLogin(String pass) {
        atlassianLoginPage.inputPassword(pass);
        atlassianLoginPage.clickLoginButton();
    }


}
