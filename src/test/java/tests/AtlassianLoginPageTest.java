package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AtlassianLoginPageTest extends BaseTest {

    @DataProvider(name = "userData")
    private Object[][] getData() {
        return new Object[][]{
                {"12345678", "Incorrect email address and / or password.\n" +
                        "Do you need help logging in?"},
                {"12", "Incorrect email address and / or password.\n" +
                        "Do you need help logging in?"},
                {"nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn" +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "Incorrect email address and / or password.\n" +
                        "Do you need help logging in?"},
                {"1 2", "Incorrect email address and / or password.\n" +
                        "Do you need help logging in?"},
                {"", "Enter your password"}
        };
    }

    @Test(dataProvider = "userData")
    public void atlassianLoginNegativeTest(String pass, String error) {
        Assert.assertTrue
                (
                        homePage
                                .open()
                                .isOpened()
                );
        homePage.logIn();
        Assert.assertTrue(loginPage.isOpened());
        loginSteps.positiveAuthorization(EMAIL);
        Assert.assertTrue(atlassianLoginPage.isOpened());
        atlassianLoginSteps.login(pass);
        Assert.assertEquals(atlassianLoginPage.getError(), error);
    }

}
