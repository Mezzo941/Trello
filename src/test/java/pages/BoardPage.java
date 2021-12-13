package pages;

import io.qameta.allure.Step;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import utils.Waiter;

import java.util.List;


@Log4j2
public class BoardPage extends BasePage {

    private static final By ACCESS_LVL = By.id("permission-level");
    private static final By MENU = By.cssSelector(".js-show-sidebar");
    private static final By MENU_OPEN_MORE = By.cssSelector(".js-open-more");
    private static final By CLOSE_BOARD = By.cssSelector(".js-close-board");
    private static final By ARCHIVE_LIST = By.cssSelector(".js-close-list");
    private static final By CONFIRM_CLOSE_BOARD = By.cssSelector("[value='Закрыть']");
    private static final By DELETE_BOARD = By.cssSelector("[data-test-id='close-board-delete-board-button']");
    private static final By CONFIRM_DELETE_BOARD = By.cssSelector("[data-test-id='close-board-delete-board-confirm-button']");
    private static final By OPEN_BOARD_AGAIN = By.cssSelector("[data-test-id='workspace-chooser-trigger-button']");
    private static final By BACK_TO_MENU_ARROW = By.cssSelector(".js-pop-widget-view");
    private static final By CARD_TITLE = By.cssSelector(".list-card-composer-textarea.js-card-title");
    private static final By SUBMIT_ADD_CARD = By.cssSelector(".js-add-card.confirm");
    private static final By CANCEL_CARD_ADDITION = By.cssSelector(".js-cancel");
    private static final By ADD_NEW_LIST = By.cssSelector(".placeholder");
    private static final By ADD_LIST_TITLE = By.cssSelector(".list-name-input");
    private static final By SUBMIT_ADD_LIST = By.cssSelector(".mod-list-add-button");
    private static final String ADD_CARD_BUTTON = "//textarea[contains(text(),'%s')]/ancestor::div[contains(@class,'list js-list-content')]//span[@class='js-add-a-card']";
    private static final String LIST = "//textarea[contains(text(),'%s')]/ancestor::div[@class='list js-list-content']";
    private static final String CARD = "//textarea[contains(text(),'%s')]/following::span[contains(text(),'%s')]";
    private static final String MEATBALLS_MENU = "//textarea[contains(text(),'%s')]/ancestor::div[@class='list js-list-content']//a[contains(@class,'icon-overflow-menu-horizontal')]";

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
        log.info("Check access level of the board");
        WebElement element = Waiter.waitVisibilityOfElement(driver, ACCESS_LVL);
        log.info("Access lvl: " + element.getText());
        return element.getText();
    }

    @Step("Click menu")
    public void clickMenuButton() {
        log.info("Click Menu");
        WebElement element = Waiter.waitElementToBeClickable(driver, MENU);
        element.click();
    }

    @Step("Click 'open more'")
    public void clickOpenMore() {
        log.info("Click 'open more'");
        WebElement element = Waiter.waitElementToBeClickable(driver, MENU_OPEN_MORE);
        element.click();
    }

    @Step("Click 'close board'")
    public void closeBoard() {
        log.info("Click 'close board'");
        WebElement element = Waiter.waitElementToBeClickable(driver, CLOSE_BOARD);
        element.click();
    }

    @Step("Click 'confirm close board'")
    public void confirmClosingBoard() {
        log.info("Click 'confirm close board'");
        WebElement element = Waiter.waitElementToBeClickable(driver, CONFIRM_CLOSE_BOARD);
        element.click();
    }

    @Step("Click 'delete board'")
    public void deleteBoard() {
        log.info("Click 'delete board'");
        Waiter.waitVisibilityOfElement(driver, OPEN_BOARD_AGAIN);
        WebElement element = Waiter.waitElementToBeClickable(driver, DELETE_BOARD);
        element.click();
    }

    @Step("Confirm delete board")
    public void confirmDeleteBoard() {
        log.info("Confirm delete board");
        WebElement element = Waiter.waitElementToBeClickable(driver, CONFIRM_DELETE_BOARD);
        element.click();
    }

    @Step("Click 'back to menu'")
    public void backToMenu() {
        log.info("Click 'back to menu'");
        WebElement element = Waiter.waitElementToBeClickable(driver, BACK_TO_MENU_ARROW);
        element.click();
    }

    @Step("Click 'Add card'")
    public void clickAddCard(String listName) {
        log.info("Click 'Add card'");
        By cardLocator = By.xpath(String.format(ADD_CARD_BUTTON, listName));
        WebElement element = Waiter.waitElementToBeClickable(driver, cardLocator);
        element.click();
    }

    @Step("Add card title")
    public void addCardTitle(String title) {
        log.info("Add card title");
        WebElement element = Waiter.waitElementToBeClickable(driver, CARD_TITLE);
        log.info("Card title: " + title);
        element.sendKeys(title);
    }

    @Step("Submit add card")
    public void submitAddCard() {
        log.info("Submit add card");
        WebElement element = Waiter.waitElementToBeClickable(driver, SUBMIT_ADD_CARD);
        element.click();
    }

    public void cancelAdditionCard() {
        WebElement element = Waiter.waitElementToBeClickable(driver, CANCEL_CARD_ADDITION);
        element.click();
    }

    @Step("Click on the card")
    public void clickOnTheCard(String listName, String title) {
        log.info("Click on the card");
        By cardLocator = By.xpath(String.format(CARD, listName, title));
        Waiter.waitUntilElementBeRefreshedAndClickable(driver, cardLocator);
        WebElement element = driver.findElement(cardLocator);
        log.info("Card: " + title);
        element.click();
    }


    @Step("Click 'Add new list'")
    public void clickToAddNewList() {
        log.info("Click 'Add new list'");
        WebElement element = Waiter.waitElementToBeClickable(driver, ADD_NEW_LIST);
        element.click();
    }

    @Step("Input list title")
    public void inputListTitle(String title) {
        log.info("Input list title");
        WebElement element = Waiter.waitElementToBeClickable(driver, ADD_LIST_TITLE);
        log.info("List title: " + title);
        element.sendKeys(title);
    }

    @Step("Submit list addition")
    public void submitAddList() {
        log.info("Submit list addition");
        WebElement element = Waiter.waitElementToBeClickable(driver, SUBMIT_ADD_LIST);
        element.click();
    }

    @Step("Click to list menu")
    public void clickListMenu(String title) {
        By menuLocator = By.xpath(String.format(MEATBALLS_MENU, title));
        log.info("Click to menu of the list named: " + title);
        WebElement element = Waiter.waitUntilElementBeRefreshedAndClickable(driver, menuLocator);
        element.click();
    }

    @Step("Click button 'Archive List'")
    public void clickArchiveList() {
        log.info("Click button 'Archive List'");
        WebElement element = Waiter.waitElementToBeClickable(driver, ARCHIVE_LIST);
        element.click();
    }

    @Step("Check if card exists")
    public boolean isCardExists(String listName, String title) {
        By cardLocator = By.xpath(String.format(CARD, listName, title));
        Waiter.waitUntilElementBeRefreshedAndClickable(driver,cardLocator);
        List<WebElement> elements = driver.findElements(cardLocator);
        log.info("Number of cards named: " + title + " found in list named: " + listName + " is: " + elements.size());
        return elements.size() > 0;
    }

    @Step("Check if list exists")
    public boolean isListExists(String title) {
        By listLocator = By.xpath(String.format(LIST, title));
        Waiter.waitUntilElementBeRefreshedAndClickable(driver,listLocator);
        List<WebElement> elements = driver.findElements(listLocator);
        log.info("Number of lists named: " + title + " is: " + elements.size());
        return elements.size() > 0;
    }

    @ToString
    public enum accessLvl {

        PUBLIC,
        PRIVATE,
        WORKING

    }

}
