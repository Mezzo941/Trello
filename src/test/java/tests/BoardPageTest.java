package tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BoardPageTest extends BaseTest {

    @Test
    @Description("Check for successful creation and deletion of one card")
    public void cardCreationAndDeletionIsSuccessful() {
        String board = "DoNotDelete";
        String list = "Нужно сделать";
        String card = "Карточка";

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
        boardSteps.addNewCard(list, card);
        boardSteps.deleteCard(list, card);
    }


    @Test
    @Description("Check for successful creation and deletion of one card in three different lists")
    public void creationAndDeletionOfOneCardInThreeDifferentListsIsSuccessful() {
        String board = "DoNotDelete";
        String list = "Нужно сделать";
        String list2 = "В процессе";
        String list3 = "Готово";
        String card = "workToDo";
        String card2 = "workInProcess";
        String card3 = "workDone";

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
        boardSteps.addNewCard(list, card);
        boardSteps.addNewCard(list2, card2);
        boardSteps.addNewCard(list3, card3);
        boardSteps.deleteCard(list, card);
        boardSteps.deleteCard(list2, card2);
        boardSteps.deleteCard(list3, card3);
    }

    @Test
    @Description("Check for successful creation and deletion of one card in new list and then archive this list")
    public void cardAdditionAndDeletionInNewListThenArchiveListIsSuccessful() {
        String board = "DoNotDelete";
        String list = "Рандом лист";
        String card = "Рандом карта";

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
        boardSteps.addNewList(list);
        boardSteps.addNewCard(list, card);
        boardSteps.deleteCard(list, card);
        boardSteps.archiveList(list);
    }

    @Test
    @Description("Check for successful creation and deletion of three cards in one list")
    public void creatingAndDeletionThreeCardsInOneListsSuccessfully() {
        String board = "DoNotDelete";
        String list = "Нужно сделать";
        String card = "card1";
        String card2 = "card2";
        String card3 = "card3";

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
        boardSteps.addNewCard(list, card);
        boardSteps.addNewCard(list, card2);
        boardSteps.addNewCard(list, card3);
        boardSteps.deleteCard(list, card);
        boardSteps.deleteCard(list, card2);
        boardSteps.deleteCard(list, card3);
    }

}
