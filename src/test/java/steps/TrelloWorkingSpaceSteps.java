package steps;

import org.openqa.selenium.WebDriver;

import pages.BoardPage.accessLvl;

import static pages.BoardPage.accessLvl.*;


public class TrelloWorkingSpaceSteps extends BaseSteps {

    public TrelloWorkingSpaceSteps(WebDriver driver) {
        super(driver);
    }

    public void createBoard(String boardName, accessLvl lvl) {
        trelloWorkingSpacePage.clickTheButtonCreateBoard();
        trelloWorkingSpacePage.insertBoardsName(boardName);
        if (!lvl.equals(WORKING)) {
            trelloWorkingSpacePage.clickToChooseAccess();
            trelloWorkingSpacePage.selectAccessLvl(lvl);
            if (lvl.equals(PUBLIC)) {
                trelloWorkingSpacePage.submitPublic();
            }
        }
        trelloWorkingSpacePage.submitCreationBoard();
    }

}
