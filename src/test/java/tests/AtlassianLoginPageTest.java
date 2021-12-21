package tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class AtlassianLoginPageTest extends BaseTest {

    @DataProvider(name = "data")
    private Object[][] getData() {
        return new Object[][]{
                {"", "Enter your password"},
                {PASS + "q", "Incorrect email address and / or password.\n" +
                        "Do you need help logging in?"},
                {"пароль123", "Incorrect email address and / or password.\n" +
                        "Do you need help logging in?"},
        };
    }

    @Test(dataProvider = "data")
    @Description("Negative login test with invalid data")
    public void atlassianLoginNegativeTest(String pass, String error) {
        Assert.assertTrue
                (
                        homePage
                                .open()
                                .isOpened()
                );
        homePage.clickToLoginLink();
        Assert.assertTrue(loginPage.isOpened());
        loginSteps.positiveAuthorization(email, PASS);
        atlassianLoginSteps.invalidLogin(pass);
        Assert.assertEquals(atlassianLoginPage.getError(), error);
    }

    @Test
    @Description("Positive login test with valid data")
    public void atlassianLoginPositiveTest() {
        Assert.assertTrue
                (
                        homePage
                                .open()
                                .isOpened()
                );
        homePage.clickToLoginLink();
        Assert.assertTrue(loginPage.isOpened());
        loginSteps.positiveAuthorization(email, PASS);
        atlassianLoginSteps.loginViaEmail(PASS);
    }

}
