package tests;

import factory.WebDriverFactory;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import pages.LoginPage;
import pages.SendLetterPage;
import utils.PropertyReader;

import java.time.Duration;

@Log4j2
public class BaseTest {


    public WebDriver driver;
    protected static final String EMAIL = PropertyReader.getProperty("trello.email");
    protected static final String PASS = PropertyReader.getProperty("trello.pass");
    protected HomePage homePage;
    protected SendLetterPage sendLetterPage;
    protected LoginPage loginPage;

    @BeforeMethod
    public void setUp(){
        driver = WebDriverFactory.gerDriver("chrome","headless");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        homePage = new HomePage(driver);
        sendLetterPage = new SendLetterPage(driver);
        loginPage = new LoginPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void exit(){
        try {
            driver.quit();
        }
        catch (NullPointerException exception){
            log.error("Driver wasn't initialized");
            log.error(driver);
        }
    }

}
