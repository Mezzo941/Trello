package pages;

import Builder.CardOptions;
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
    private static final By DESCRIPTION = By.xpath("//div[contains(@class,'js-desc')]/p");
    private static final String COMMENT = "//div[contains(@class,'js-new-comment')]/following::div[@class='comment-container']//p[contains(text(),'%s')]";
    private static final String TITLE = "//h2[contains(text(),'%s')]/ancestor::div[@class='window-title']";

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

    @Step("Input random comment: {text}")
    public void inputComment(String text) {
        CardOptions comment = CardOptions.builder()
                .setDriver(driver)
                .setComment(text)
                .build();
        comment.addComment();
    }

    @Step("Input random description: {text}")
    public void inputDescription(String text) {
        CardOptions cardOptions = CardOptions.builder()
                .setDriver(driver)
                .setDescription(text)
                .build();
        cardOptions.addDescription();
    }

    @Step("Get description from cardPage")
    public String getDescription() {
        WebElement element = Waiter.waitVisibilityOfElement(driver, DESCRIPTION);
        log.info("Get description from cardPage");
        log.info(element.getText());
        return element.getText();
    }

    @Step("Get comment from cardPage")
    public String getComment(String comment) {
        By commentLocator = By.xpath(String.format(COMMENT, comment));
        WebElement element = Waiter.waitVisibilityOfElement(driver, commentLocator);
        log.info("Get comment from cardPage");
        log.info(element.getText());
        return element.getText();
    }

}
