package steps;

import org.openqa.selenium.WebDriver;

public class LoginSteps extends BaseSteps {

    public LoginSteps(WebDriver driver) {
        super(driver);
    }

    public void negativeAuthorization(String login, String pass) {
        loginPage.inputLogin(login);
        loginPage.inputPassword(pass);
        loginPage.clickLoginButtonNegative();
    }

    public void positiveAuthorization(String login) {
        loginPage.inputLogin(login);
        loginPage.clickLoginButtonPositive();
    }

    public void authorizationFromCloud(String email, String pass) {
        loginPage.inputLogin(email);
        loginPage.inputPassword(pass);
        loginPage.clickLoginButtonNegative();
    }

}
