package tests;

import com.sun.org.glassfish.gmbal.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.BoardPage.accessLvl;

import static pages.BoardPage.accessLvl.*;


public class TrelloWorkingSpacePageTest extends BaseTest {

    @DataProvider(name = "forCreating")
    public Object[][] getData1() {
        return new Object[][]
                {
                        {EMAIL, PASS, "PrivateBoard", PRIVATE, "Приватная"},
                        {EMAIL, PASS, "PublicBoard", PUBLIC, "Публичная"},
                        {EMAIL, PASS, "DefaultBoard", WORKING, "Для рабочего пространства"},
                };
    }

    @DataProvider(name = "forDeletion")
    public Object[][] getData2() {
        return new Object[][]
                {
                        {EMAIL, PASS, "PrivateBoard"},
                        {EMAIL, PASS, "PublicBoard"},
                        {EMAIL, PASS, "DefaultBoard"}
                };
    }

    @Description("Creating boards with different access types")
    @Test(dataProvider = "forCreating", priority = 1)
    public void boardCreationIsSuccessful(String email, String pass, String boardName, accessLvl lvl, String expLvl) {
        Assert.assertTrue(
                homePage
                        .open()
                        .isOpened()
        );
        homePage.logIn();
        Assert.assertTrue(loginPage.isOpened());
        loginSteps.positiveAuthorization(email);
        Assert.assertTrue(atlassianLoginPage.isOpened());
        atlassianLoginSteps.login(pass);
        Assert.assertTrue(trelloWorkingSpacePage.isOpened());
        trelloWorkingSpaceSteps.createBoard(boardName, lvl);
        Assert.assertTrue(boardPage.isOpened(boardName));
        Assert.assertEquals(boardPage.getAccessLvl(), expLvl);
        trelloWorkingSpacePage.open();
        Assert.assertTrue(trelloWorkingSpacePage.isBoardCreated(boardName));
    }

    @Test(dataProvider = "forDeletion", priority = 2)
    public void boardDeletionIsSuccessful(String email, String pass, String boardName) {
        Assert.assertTrue(
                homePage
                        .open()
                        .isOpened()
        );
        homePage.logIn();
        Assert.assertTrue(loginPage.isOpened());
        loginSteps.positiveAuthorization(email);
        Assert.assertTrue(atlassianLoginPage.isOpened());
        atlassianLoginSteps.login(pass);
        Assert.assertTrue(trelloWorkingSpacePage.isOpened());
        trelloWorkingSpaceSteps.deleteBoard(boardName);
        Assert.assertTrue(trelloWorkingSpacePage.isOpened());
        Assert.assertFalse(trelloWorkingSpacePage.isBoardCreated(boardName));
    }

}
