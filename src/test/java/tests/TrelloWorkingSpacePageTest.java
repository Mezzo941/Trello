package tests;

import io.qameta.allure.Description;
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

    @Test(dataProvider = "forCreating", priority = 1)
    @Description("Creating boards with different access types")
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
        trelloWorkingSpaceSteps.createBoard(boardName, lvl);
        Assert.assertEquals(boardPage.getAccessLvl(), expLvl);
        trelloWorkingSpacePage.open();
        Assert.assertTrue(trelloWorkingSpacePage.isOpened());
        Assert.assertTrue(trelloWorkingSpacePage.isBoardExists(boardName));
    }

    @Test(dataProvider = "forDeletion", priority = 2)
    @Description("Creating boards with different access types")
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
        trelloWorkingSpaceSteps.deleteBoard(boardName);
        Assert.assertFalse(trelloWorkingSpacePage.isBoardExists(boardName));
    }

}
