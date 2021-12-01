package steps;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

import pages.BoardPage.accessLvl;

import static pages.BoardPage.accessLvl.*;

@Log4j2
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

    public void deleteBoard(String boardName) {
        trelloWorkingSpacePage.clickToTheBoard(boardName);
        boardPage.isOpened(boardName);
        boardPage.clickMenuButton();
        /*String lvl = boardPage.getAccessLvl();
        if (lvl.equals("Публичная")) {
            log.info("------------------: " + lvl);
            boardPage.backToMenu();
        } else {
            boardPage.clickMenuButton();
        }*/
        boardPage.clickOpenMore();
        boardPage.closeBoard();
        boardPage.confirmClosingBoard();
        boardPage.deleteBoard();
        boardPage.confirmDeleteBoard();
    }

}
