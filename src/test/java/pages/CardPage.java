package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Waiter;


@Log4j2
public class CardPage extends BasePage {

    private static final By ARCHIVING_CARD = By.cssSelector(".js-archive-card");
    private static final By DELETE_CARD = By.cssSelector(".js-delete-card");
    private static final By CONFIRM_DELETION_OF_THE_CARD = By.cssSelector(".js-confirm");
    private static final String TITLE = "//h2[contains(text(),'%s')]/following::textarea[contains(@class,'mod-card-back-title')]";

    public CardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpened(String title) {
        By titleLocator = By.xpath(String.format(TITLE, title));
        WebElement element = Waiter.waitVisibilityOfElement(driver, titleLocator);
        log.info("CardPage named " + title + " is opened. Title's loading status: " + element.isDisplayed());
        return element.isDisplayed();
    }

    @Step("Click on the button 'Archiving'")
    public void clickArchiving() {
        WebElement element = Waiter.waitVisibilityOfElement(driver, ARCHIVING_CARD);
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

}
