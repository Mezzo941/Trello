package pages;

import com.sun.org.glassfish.gmbal.Description;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.BoardPage.accessLvl;
import utils.ElementsTryCatcher;

import java.time.Duration;

import static pages.BoardPage.accessLvl.*;

@Log4j2
public class TrelloWorkingSpacePage extends BasePage {

    private static final By TITLE_PATH = By.xpath("//h3[contains(text(),'ВАШИ')]");
    private static final By CREATE_BOARD_BUTTON = By.cssSelector(".board-tile.mod-add");
    private static final By CREATE_BUTTON = By.cssSelector("[data-test-id='header-create-menu-button']");
    private static final By ADD_BOARD_TITLE = By.cssSelector("[data-test-id='create-board-title-input']");
    private static final By ACCESS = By.xpath("//input[@data-test-id='create-board-title-input']//following::button//span[@aria-label='DownIcon']");
    private static final By SUBMIT_CREATE_BOARD = By.cssSelector("[data-test-id='create-board-submit-button']");
    private static final By SUBMIT_PUBLIC = By.xpath("//button[contains(text(),'Да, сделать')]");
    private static final String SELECT_ACCESS_LVL = "//div[contains(text(),'%s')]";
    private static final String TITLE = "ВАШИ РАБОЧИЕ ПРОСТРАНСТВА";
    private static final String BOARD_TITLE = "//h3[contains(text(),'ВАШИ')]/following::div[text()='%s']";

    public TrelloWorkingSpacePage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpened() {
        boolean status = super.isOpened(TITLE, TITLE_PATH);
        if (status) {
            log.info("Trello area page is opened");
            return true;
        } else {
            return false;
        }
    }

    public void open() {
        log.info("Go to the working space");
        driver.get("https://trello.com/vsevolod268/boards");
    }

    @Description("Click the button 'Create board'")
    public void clickTheButtonCreateBoard() {
        log.info("Click the button 'Create board'");
        WebElement element = ElementsTryCatcher.protect(driver, CREATE_BOARD_BUTTON);
        element.click();
    }

    @Description("Insert board's name")
    public void insertBoardsName(String name) {
        log.info("Insert board's name: " + name);
        WebElement element = ElementsTryCatcher.protect(driver, ADD_BOARD_TITLE);
        element.sendKeys(name);
    }

    @Description("Choose access lvl")
    public void clickToChooseAccess() {
        log.info("Click the button 'Select access'");
        WebElement element = ElementsTryCatcher.protect(driver, ACCESS);
        element.click();
    }

    @Description("Select access level")
    public void selectAccessLvl(accessLvl lvl) {
        switch (lvl) {
            case PUBLIC: {
                log.info("Access level: " + PUBLIC);
                WebElement element = ElementsTryCatcher.protect(driver, By.xpath(String.format(SELECT_ACCESS_LVL, "Публичная")));
                element.click();
                break;
            }
            case PRIVATE: {
                log.info("Access level: " + PRIVATE);
                WebElement element = ElementsTryCatcher.protect(driver, By.xpath(String.format(SELECT_ACCESS_LVL, "Приватная")));
                element.click();
                break;
            }
            case WORKING: {
                log.info("Access level: " + WORKING);
                WebElement element = ElementsTryCatcher.protect(driver, By.xpath(String.format(SELECT_ACCESS_LVL, "Для рабочего")));
                element.click();
                break;
            }
        }
    }

    @Description("Submit creation board")
    public void submitCreationBoard() {
        WebElement element = ElementsTryCatcher.protect(driver, SUBMIT_CREATE_BOARD);
        try {
            log.info("Submit creation board");
            try {
                new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(element)).click();
            } catch (TimeoutException e2) {
                Assert.fail("The button is still disabled");
                e2.printStackTrace();
            }
        } catch (ElementClickInterceptedException e1) {
            Assert.fail("Can't create the board. Button may have been disabled");
            e1.printStackTrace();
        }
    }

    @Description("Submit public")
    public void submitPublic() {
        WebElement element = ElementsTryCatcher.protect(driver, SUBMIT_PUBLIC);
        try {
            log.info("Submit public");
            element.click();
        } catch (ElementClickInterceptedException e) {
            Assert.fail("Can't click the button. It may have been disabled");
            e.printStackTrace();
        }
    }

    public boolean isBoardCreated(String title) {
        By titleByXpath = By.xpath(String.format(BOARD_TITLE, title));
        WebElement element = ElementsTryCatcher.protect(driver, titleByXpath);
        boolean status = false;
        try {
            status = element.isDisplayed();
        } catch (Exception e) {
            Assert.fail("Board didn't create");
            e.printStackTrace();
        }
        return status;
    }

}
