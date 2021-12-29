package tests;

import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BoardPageTest extends BaseTest {

    @Test
    @Description("Check for successful creation and deletion of one card")
    public void cardCreationAndDeletionIsSuccessful() {
        String list = "Нужно сделать";
        String card = new Faker().harryPotter().spell();

        Assert.assertTrue(
                homePage
                        .open()
                        .isOpened()
        );
        homePage.clickToLoginLink();
        Assert.assertTrue(loginPage.isOpened());
        loginSteps.positiveAuthorization(email, PASS);
        atlassianLoginSteps.loginViaEmail(PASS);
        workspacePage.clickToTheBoard(boardName);
        Assert.assertTrue(boardPage.isOpened(boardName));
        boardSteps.addNewCard(list, card);
        boardSteps.deleteCard(list, card);
    }


    @Test
    @Description("Check for successful creation and deletion of one card in three different lists")
    public void creationAndDeletionOfOneCardInThreeDifferentListsIsSuccessful() {
        String list = "Нужно сделать";
        String list2 = "В процессе";
        String list3 = "Готово";
        String card = new Faker().animal().name();
        String card2 = new Faker().artist().name();
        String card3 = new Faker().color().name();

        Assert.assertTrue(
                homePage
                        .open()
                        .isOpened()
        );
        homePage.clickToLoginLink();
        Assert.assertTrue(loginPage.isOpened());
        loginSteps.positiveAuthorization(email, PASS);
        atlassianLoginSteps.loginViaEmail(PASS);
        workspacePage.clickToTheBoard(boardName);
        Assert.assertTrue(boardPage.isOpened(boardName));
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
        String list = new Faker().country().name();
        String card = new Faker().harryPotter().character();

        Assert.assertTrue(
                homePage
                        .open()
                        .isOpened()
        );
        homePage.clickToLoginLink();
        Assert.assertTrue(loginPage.isOpened());
        loginSteps.positiveAuthorization(email, PASS);
        atlassianLoginSteps.loginViaEmail(PASS);
        workspacePage.clickToTheBoard(boardName);
        Assert.assertTrue(boardPage.isOpened(boardName));
        boardSteps.addNewList(list);
        boardSteps.addNewCard(list, card);
        boardSteps.deleteCard(list, card);
        boardSteps.archiveList(list);
    }

    @Test
    @Description("Check for successful creation and deletion of three cards in one list")
    public void creatingAndDeletionThreeCardsInOneListsSuccessfully() {
        String list = "Нужно сделать";
        String card = new Faker().ancient().titan();
        String card2 = new Faker().superhero().name();
        String card3 = new Faker().dog().name();

        Assert.assertTrue(
                homePage
                        .open()
                        .isOpened()
        );
        homePage.clickToLoginLink();
        Assert.assertTrue(loginPage.isOpened());
        loginSteps.positiveAuthorization(email, PASS);
        atlassianLoginSteps.loginViaEmail(PASS);
        workspacePage.clickToTheBoard(boardName);
        Assert.assertTrue(boardPage.isOpened(boardName));
        boardSteps.addNewCard(list, card);
        boardSteps.addNewCard(list, card2);
        boardSteps.addNewCard(list, card3);
        boardSteps.deleteCard(list, card);
        boardSteps.deleteCard(list, card2);
        boardSteps.deleteCard(list, card3);
    }

}
