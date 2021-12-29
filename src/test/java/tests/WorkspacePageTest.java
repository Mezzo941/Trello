package tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.BoardPage.AccessLvl;

import static pages.BoardPage.AccessLvl.*;


public class WorkspacePageTest extends BaseTest {

    @DataProvider(name = "boardData")
    public Object[][] getData() {
        return new Object[][]
                {
                        {"PrivateBoard", PRIVATE, "Приватная"},
                        {"PublicBoard", PUBLIC, "Публичная"},
                        {"WorkingBoard", WORKING, "Для рабочего пространства"}
                };
    }

    @DataProvider(name = "boardName")
    public Object[][] getBoardName() {
        return new Object[][]
                {
                        {"PrivateBoard"},
                        {"PublicBoard"},
                        {"WorkingBoard"}
                };
    }

    @Test(dataProvider = "boardData", priority = 1)
    @Description("Check for creation of boards")
    public void boardCreationIsSuccessful(String boardName, AccessLvl lvl, String expLvl) {
        Assert.assertTrue(
                homePage
                        .open()
                        .isOpened()
        );
        homePage.clickToLoginLink();
        Assert.assertTrue(loginPage.isOpened());
        loginSteps.positiveAuthorization(email, PASS);
        atlassianLoginSteps.loginViaEmail(PASS);
        workingSpaceSteps.createBoard(boardName, lvl);
        Assert.assertEquals(boardPage.getAccessLvl(), expLvl);
        workspacePage.open();
        Assert.assertTrue(workspacePage.isOpened());
        Assert.assertTrue(workspacePage.isBoardExists(boardName));
    }

    @Test(dataProvider = "boardName", priority = 2)
    @Description("Check for deletion of boards")
    public void boardDeletionIsSuccessful(String boardName) {
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
    }

    @Test
    @Description("Check how many boards can be created")
    public void AbilityToCreateSevenBoardsAfterThreeAlreadyCreatedBoardsIsSuccessful() {
        Assert.assertTrue(
                homePage
                        .open()
                        .isOpened()
        );
        homePage.clickToLoginLink();
        Assert.assertTrue(loginPage.isOpened());
        loginSteps.positiveAuthorization(email, PASS);
        atlassianLoginSteps.loginViaEmail(PASS);
        Assert.assertEquals(workspacePage.getTheNumberOfBoardsCreated(), 3);
        Assert.assertEquals(workspacePage.getBoardsNumberCanBeCreated(), "Осталось: 7");
    }

    @Test
    @Description("Creating nine boards and check how many boards can be created")
    public void AbilityToCreateOneMoreBoardAfterNineAlreadyCreatedBoardsIsSuccessful() {
        String boardName = "Simple";
        int boardsNumber = 6;
        Assert.assertTrue(
                homePage
                        .open()
                        .isOpened()
        );
        homePage.clickToLoginLink();
        Assert.assertTrue(loginPage.isOpened());
        loginSteps.positiveAuthorization(email, PASS);
        atlassianLoginSteps.loginViaEmail(PASS);
        for (int i = 0; i < boardsNumber; i++) {
            workingSpaceSteps.createBoard(boardName + i, WORKING);
            workspacePage.open();
            Assert.assertTrue(workspacePage.isOpened());
        }
        Assert.assertEquals(workspacePage.getTheNumberOfBoardsCreated(), 9);
        Assert.assertEquals(workspacePage.getBoardsNumberCanBeCreated(), "Осталось: 1");
        for (int i = 0; i < boardsNumber; i++) {
            workingSpaceSteps.deleteBoard(boardName + i);
        }
    }

    @Test
    @Description("Creating nine boards and check how many boards can be created")
    public void tabooAfterTenAlreadyCreatedBoardsIsSuccessful() {
        String boardName = "Simple";
        int boardsNumber = 7;
        Assert.assertTrue(
                homePage
                        .open()
                        .isOpened()
        );
        homePage.clickToLoginLink();
        Assert.assertTrue(loginPage.isOpened());
        loginSteps.positiveAuthorization(email, PASS);
        atlassianLoginSteps.loginViaEmail(PASS);
        for (int i = 0; i < boardsNumber; i++) {
            workingSpaceSteps.createBoard(boardName + i, WORKING);
            workspacePage.open();
            Assert.assertTrue(workspacePage.isOpened());
        }
        Assert.assertEquals
                (
                        workspacePage.getTabooOnCreating(),
                        "Больше доступных досок нет\n" +
                                "Получите неограниченное количество досок"
                );
        for (int i = 0; i < boardsNumber; i++) {
            workingSpaceSteps.deleteBoard(boardName + i);
        }
    }

    /*@Test(enabled = false)
    @Description("Check for deletion of boards")
    public void boardDeletionIsSuccessful1() {
        String boardName = "Simple";
        Assert.assertTrue(
                homePage
                        .open()
                        .isOpened()
        );
        homePage.clickToLoginLink();
        Assert.assertTrue(loginPage.isOpened());
        loginSteps.positiveAuthorization(email, PASS);
        atlassianLoginSteps.loginViaEmail(PASS);
        for (int i = 0; i < 7; i++) {
            workingSpaceSteps.deleteBoard(boardName + i);
        }
    }*/

}
