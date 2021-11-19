package pages;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Log4j2
public abstract class BasePage {

    protected static final String BASE_URI = "https://trello.com/";
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isOpened() {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(5)).until(webDriver ->
                    ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        }
        catch (Exception e){
            log.error("Page did not load");
            return false;
        }
    }

}
