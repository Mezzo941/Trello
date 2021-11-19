package tests;

import factory.ScreenshotFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginPageTest extends BaseTest {


    @DataProvider(name = "user")
    public Object[][] getUser() {
        return new Object[][]
                {
                        {"", PASS, "Отсутствует адрес электронной почты"},
                        {"", "", "Отсутствует адрес электронной почты"},
                        {"email@gmail.com", "eeeeeeee", "Указан неверный адрес и/или пароль. Нужна помощь?"},
                        {"email@gmail.com", "e", "Указан неверный адрес и/или пароль. Нужна помощь?"},
                        {"email@gmail.com", "", "Указан неверный адрес и/или пароль. Нужна помощь?"},
                        {" fdf", "", "Аккаунта с таким именем пользователя не существует"},
                        {"вдтывдлтдвлд", "", "Аккаунта с таким именем пользователя не существует"},
                        {"вдтывдлтдвлд вдтывдлтдвлд", "", "Аккаунта с таким именем пользователя не существует"},
                        {"fdfdffdffdfdffdffdfdffdffdfdffdffdfdffdffdfdffdffdfdffdffdfdffdffdfdffdffdfdffdffdfdffdffdfdffdffdfdffdffdfdffdffdfdffdffdfdffdffdfdffdffdfdffdffdfdffdffdfdffdffdfdffdf@gmail.com",
                                "", "Аккаунт с таким адресом электронной почты не существует"},
                        {" fdfdff@kek.com", "", "Аккаунт с таким адресом электронной почты не существует"},
                };
    }

    @Test(dataProvider = "user")
    public void negativeLoginTest(String email, String password, String error) throws IOException {
        homePage.
                open().
                logIn().
                authorization(email, password);
            Assert.assertEquals(loginPage.getError(), error);
        ScreenshotFactory.getScreen(driver);
    }

}
