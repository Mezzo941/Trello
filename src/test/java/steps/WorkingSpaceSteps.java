package steps;

import lombok.extern.log4j.Log4j2;

import org.testng.Assert;
import pages.BoardPage;
import pages.BoardPage.AccessLvl;
import pages.WorkspacePage;

import static pages.BoardPage.AccessLvl.*;

@Log4j2
public class WorkingSpaceSteps {

    private final WorkspacePage workspacePage;
    private final BoardPage boardPage;

    public WorkingSpaceSteps(WorkspacePage workspacePage, BoardPage boardPage) {
        this.workspacePage = workspacePage;
        this.boardPage = boardPage;
    }

    public void createBoard(String boardName, AccessLvl lvl) {
        workspacePage.clickTheButtonCreateBoard();
        workspacePage.insertBoardsName(boardName);
        if (!lvl.equals(WORKING)) {
            workspacePage.clickToChooseAccess();
            workspacePage.selectAccessLvl(lvl);
            if (lvl.equals(PUBLIC)) {
                workspacePage.submitPublic();
            }
        }
        workspacePage.submitCreationBoard();
        Assert.assertTrue(boardPage.isOpened(boardName));
    }

    public void deleteBoard(String boardName) {
        workspacePage.clickToTheBoard(boardName);
        Assert.assertTrue(boardPage.isOpened(boardName));
        String lvl = boardPage.getAccessLvl();
        if (lvl.equals("Публичная")) {
            try {
                boardPage.clickMenuButton();
                boardPage.backToMenu();
            } catch (AssertionError e) {
                boardPage.backToMenu();
            }
        } else {
            boardPage.clickMenuButton();
        }
        boardPage.clickOpenMore();
        boardPage.closeBoard();
        boardPage.confirmClosingBoard();
        boardPage.deleteBoard();
        boardPage.confirmDeleteBoard();
        Assert.assertTrue(workspacePage.isOpened());
        Assert.assertFalse(workspacePage.isBoardExists(boardName));
    }

}
