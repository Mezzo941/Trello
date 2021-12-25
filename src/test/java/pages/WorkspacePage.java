package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import pages.BoardPage.AccessLvl;
import utils.Waiter;

import java.util.List;

import static pages.BoardPage.AccessLvl.*;


@Log4j2
public class WorkspacePage extends BasePage {

    private static final String HEADER = "ВАШИ РАБОЧИЕ ПРОСТРАНСТВА";
    private static final String BOARD_TITLE = "//h3[contains(text(),'" + HEADER + "')]/following::div[text()='%s']";
    private static final String REACT_SELECT_OPTION = "react-select-2-option-%d";
    private static final By TITLE_PATH = By.xpath("//h3[contains(text(),'ВАШИ РАБОЧИЕ ПРОСТРАНСТВА')]");
    private static final By CREATE_BOARD_BUTTON = By.cssSelector("[data-test-id='create-board-tile']");
    private static final By ADD_BOARD_TITLE = By.cssSelector("[data-test-id='create-board-title-input']");
    private static final By ACCESS = By.xpath("//div[text()='Рабочее пространство']");
    private static final By BOARD_NUMBER = By.cssSelector(".remaining");
    private static final By RANDOM_BOARD = By.xpath("//h3[contains(text(),'" + HEADER + "')]/following::li");
    private static final By SUBMIT_CREATE_BOARD = By.cssSelector("[data-test-id='create-board-submit-button']");
    private static final By SUBMIT_PUBLIC = By.xpath("//button[contains(text(),'Да, сделать доску публичной')]");

    public WorkspacePage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpened() {
        boolean status = super.isOpened(HEADER, TITLE_PATH);
        if (status) {
            log.info("Workspace page is opened");
            return true;
        } else {
            return false;
        }
    }

    @Step("Click to the board")
    public void clickToTheBoard(String boardName) {
        log.info("Click to the board: " + boardName);
        By boardLocator = By.xpath(String.format(BOARD_TITLE, boardName));
        WebElement element = Waiter.waitElementToBeClickable(driver, boardLocator);
        element.click();
    }

    @Step("Open workspace page")
    public void open() {
        log.info("Go to the working space");
        driver.get("https://trello.com/vsevolod268/boards");
    }

    @Step("Click the button 'Create board'")
    public void clickTheButtonCreateBoard() {
        log.info("Click the button 'Create board'");
        WebElement element = Waiter.waitElementToBeClickable(driver, CREATE_BOARD_BUTTON);
        element.click();
    }

    @Step("Insert board's name")
    public void insertBoardsName(String name) {
        log.info("Insert board's name: " + name);
        WebElement element = Waiter.waitVisibilityOfElement(driver, ADD_BOARD_TITLE);
        element.sendKeys(name);
    }

    @Step("Click button to choose access lvl")
    public void clickToChooseAccess() {
        log.info("Click the button 'Select access'");
        WebElement element = Waiter.waitElementToBeClickable(driver, ACCESS);
        element.click();
    }

    @Step("Select access level")
    public void selectAccessLvl(AccessLvl lvl) {
        switch (lvl) {
            case PUBLIC: {
                log.info("Access level: " + PUBLIC);
                WebElement element = Waiter.waitElementToBeClickable(driver, By.id(String.format(REACT_SELECT_OPTION, 2)));
                element.click();
                break;
            }
            case PRIVATE: {
                log.info("Access level: " + PRIVATE);
                WebElement element = Waiter.waitElementToBeClickable(driver, By.id(String.format(REACT_SELECT_OPTION, 0)));
                element.click();
                break;
            }
            case WORKING: {
                log.info("Access level: " + WORKING);
                WebElement element = Waiter.waitElementToBeClickable(driver, By.id(String.format(REACT_SELECT_OPTION, 1)));
                element.click();
                break;
            }
        }
    }

    @Step("Submit creation board")
    public void submitCreationBoard() {
        log.info("Submit creation board");
        WebElement element = Waiter.waitElementToBeClickable(driver, SUBMIT_CREATE_BOARD);
        element.click();
    }

    @Step("Submit public")
    public void submitPublic() {
        log.info("Submit public");
        WebElement element = Waiter.waitElementToBeClickable(driver, SUBMIT_PUBLIC);
        element.click();
    }

    @Step("Check for board exists")
    public boolean isBoardExists(String title) {
        By titleByXpath = By.xpath(String.format(BOARD_TITLE, title));
        List<WebElement> elements = driver.findElements(titleByXpath);
        log.info("Number of boards named " + title + " have found: " + elements.size());
        return elements.size() > 0;
    }

    @Step("Get the number of boards created")
    public int getTheNumberOfBoardsCreated() {
        List<WebElement> boards = driver.findElements(RANDOM_BOARD);
        log.info("The number of boards created is " + (boards.size() - 1));
        return boards.size() - 1;
    }

    @Step("Check how many boards can be created")
    public String getBoardsNumberCanBeCreated() {
        WebElement element = Waiter.waitElementToBeClickable(driver, BOARD_NUMBER);
        log.info(element.getText());
        return element.getText();
    }

    @Step("Get taboo on creating a new board")
    public String getTabooOnCreating() {
        log.info("Get taboo on creating a new board:");
        WebElement element = Waiter.waitVisibilityOfElement(driver, CREATE_BOARD_BUTTON);
        log.info(element.getText());
        return element.getText();
    }

}
