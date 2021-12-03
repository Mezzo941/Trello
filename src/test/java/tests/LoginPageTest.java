package tests;

import com.sun.org.glassfish.gmbal.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {


    @DataProvider(name = "userData")
    public Object[][] getData() {
        return new Object[][]
                {
                        {"email@gmail.com", "eeeeeeee", "Указан неверный адрес и/или пароль. Нужна помощь?"},
                        {"", "", "Отсутствует адрес электронной почты"},
                        {"fdf", "", "Аккаунта с таким именем пользователя не существует"},
                        {"gmail @gmail.com", "", "Аккаунт с таким адресом электронной почты не существует"},
                        {"Александр", "", "Аккаунта с таким именем пользователя не существует"},
                        {"fdfdffdffdfdffdffdfdffdffdfdffdffdfdffdffdfdffdffdfdffdffdfdffdffdfdffd" +
                                "ffdfdffdffdfdffdffdfdffdffdfdffdffdfdffdffdfdffdffdfdffdffdfdffd" +
                                "ffdfdffdffdfdffdffdfdffdffdfdffdf@gmail.com"," ",
                                "Аккаунт с таким адресом электронной почты не существует"},
                        {" fdfdff@kek.com", "", "Аккаунт с таким адресом электронной почты не существует"},
                };
    }

    @Description("Negative login test with using invalid data")
    @Test(dataProvider = "userData", priority = 1)
    public void negativeLoginTest(String email, String password, String error) {
        Assert.assertTrue(
                homePage
                        .open()
                        .isOpened()
        );
        homePage.logIn();
        Assert.assertTrue(loginPage.isOpened());
        loginSteps.negativeAuthorization(email, password);
        Assert.assertEquals(loginPage.getError(), error);
    }

    @Description("Positive login test with using valid data")
    @Test(priority = 2)
    public void positiveLoginTest() {
        Assert.assertTrue(
                homePage
                        .open()
                        .isOpened()
        );
        homePage.logIn();
        Assert.assertTrue(loginPage.isOpened());
        loginSteps.positiveAuthorization(EMAIL);
        Assert.assertTrue(atlassianLoginPage.isOpened());
        atlassianLoginSteps.login(PASS);
        Assert.assertTrue(trelloWorkingSpacePage.isOpened());
    }


}
