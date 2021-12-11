package tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.BoardPage.accessLvl;

import static pages.BoardPage.accessLvl.*;


public class WorkingSpacePageTest extends BaseTest {

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

    @Test(dataProvider = "forCreating", priority = 1)
    @Description("Check for creation of boards with different types of access")
    public void boardCreationIsSuccessful(String email, String pass, String boardName, accessLvl lvl, String expLvl) {
        Assert.assertTrue(
                homePage
                        .open()
                        .isOpened()
        );
        homePage.clickToLoginLink();
        Assert.assertTrue(loginPage.isOpened());
        loginSteps.positiveAuthorization(email, pass);
        atlassianLoginSteps.validLogin(pass);
        workingSpaceSteps.createBoard(boardName, lvl);
        Assert.assertEquals(boardPage.getAccessLvl(), expLvl);
        workingSpacePage.open();
        Assert.assertTrue(workingSpacePage.isBoardExists(boardName));
    }

    @Test(dataProvider = "forDeletion", priority = 2)
    @Description("Check for deletion of boards")
    public void boardDeletionIsSuccessful(String email, String pass, String boardName) {
        Assert.assertTrue(
                homePage
                        .open()
                        .isOpened()
        );
        homePage.clickToLoginLink();
        Assert.assertTrue(loginPage.isOpened());
        loginSteps.positiveAuthorization(email, pass);
        atlassianLoginSteps.validLogin(pass);
        workingSpaceSteps.deleteBoard(boardName);
        Assert.assertFalse(workingSpacePage.isBoardExists(boardName));
    }

    @Test(enabled = false)
    public void boardCreationIsSuccessful1() {
        Assert.assertTrue(
                homePage
                        .open()
                        .isOpened()
        );
        homePage.clickToLoginLink();
        Assert.assertTrue(loginPage.isOpened());
        loginSteps.positiveAuthorization(EMAIL, PASS);
        atlassianLoginSteps.validLogin(PASS);
        workingSpaceSteps.createBoard("board", PUBLIC);
        Assert.assertEquals(boardPage.getAccessLvl(), "Публичная");
        workingSpacePage.open();
        workingSpaceSteps.deleteBoard("board");
        Assert.assertFalse(workingSpacePage.isBoardExists("board"));
    }

}
