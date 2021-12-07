package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.testng.Assert;
import utils.Waiter;

@Log4j2
public class HomePage extends BasePage {

    private static final By LOG_IN_BUTTON = By.xpath("//div[@class='float-right buttons']/a[contains(@href,'login')]");
    private static final By HEADER = By.cssSelector("[data-track-group='Global Header']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpened() {
        boolean status = super.isOpened(HEADER);
        if (status) {
            log.info("Home page is opened");
            return true;
        } else {
            return false;
        }
    }

    @Step("Open home trello page")
    public HomePage open() {
        try {
            log.info("Open " + BASE_URL);
            driver.get(BASE_URL);
        } catch (WebDriverException e) {
            Assert.fail("Can't open page by this URL " + BASE_URL);
        }
        return this;
    }

    @Step("Click to the login link")
    public void clickToLoginLink() {
        log.info("Click to the login link");
        WebElement element = Waiter.waitVisibilityOfElement(driver, LOG_IN_BUTTON);
        element.click();
    }

}
