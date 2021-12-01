package tests;

import com.sun.org.glassfish.gmbal.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.BoardPage.accessLvl;

import static pages.BoardPage.accessLvl.*;


public class TrelloWorkingSpacePageTest extends BaseTest {

    @DataProvider(name = "boardData")
    public Object[][] getData() {
        return new Object[][]
                {
                        {EMAIL, PASS, "PrivateBoard2", PRIVATE, "Приватная"},
                        {EMAIL, PASS, "PublicBoard2", PUBLIC, "Публичная"},
                        {EMAIL, PASS, "DefaultBoard2", WORKING, "Для рабочего пространства"},
                };
    }


    @Description("Creating boards with different access types")
    @Test(dataProvider = "boardData")
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

}
