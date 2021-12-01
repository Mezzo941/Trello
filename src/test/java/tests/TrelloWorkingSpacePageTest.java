package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import static pages.BoardPage.accessLvl.*;


public class TrelloWorkingSpacePageTest extends BaseTest {

    @Test
    public void creationDefaultBoardIsSuccessfully() {
        String name = "DefaultBoard";
        Assert.assertTrue(
                homePage
                        .open()
                        .isOpened()
        );
        homePage.logIn();
        Assert.assertTrue(loginPage.isOpened());
        loginSteps.positiveAuthorization(EMAIL);
        Assert.assertTrue(atlassianLoginPage.isOpened());
        atlassianLoginSteps.login(PASS);
        Assert.assertTrue(trelloWorkingSpacePage.isOpened());
        trelloWorkingSpaceSteps.createBoard(name, WORKING);
        Assert.assertTrue(boardPage.isOpened(name));
        Assert.assertEquals(boardPage.getAccessLvl(), "Для рабочего пространства");
        trelloWorkingSpacePage.open();
        Assert.assertTrue(trelloWorkingSpacePage.isBoardCreated(name));
    }

    @Test
    public void creationPrivateBoardIsSuccessfully() {
        String name = "PrivateBoard";
        Assert.assertTrue(
                homePage
                        .open()
                        .isOpened()
        );
        homePage.logIn();
        Assert.assertTrue(loginPage.isOpened());
        loginSteps.positiveAuthorization(EMAIL);
        Assert.assertTrue(atlassianLoginPage.isOpened());
        atlassianLoginSteps.login(PASS);
        Assert.assertTrue(trelloWorkingSpacePage.isOpened());
        trelloWorkingSpaceSteps.createBoard(name, PRIVATE);
        Assert.assertTrue(boardPage.isOpened(name));
        Assert.assertEquals(boardPage.getAccessLvl(), "Приватная");
        trelloWorkingSpacePage.open();
        Assert.assertTrue(trelloWorkingSpacePage.isBoardCreated(name));
    }

    @Test
    public void creationPublicBoardIsSuccessfully() {
        String name = "PublicBoard";
        Assert.assertTrue(
                homePage
                        .open()
                        .isOpened()
        );
        homePage.logIn();
        Assert.assertTrue(loginPage.isOpened());
        loginSteps.positiveAuthorization(EMAIL);
        Assert.assertTrue(atlassianLoginPage.isOpened());
        atlassianLoginSteps.login(PASS);
        Assert.assertTrue(trelloWorkingSpacePage.isOpened());
        trelloWorkingSpaceSteps.createBoard(name, PUBLIC);
        Assert.assertTrue(boardPage.isOpened(name));
        Assert.assertEquals(boardPage.getAccessLvl(), "Публичная");
        trelloWorkingSpacePage.open();
        Assert.assertTrue(trelloWorkingSpacePage.isBoardCreated(name));
    }

}
