package tests;

import steps.BoardSteps;
import steps.WorkingSpaceSteps;
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

import java.time.Duration;


@Log4j2
@Listeners(TestListener.class)
public class BaseTest {

    private WebDriver driver;

    protected static final String PASS = PropertyReader.getProperty("trello.pass");
    protected static String EMAIL;
    protected static String boardName;

    protected HomePage homePage;
    protected LoginPage loginPage;
    protected AtlassianLoginPage atlassianLoginPage;
    protected WorkspacePage workspacePage;
    protected BoardPage boardPage;
    protected CardPage cardPage;

    protected LoginSteps loginSteps;
    protected AtlassianLoginSteps atlassianLoginSteps;
    protected WorkingSpaceSteps workingSpaceSteps;
    protected BoardSteps boardSteps;


    private void setAccountData(String id) {
        switch (id) {
            case "1": {
                EMAIL = PropertyReader.getProperty("trello.email1");
                break;
            }
            case "2": {
                EMAIL = PropertyReader.getProperty("trello.email2");
                break;
            }
        }
    }

    @Parameters({"browser", "boardName", "accountId"})
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser,
                      @Optional("DoNotDelete") String boardName,
                      @Optional("1") String id,
                      ITestContext context) {
        try {
            driver = WebDriverFactory.gerDriver(browser, "--headless", "--lang=en");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        } catch (NullPointerException e) {
            Assert.fail("driver assert was failed. Details: " + driver);
            e.printStackTrace();
        }
        setAccountData(id);
        BaseTest.boardName = boardName;
        context.setAttribute("driver", driver);
        //init pages
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        atlassianLoginPage = new AtlassianLoginPage(driver);
        workspacePage = new WorkspacePage(driver);
        boardPage = new BoardPage(driver);
        cardPage = new CardPage(driver);
        //init steps
        loginSteps = new LoginSteps(loginPage, atlassianLoginPage);
        atlassianLoginSteps = new AtlassianLoginSteps(atlassianLoginPage, workspacePage);
        workingSpaceSteps = new WorkingSpaceSteps(workspacePage, boardPage);
        boardSteps = new BoardSteps(boardPage, cardPage);
    }

    @AfterMethod(alwaysRun = true)
    public void exit() {
        if (driver != null) {
            driver.quit();
        }
    }

}
