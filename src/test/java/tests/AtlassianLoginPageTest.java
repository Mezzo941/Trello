package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AtlassianLoginPageTest extends BaseTest {

    @DataProvider(name = "userData")
    private Object[][] getData() {
        return new Object[][]{
                {"12345678", "Неверный адрес электронной почты и/или пароль.\n" +
                        "Требуется помощь, чтобы войти?"},
                {"12", "Неверный адрес электронной почты и/или пароль.\n" +
                        "Требуется помощь, чтобы войти?"},
                {"nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn" +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "Неверный адрес электронной почты и/или пароль.\n" +
                        "Требуется помощь, чтобы войти?"},
                {"1 2", "Неверный адрес электронной почты и/или пароль.\n" +
                        "Требуется помощь, чтобы войти?"},
                {"", "Введите пароль"}
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
