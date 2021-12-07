package steps;

import lombok.extern.log4j.Log4j2;

import org.testng.Assert;
import pages.BoardPage;
import pages.BoardPage.accessLvl;
import pages.TrelloWorkingSpacePage;

import static pages.BoardPage.accessLvl.*;

@Log4j2
public class TrelloWorkingSpaceSteps {

    private final TrelloWorkingSpacePage trelloWorkingSpacePage;
    private final BoardPage boardPage;

    public TrelloWorkingSpaceSteps(TrelloWorkingSpacePage trelloWorkingSpacePage, BoardPage boardPage) {
        this.trelloWorkingSpacePage = trelloWorkingSpacePage;
        this.boardPage = boardPage;
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
        Assert.assertTrue(boardPage.isOpened(boardName));
    }

    public void deleteBoard(String boardName) {
        trelloWorkingSpacePage.clickToTheBoard(boardName);
        boardPage.isOpened(boardName);
        if (!boardName.equals("PublicBoard")) {
            boardPage.clickMenuButton();
        } else {
            boardPage.backToMenu();
        }
        boardPage.clickOpenMore();
        boardPage.closeBoard();
        boardPage.confirmClosingBoard();
        boardPage.deleteBoard();
        boardPage.confirmDeleteBoard();
        Assert.assertTrue(trelloWorkingSpacePage.isOpened());
    }

}
