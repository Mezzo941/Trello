package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BoardPageTest extends BaseTest {

    @Test
    public void cardCreationIsSuccessful() {
        String board = "DoNotDelete";
        String list = "Нужно сделать";
        String cardTitle = "Карточка";

        Assert.assertTrue(
                homePage
                        .open()
                        .isOpened()
        );
        homePage.clickToLoginLink();
        Assert.assertTrue(loginPage.isOpened());
        loginSteps.positiveAuthorization(EMAIL, PASS);
        atlassianLoginSteps.validLogin(PASS);
        trelloWorkingSpacePage.clickToTheBoard(board);
        Assert.assertTrue(boardPage.isOpened(board));
        boardSteps.addCard(list, cardTitle);
        Assert.assertTrue(boardPage.isCardExists(list, cardTitle));
    }

    @Test
    public void cardDeletionIsSuccessful() {
        String board = "DoNotDelete";
        String list = "Нужно сделать";
        String cardTitle = "Карточка";

        Assert.assertTrue(
                homePage
                        .open()
                        .isOpened()
        );
        homePage.clickToLoginLink();
        Assert.assertTrue(loginPage.isOpened());
        loginSteps.positiveAuthorization(EMAIL, PASS);
        atlassianLoginSteps.validLogin(PASS);
        trelloWorkingSpacePage.clickToTheBoard(board);
        Assert.assertTrue(boardPage.isOpened(board));
        boardSteps.deleteCard(list, cardTitle);
        Assert.assertFalse(boardPage.isCardExists(list, cardTitle));
    }

}
