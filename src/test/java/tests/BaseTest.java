package tests;

import steps.BoardSteps;
import steps.WorkingSpaceSteps;
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

    private WebDriver driver;

    protected static final String EMAIL = PropertyReader.getProperty("trello.email");
    protected static final String PASS = PropertyReader.getProperty("trello.pass");

    protected HomePage homePage;
    protected LoginPage loginPage;
    protected AtlassianLoginPage atlassianLoginPage;
    protected WorkingSpacePage workingSpacePage;
    protected BoardPage boardPage;
    protected CardPage cardPage;

    protected LoginSteps loginSteps;
    protected AtlassianLoginSteps atlassianLoginSteps;
    protected WorkingSpaceSteps workingSpaceSteps;
    protected BoardSteps boardSteps;

    @BeforeSuite
    public void deleteScreenDir() throws IOException {
        ScreenshotUtils.deleteScreenDir();
    }

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser, ITestContext context) {
        try {
            driver = WebDriverFactory.gerDriver(browser, /*"--headless",*/"--lang=en");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        } catch (NullPointerException e) {
            Assert.fail("driver assert was failed. Details: " + driver);
            e.printStackTrace();
        }
        context.setAttribute("driver", driver);
        //init pages
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        atlassianLoginPage = new AtlassianLoginPage(driver);
        workingSpacePage = new WorkingSpacePage(driver);
        boardPage = new BoardPage(driver);
        cardPage = new CardPage(driver);
        //init steps
        loginSteps = new LoginSteps(loginPage);
        atlassianLoginSteps = new AtlassianLoginSteps(atlassianLoginPage);
        workingSpaceSteps = new WorkingSpaceSteps(workingSpacePage, boardPage);
        boardSteps = new BoardSteps(boardPage, cardPage);
    }

    @AfterMethod(alwaysRun = true)
    public void exit() {
        if (driver != null) {
            driver.quit();
        }
    }

}
