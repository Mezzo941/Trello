package steps;

import org.testng.Assert;
import pages.AtlassianLoginPage;
import pages.LoginPage;


public class LoginSteps {

    private final LoginPage loginPage;
    private final AtlassianLoginPage atlassianLoginPage;

    public LoginSteps(LoginPage loginPage, AtlassianLoginPage atlassianLoginPage) {
        this.loginPage = loginPage;
        this.atlassianLoginPage = atlassianLoginPage;
    }

    public void negativeAuthorization(String login, String pass) {
        loginPage.inputLogin(login);
        loginPage.inputPassword(pass);
        loginPage.clickLoginButton();
    }

    public void positiveAuthorization(String email, String pass) {
        loginPage.inputLogin(email);
        loginPage.inputPassword(pass);
        loginPage.clickLoginButton();
        Assert.assertTrue(atlassianLoginPage.isOpened());
    }

}
