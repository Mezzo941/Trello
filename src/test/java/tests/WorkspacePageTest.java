package tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;


public class WorkspacePageTest extends BaseTest {

    //TODO REMAKE TESTS
    /*@Test(priority = 1)
    @Description("Check for creation of boards")
    public void boardCreationIsSuccessful() {
        Assert.assertTrue(
                homePage
                        .open()
                        .isOpened()
        );
        homePage.clickToLoginLink();
        Assert.assertTrue(loginPage.isOpened());
        loginSteps.positiveAuthorization(EMAIL, PASS);
        atlassianLoginSteps.loginViaEmail(PASS);
        workingSpaceSteps.createBoard2(boardName);
        Assert.assertEquals(boardPage.getAccessLvl(), "Для рабочего пространства");
        workspacePage.open();
        Assert.assertTrue(workspacePage.isBoardExists(boardName));
    }*/

    @Test(priority = 2)
    @Description("Check for deletion of boards")
    public void boardDeletionIsSuccessful() {
        Assert.assertTrue(
                homePage
                        .open()
                        .isOpened()
        );
        homePage.clickToLoginLink();
        Assert.assertTrue(loginPage.isOpened());
        loginSteps.positiveAuthorization(email, PASS);
        atlassianLoginSteps.loginViaEmail(PASS);
        workingSpaceSteps.deleteBoard(boardName);
        Assert.assertFalse(workspacePage.isBoardExists(boardName));
    }

}
