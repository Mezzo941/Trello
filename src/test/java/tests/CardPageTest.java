package tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CardPageTest extends BaseTest{

    @Test
    @Description("Verification of addition a description and comment to the card ")
    public void additionOfDescriptionAndCommentToTheCardIsSuccessful(){
        String board = "DoNotDelete";
        String list = "Нужно сделать";
        String card = "тесткарта";
        Assert.assertTrue(
                homePage
                        .open()
                        .isOpened()
        );
        homePage.clickToLoginLink();
        Assert.assertTrue(loginPage.isOpened());
        loginSteps.positiveAuthorization(EMAIL, PASS);
        atlassianLoginSteps.validLogin(PASS);
        workingSpacePage.clickToTheBoard(board);
        Assert.assertTrue(boardPage.isOpened(board));
        boardSteps.addNewCard(list,card);
        boardPage.clickOnTheCard(list,card);
        Assert.assertTrue(cardPage.isOpened(card));
        cardPage.inputDescription("Что-то произошло");
        cardPage.inputComment("Мой первый коммент");
        Assert.assertEquals(cardPage.getDescription(),"Что-то произошло");
        Assert.assertEquals(cardPage.getComment("первый"),"Мой первый коммент");
    }

}
