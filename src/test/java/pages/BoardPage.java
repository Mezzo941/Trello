package pages;

import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.Waiter;


@Log4j2
public class BoardPage extends BasePage {

    private static final By ACCESS_LVL = By.id("permission-level");
    private static final By MENU = By.cssSelector(".js-show-sidebar");
    private static final By MENU_OPEN_MORE = By.cssSelector(".js-open-more");
    private static final By CLOSE_BOARD = By.cssSelector(".js-close-board");
    private static final By CONFIRM_CLOSE_BOARD = By.cssSelector("[value='Закрыть']");
    private static final By DELETE_BOARD = By.cssSelector("[data-test-id='close-board-delete-board-button']");
    private static final By CONFIRM_DELETE_BOARD = By.cssSelector("[data-test-id='close-board-delete-board-confirm-button']");
    private static final By BACK_TO_MENU_ARROW = By.cssSelector(".js-pop-widget-view");

    public BoardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpened(String boardName) {
        boolean status = super.isOpened(boardName, TITLE_PATH);
        if (status) {
            log.info("Board page is opened");
            return true;
        } else {
            return false;
        }
    }

    public String getAccessLvl() {
        WebElement element = Waiter.waitElement(driver, ACCESS_LVL);
        return element.getText();
    }

    public void clickMenuButton() {
        log.info("Click Menu");
        WebElement element = Waiter.waitElement(driver, MENU);
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            Assert.fail("Can't click menu button");
            e.printStackTrace();
        }
    }

    public void clickOpenMore() {
        log.info("Click 'more' button");
        WebElement element = Waiter.waitElement(driver, MENU_OPEN_MORE);
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            Assert.fail("Can't click 'More' button");
            e.printStackTrace();
        }
    }

    public void closeBoard() {
        log.info("Click 'close board' button");
        WebElement element = Waiter.waitElement(driver, CLOSE_BOARD);
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            Assert.fail("Can't click 'close board' button");
            e.printStackTrace();
        }
    }

    public void confirmClosingBoard() {
        log.info("Confirm closing board");
        WebElement element = Waiter.waitElement(driver, CONFIRM_CLOSE_BOARD);
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            Assert.fail("Can't click 'confirm closing' button");
            e.printStackTrace();
        }
    }

    public void deleteBoard() {
        log.info("Click to 'delete board' button");
        WebElement element = Waiter.waitElement(driver, DELETE_BOARD);
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            Assert.fail("Can't click 'confirm closing' button");
            e.printStackTrace();
        }
    }

    public void confirmDeleteBoard() {
        log.info("Confirm delete board");
        WebElement element = Waiter.waitElement(driver, CONFIRM_DELETE_BOARD);
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            Assert.fail("Can't click 'confirm delete' button");
            e.printStackTrace();
        }
    }

    public void backToMenu(){
        log.info("Click back to menu");
        WebElement element = Waiter.waitElement(driver, BACK_TO_MENU_ARROW);
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            Assert.fail("Can't click 'confirm delete' button");
            e.printStackTrace();
        }
    }

    @ToString
    public enum accessLvl {

        PUBLIC,
        PRIVATE,
        WORKING

    }
}
