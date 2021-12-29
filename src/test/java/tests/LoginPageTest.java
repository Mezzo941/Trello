package tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class LoginPageTest extends BaseTest {

    @DataProvider(name = "userData1")
    public Object[][] getData1() {
        return new Object[][]
                {
                        {"gmail@gmail.com", "", "Указан неверный адрес и/или пароль. Нужна помощь?"},
                        {"", PASS, "Отсутствует адрес электронной почты"},
                        {"asking12345@mail.ru", PASS, "Аккаунт с таким адресом электронной почты не существует"},
                        {"Mezzo9412", PASS, "Аккаунта с таким именем пользователя не существует"},
                        {"!@#$%^&*()_+=-", PASS, "Аккаунт с таким адресом электронной почты не существует"},
                        {"анатолий@почта.рф", PASS, "Аккаунт с таким адресом электронной почты не существует"},
                        {"анатолий1234", PASS, "Аккаунта с таким именем пользователя не существует"},
                        {"gmail@gmail.com", PASS, "Указан неверный адрес и/или пароль. Нужна помощь?"},
                };
    }

    @DataProvider(name = "userData2")
    public Object[][] getData2() {
        return new Object[][]
                {
                        {email, PASS},
                        {USERNAME, PASS}
                };
    }

    @Test(dataProvider = "userData1", priority = 1)
    @Description("Negative login test using invalid data")
    public void negativeLoginTest(String email, String password, String error) {
        Assert.assertTrue(
                homePage
                        .open()
                        .isOpened()
        );
        homePage.clickToLoginLink();
        Assert.assertTrue(loginPage.isOpened());
        loginSteps.negativeAuthorization(email, password);
        Assert.assertEquals(loginPage.getError(), error);
    }

    @Test(dataProvider = "userData2", priority = 2)
    @Description("Positive login test using valid data")
    public void positiveLoginTest(String emailOrUsername, String pass) {
        Assert.assertTrue(
                homePage
                        .open()
                        .isOpened()
        );
        homePage.clickToLoginLink();
        Assert.assertTrue(loginPage.isOpened());
        loginSteps.positiveAuthorization(emailOrUsername, pass);
    }

}
