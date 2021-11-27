package tests;

import steps.TrelloAreaSteps;
import utils.ScreenshotUtils;
import factory.WebDriverFactory;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.*;
import steps.AtlassianLoginSteps;
import steps.LoginSteps;
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
    protected AtlassianLoginPage atlassianLoginPage;
    protected TrelloAreaPage trelloAreaPage;

    protected LoginSteps loginSteps;
    protected AtlassianLoginSteps atlassianLoginSteps;
    protected TrelloAreaSteps trelloAreaSteps;

    @BeforeSuite
    public void deleteScreenDir() throws IOException {
        ScreenshotUtils.deleteScreenDir();
    }

    @BeforeMethod
    public void setUp(ITestContext context) {
        try {
            driver = WebDriverFactory.gerDriver("chrome", "start-maximized");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            context.setAttribute("driver", driver);
        } catch (NullPointerException e) {
            log.fatal("driver is " + driver);
            e.printStackTrace();
            Assert.fail("driver assert was failed. Details: " + driver);
        }
        homePage = new HomePage(driver);
        sendLetterPage = new SendLetterPage(driver);
        loginPage = new LoginPage(driver);
        loginSteps = new LoginSteps(driver);
        atlassianLoginPage = new AtlassianLoginPage(driver);
        atlassianLoginSteps = new AtlassianLoginSteps(driver);
        trelloAreaPage = new TrelloAreaPage(driver);
        trelloAreaSteps = new TrelloAreaSteps(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void exit() {
        if (driver != null) {
            driver.quit();
        }
    }

}
