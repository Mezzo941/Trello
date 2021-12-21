package steps;

import lombok.extern.log4j.Log4j2;

import org.testng.Assert;
import pages.BoardPage;
import pages.BoardPage.accessLvl;
import pages.WorkspacePage;

import static pages.BoardPage.accessLvl.*;

@Log4j2
public class WorkingSpaceSteps {

    private final WorkspacePage workspacePage;
    private final BoardPage boardPage;

    public WorkingSpaceSteps(WorkspacePage workspacePage, BoardPage boardPage) {
        this.workspacePage = workspacePage;
        this.boardPage = boardPage;
    }

    public void createBoard(String boardName, accessLvl lvl) {
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
        boardPage.clickMenuButton();
        if (lvl.equals("Публичная")) {
            boardPage.backToMenu();
        }
        boardPage.clickOpenMore();
        boardPage.closeBoard();
        boardPage.confirmClosingBoard();
        boardPage.deleteBoard();
        boardPage.confirmDeleteBoard();
        Assert.assertTrue(workspacePage.isOpened());
    }

    /*public void createBoard2(String boardName) {
        workspacePage.clickTheButtonCreateBoard();
        workspacePage.insertBoardsName(boardName);
        workspacePage.submitCreationBoard();
        Assert.assertTrue(boardPage.isOpened(boardName));
    }*/

}
