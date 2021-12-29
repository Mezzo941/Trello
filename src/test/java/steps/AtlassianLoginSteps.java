package steps;

import org.testng.Assert;
import pages.AtlassianLoginPage;
import pages.WorkspacePage;

public class AtlassianLoginSteps {

    private final AtlassianLoginPage atlassianLoginPage;
    private final WorkspacePage workspacePage;

    public AtlassianLoginSteps(AtlassianLoginPage atlassianLoginPage, WorkspacePage workspacePage) {
        this.atlassianLoginPage = atlassianLoginPage;
        this.workspacePage = workspacePage;
    }

    public void loginViaEmail(String pass) {
        atlassianLoginPage.enterPassword(pass);
        atlassianLoginPage.clickLoginButton();
        Assert.assertTrue(workspacePage.isOpened());
    }

    public void invalidLogin(String pass) {
        atlassianLoginPage.enterPassword(pass);
        atlassianLoginPage.clickLoginButton();
    }

    public void loginViaUsername(String email, String pass) {
        atlassianLoginPage.enterEmail(email);
        atlassianLoginPage.clickLoginButton();
        atlassianLoginPage.enterPassword(pass);
        atlassianLoginPage.clickLoginButton();
        Assert.assertTrue(workspacePage.isOpened());
    }


}
