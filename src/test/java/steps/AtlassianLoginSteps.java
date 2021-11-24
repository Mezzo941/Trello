package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AtlassianLoginSteps extends BaseSteps{

    public AtlassianLoginSteps(WebDriver driver) {
        super(driver);
    }

    public void atlassianLogin(String pass){
        atlassianLoginPage.inputPassword(pass);
        atlassianLoginPage.clickLoginButton();
    }


}
