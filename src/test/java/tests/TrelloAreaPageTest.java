package tests;

import org.testng.annotations.Test;

import static pages.TrelloAreaPage.Lvl.PRIVATE;

public class TrelloAreaPageTest extends BaseTest {

    @Test
    public void creationTrelloBoardIsSuccessfully(){
        homePage.open();
        homePage.logIn();
        loginSteps.positiveAuthorization(EMAIL);
        atlassianLoginPage.isOpened();
        atlassianLoginSteps.login(PASS);
        trelloAreaSteps.createBoard("testBoard", PRIVATE);
    }

}
