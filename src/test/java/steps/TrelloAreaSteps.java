package steps;

import org.openqa.selenium.WebDriver;
import pages.TrelloAreaPage;

public class TrelloAreaSteps extends BaseSteps {

    public TrelloAreaSteps(WebDriver driver) {
        super(driver);
    }

    public void createBoard(String boardsName, TrelloAreaPage.Lvl accessLvl) {
        trelloAreaPage.clickTheButtonCreateBoard();
        trelloAreaPage.insertBoardsName(boardsName);
        trelloAreaPage.clickToChooseAccess();
        trelloAreaPage.selectAccessLvl(accessLvl);
        trelloAreaPage.submitCreationBoard();
    }

}
