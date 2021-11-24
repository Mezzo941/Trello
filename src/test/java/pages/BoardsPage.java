package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class BoardsPage extends BasePage {

    private static final By TITLE_PATH = By.tagName("h3");
    private static final String TITLE = "ВАШИ РАБОЧИЕ ПРОСТРАНСТВА";

    public BoardsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpened() {
        boolean status = super.isOpened(TITLE, TITLE_PATH);
        if (status) {
            log.info("Boards page is opened");
            return true;
        } else {
            return false;
        }
    }
}
