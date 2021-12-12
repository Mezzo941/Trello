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
    private static final By DESCRIPTION = By.xpath("//div[contains(@class,'js-show-with-desc')]/p");
    private static final String DELETE_COMMENT = "//p[contains(text(),'%s')]/ancestor::div[@class='js-list-actions mod-card-back']//a[@class='js-confirm-delete-action']";
    private static final String COMMENT = "//div[contains(@class,'js-new-comment')]/following::div[@class='comment-container']//p[contains(text(),'%s')]";
    private static final String TITLE = "//h2[contains(text(),'%s')]/ancestor::div[@class='window-title']";

    public CardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpened(String title) {
        By titleLocator = By.xpath(String.format(TITLE, title));
        WebElement element = Waiter.waitVisibilityOfElement(driver, titleLocator);
        log.info("Card page named " + title + " is opened. Title's loading status: " + element.isDisplayed());
        return element.isDisplayed();
    }

    @Step("Click on the button 'Archiving'")
    public void clickArchiving() {
        log.info("Click on the button 'Archiving'");
        WebElement element = Waiter.waitElementToBeClickable(driver, ARCHIVING_CARD);
        element.click();
    }

    @Step("Click on the button 'Delete card'")
    public void clickDeleteCard() {
        log.info("Click on the button 'Delete card'");
        WebElement element = Waiter.waitElementToBeClickable(driver, DELETE_CARD);
        element.click();
    }

    @Step("Click on the button 'Confirm deletion'")
    public void confirmDeletionOfTheCard() {
        log.info("Click on the button 'Confirm deletion'");
        WebElement element = Waiter.waitElementToBeClickable(driver, CONFIRM_DELETION_OF_THE_CARD);
        element.click();
    }

    @Step("Input random comment: {text}")
    public void inputComment(String text) {
        log.info("Input random comment: " + text);
        CardOptions comment = CardOptions.builder()
                .setDriver(driver)
                .setComment(text)
                .build();
        comment.addComment();
    }

    @Step("Input random description: {text}")
    public void inputDescription(String text) {
        log.info("Input random description: " + text);
        CardOptions cardOptions = CardOptions.builder()
                .setDriver(driver)
                .setDescription(text)
                .build();
        cardOptions.addDescription();
    }

    public void deleteComment(String comment) {
        By deleteCommentLocator = By.xpath(String.format(DELETE_COMMENT, comment));
        WebElement element = Waiter.waitElementToBeClickable(driver, deleteCommentLocator);
        element.click();
    }

    @Step("Get description from cardPage")
    public String getDescription() {
        log.info("Get description from cardPage");
        WebElement element = Waiter.waitVisibilityOfElement(driver, DESCRIPTION);
        log.info(element.getText());
        return element.getText();
    }

    @Step("Get comment from cardPage")
    public String getComment(String comment) {
        log.info("Get comment from cardPage");
        By deleteCommentLocator = By.xpath(String.format(DELETE_COMMENT, comment));
        By commentLocator = By.xpath(String.format(COMMENT, comment));
        WebElement element = Waiter.waitUntilElementBeRefreshedAndClickable(driver, commentLocator);
        Waiter.waitVisibilityOfElement(driver, deleteCommentLocator);
        log.info(element.getText());
        return element.getText();
    }

}
