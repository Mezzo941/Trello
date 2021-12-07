package pages;

import io.qameta.allure.Step;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.Waiter;

import java.util.List;


@Log4j2
public class BoardPage extends BasePage {

    private static final By ACCESS_LVL = By.id("permission-level");
    private static final By MENU = By.cssSelector(".js-show-sidebar");
    private static final By MENU_OPEN_MORE = By.cssSelector(".js-open-more");
    private static final By CLOSE_BOARD = By.cssSelector(".js-close-board");
    private static final By ARCHIVING = By.cssSelector(".js-archive-card");
    private static final By CONFIRM_CLOSE_BOARD = By.cssSelector("[value='Закрыть']");
    private static final By CONFIRM_DELETION_OF_THE_CARD = By.cssSelector("[value='Удалить']");
    private static final By DELETE_BOARD = By.cssSelector("[data-test-id='close-board-delete-board-button']");
    private static final By DELETE_CARD = By.cssSelector(".js-delete-card");
    private static final By CONFIRM_DELETE_BOARD = By.cssSelector("[data-test-id='close-board-delete-board-confirm-button']");
    private static final By OPEN_BOARD_AGAIN = By.cssSelector("[data-test-id='workspace-chooser-trigger-button']");
    private static final By BACK_TO_MENU_ARROW = By.cssSelector(".js-pop-widget-view");
    private static final By CARD_TITLE = By.cssSelector(".list-card-composer-textarea.js-card-title");
    private static final By SUBMIT_ADD_CARD = By.cssSelector(".js-add-card.confirm");
    private static final String CARD_WINDOW_TITLE = "//h2[contains(text(),'%s')]/following::textarea[contains(@class,'mod-card-back-title')]";
    private static final String ADD_CARD_BUTTON = "//textarea[contains(text(),'%s')]/ancestor::div[contains(@class,'list js-list-content')]//span[@class='js-add-a-card']";
    private static final String CARD = "//textarea[contains(text(),'%s')]/ancestor::div[@class='list js-list-content']//*[contains(text(),'%s')]";


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

    @Step("Check access level of the board")
    public String getAccessLvl() {
        WebElement element = Waiter.waitVisibilityOfElement(driver, ACCESS_LVL);
        log.info("Access lvl of the board: " + element.getText());
        return element.getText();
    }

    @Step("Click menu")
    public void clickMenuButton() {
        log.info("Click Menu");
        WebElement element = Waiter.waitVisibilityOfElement(driver, MENU);
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            Assert.fail("Can't click menu button");
            e.printStackTrace();
        }
    }

    @Step("Click 'open more'")
    public void clickOpenMore() {
        log.info("Click 'open more'");
        WebElement element = Waiter.waitVisibilityOfElement(driver, MENU_OPEN_MORE);
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            Assert.fail("Can't click 'More' button");
            e.printStackTrace();
        }
    }

    @Step("Click 'close board'")
    public void closeBoard() {
        log.info("Click 'close board'");
        WebElement element = Waiter.waitVisibilityOfElement(driver, CLOSE_BOARD);
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            Assert.fail("Can't click 'close board' button");
            e.printStackTrace();
        }
    }

    @Step("Click 'confirm close board'")
    public void confirmClosingBoard() {
        log.info("Click 'confirm close board'");
        WebElement element = Waiter.waitVisibilityOfElement(driver, CONFIRM_CLOSE_BOARD);
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            Assert.fail("Can't click 'confirm closing' button");
            e.printStackTrace();
        }
    }

    @Step("Click 'delete board'")
    public void deleteBoard() {
        log.info("Click 'delete board'");
        Waiter.waitVisibilityOfElement(driver, OPEN_BOARD_AGAIN);
        WebElement element = Waiter.waitVisibilityOfElement(driver, DELETE_BOARD);
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            Assert.fail("Can't click 'confirm closing' button");
            e.printStackTrace();
        }
    }

    @Step("Confirm delete board")
    public void confirmDeleteBoard() {
        log.info("Confirm delete board");
        WebElement element = Waiter.waitVisibilityOfElement(driver, CONFIRM_DELETE_BOARD);
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            Assert.fail("Can't click 'confirm delete' button");
            e.printStackTrace();
        }
    }

    @Step("Click 'back to menu'")
    public void backToMenu() {
        log.info("Click 'back to menu'");
        WebElement element = Waiter.waitVisibilityOfElement(driver, BACK_TO_MENU_ARROW);
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            Assert.fail("Can't click 'confirm delete' button");
            e.printStackTrace();
        }
    }

    @Step("Click 'Add card'")
    public void clickAddCard(String listName) {
        By cardLocator = By.xpath(String.format(ADD_CARD_BUTTON, listName));
        WebElement element = Waiter.waitVisibilityOfElement(driver, cardLocator);
        log.info("Click 'Add card'");
        element.click();
    }

    @Step("Add card title")
    public void addCardTitle(String title) {
        WebElement element = Waiter.waitVisibilityOfElement(driver, CARD_TITLE);
        log.info("Add card title: " + title);
        element.sendKeys(title);
    }

    @Step("Submit add card")
    public void submitAddCard() {
        WebElement element = Waiter.waitVisibilityOfElement(driver, SUBMIT_ADD_CARD);
        log.info("Submit add card");
        element.click();
    }

    @Step("Click on the card")
    public void clickOnTheCard(String listName, String title) {
        By cardLocator = By.xpath(String.format(CARD, listName, title));
        By cardWindowTitleLocator = By.xpath(String.format(CARD_WINDOW_TITLE, title));
        WebElement element = Waiter.waitVisibilityOfElement(driver, cardLocator);
        log.info("Click on the card: " + title);
        element.click();
        Waiter.waitVisibilityOfElement(driver, cardWindowTitleLocator);
    }

    @Step("Click on the button 'Archiving'")
    public void clickArchiving() {
        WebElement element = Waiter.waitVisibilityOfElement(driver, ARCHIVING);
        log.info("Click on the button 'Archiving'");
        element.click();
    }

    @Step("Click on the button 'Delete card'")
    public void clickDeleteCard() {
        WebElement element = Waiter.waitVisibilityOfElement(driver, DELETE_CARD);
        log.info("Click on the button 'Delete card'");
        element.click();
    }

    @Step("Click on the button 'Confirm deletion'")
    public void confirmDeletionOfTheCard() {
        WebElement element = Waiter.waitVisibilityOfElement(driver, CONFIRM_DELETION_OF_THE_CARD);
        log.info("Click on the button 'Confirm deletion'");
        element.click();
    }

    @Step("Check if card exists")
    public boolean isCardExists(String listName, String title) {
        By cardLocator = By.xpath(String.format(CARD, listName, title));
        List<WebElement> elements = driver.findElements(cardLocator);
        log.info("Number of cards found: " + elements.size());
        return elements.size() > 0;
    }

    @ToString
    public enum accessLvl {

        PUBLIC,
        PRIVATE,
        WORKING

    }
}
