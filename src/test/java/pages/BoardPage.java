package pages;

import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ElementsTryCatcher;


@Log4j2
public class BoardPage extends BasePage {

    private static final By ACCESS_LVL = By.id("permission-level");

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
        WebElement element = ElementsTryCatcher.protect(driver,ACCESS_LVL);
        return element.getText();
    }

    @ToString
    public enum accessLvl {

        PUBLIC,
        PRIVATE,
        WORKING

    }
}
