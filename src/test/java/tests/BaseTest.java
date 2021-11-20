package tests;

import factory.ScreenshotFactory;
import factory.WebDriverFactory;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.HomePage;
import pages.LoginPage;
import pages.SendLetterPage;
import utils.PropertyReader;
import utils.TestListener;

import java.io.IOException;
import java.time.Duration;

@Log4j2
@Listeners(TestListener.class)
public class BaseTest {


    public WebDriver driver;
    protected static final String EMAIL = PropertyReader.getProperty("trello.email");
    protected static final String PASS = PropertyReader.getProperty("trello.pass");
    protected HomePage homePage;
    protected SendLetterPage sendLetterPage;
    protected LoginPage loginPage;

    @BeforeClass
    public void deleteScreenDir() throws IOException {
        ScreenshotFactory.deleteScreenDir();
    }

    @BeforeMethod
    public void setUp(ITestContext context) {
        driver = WebDriverFactory.gerDriver("chrome", "headless");
        context.setAttribute("driver", driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        homePage = new HomePage(driver);
        sendLetterPage = new SendLetterPage(driver);
        loginPage = new LoginPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void exit() {
        try {
            driver.quit();
        } catch (NullPointerException exception) {
            log.error("Driver wasn't initialized");
            log.error(driver);
        }
    }

}
