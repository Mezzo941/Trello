package Builder;

import lombok.Builder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Click;
import utils.Waiter;


@Builder
public class CardOptions {

    private static final By SAVE_COMMENT = By.cssSelector(".js-add-comment");
    private static final By SAVE_DESCRIPTION = By.cssSelector(".mod-submit-edit.js-save-edit");
    private static final By DESCRIPTION_TEXTAREA = By.cssSelector(".card-description");
    private static final By COMMENT_TEXTAREA = By.cssSelector(".js-new-comment-input");
    /*private static final By DESCRIPTION_FAKE_TEXTAREA = By.cssSelector(".description-fake-text-area");*/

    private final WebDriver setDriver;
    private final String setDescription;
    private final String setComment;

    public void addDescription() {
        WebElement desc = Waiter.waitElementToBeClickable(setDriver, DESCRIPTION_TEXTAREA);
        new Click(setDriver).byAction(desc);
        desc.sendKeys(setDescription);
        WebElement saveButton = Waiter.waitElementToBeClickable(setDriver, SAVE_DESCRIPTION);
        saveButton.click();
    }

    public void addComment() {
        WebElement desc = Waiter.waitElementToBeClickable(setDriver, COMMENT_TEXTAREA);
        new Click(setDriver).byAction(desc);
        desc.sendKeys(setComment);
        WebElement saveButton = Waiter.waitElementToBeClickable(setDriver, SAVE_COMMENT);
        saveButton.click();
    }

}
