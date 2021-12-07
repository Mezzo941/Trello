package steps;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.AtlassianLoginPage;
import pages.LoginPage;

public class LoginSteps {

    private final LoginPage loginPage;

    public LoginSteps(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    public void negativeAuthorization(String login, String pass) {
        loginPage.inputLogin(login);
        loginPage.inputPassword(pass);
        loginPage.clickLoginButtonNegative();
    }

    public void positiveAuthorization(String email, String pass) {
        WebDriver driver = loginPage.getDriver();
        loginPage.inputLogin(email);
        loginPage.inputPassword(pass);
        loginPage.clickLoginButtonNegative();
        Assert.assertTrue(new AtlassianLoginPage(driver).isOpened());
    }

}
