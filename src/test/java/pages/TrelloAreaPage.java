package pages;

import com.sun.org.glassfish.gmbal.Description;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ElementsTryCatcher;

@Log4j2
public class TrelloAreaPage extends BasePage {

    private static final By TITLE_PATH = By.tagName("h3");
    private static final By CREATE_BOARD_BUTTON = By.cssSelector(".board-tile");
    private static final By CREATE_BUTTON = By.cssSelector("[data-test-id='header-create-menu-button']");
    private static final By ADD_BOARD_TITLE = By.cssSelector("[data-test-id='create-board-title-input']");
    private static final By ACCESS = By.xpath("//input[@data-test-id='create-board-title-input']//following::button//span[@aria-label='DownIcon']");
    private static final By SUBMIT_CREATE_BOARD = By.cssSelector("[data-test-id='create-board-submit-button']");
    private static final String SELECT_ACCESS_LVL = "//*[contains(text(),'%s')]";
    private static final String TITLE = "ВАШИ РАБОЧИЕ ПРОСТРАНСТВА";

    public TrelloAreaPage(WebDriver driver) {
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

    public void clickToChooseAccess() {
        log.info("Click the button 'Select access'");
        WebElement element = ElementsTryCatcher.protect(driver, ACCESS);
        element.click();
    }

    @Description("Select access level")
    public void selectAccessLvl(Lvl lvl) {
        switch (lvl) {
            case PUBLIC: {
                log.info("Access level: " + Lvl.PUBLIC);
                WebElement element = ElementsTryCatcher.protect(driver, By.xpath(String.format(SELECT_ACCESS_LVL, "все участники")));
                element.click();
                break;
            }
            case PRIVATE: {
                log.info("Access level: " + Lvl.PRIVATE);
                WebElement element = ElementsTryCatcher.protect(driver, By.xpath(String.format(SELECT_ACCESS_LVL, "только ее участники")));
                element.click();
                break;
            }
            case WORKING: {
                log.info("Access level: " + Lvl.WORKING);
                WebElement element = ElementsTryCatcher.protect(driver, By.xpath(String.format(SELECT_ACCESS_LVL, "все в Интернете")));
                element.click();
                break;
            }
        }
    }

    @Description("Submit creation board")
    public void submitCreationBoard() {
        log.info("Submit creation board");
        WebElement element = ElementsTryCatcher.protect(driver, SUBMIT_CREATE_BOARD);
        element.click();
    }

    @ToString
    public enum Lvl {

        PUBLIC,
        PRIVATE,
        WORKING

    }


}
