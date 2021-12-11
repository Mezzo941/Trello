package steps;

import lombok.extern.log4j.Log4j2;

import org.testng.Assert;
import pages.BoardPage;
import pages.BoardPage.accessLvl;
import pages.WorkingSpacePage;

import static pages.BoardPage.accessLvl.*;

@Log4j2
public class WorkingSpaceSteps {

    private final WorkingSpacePage workingSpacePage;
    private final BoardPage boardPage;

    public WorkingSpaceSteps(WorkingSpacePage workingSpacePage, BoardPage boardPage) {
        this.workingSpacePage = workingSpacePage;
        this.boardPage = boardPage;
    }

    public void createBoard(String boardName, accessLvl lvl) {
        workingSpacePage.clickTheButtonCreateBoard();
        workingSpacePage.insertBoardsName(boardName);
        if (!lvl.equals(WORKING)) {
            workingSpacePage.clickToChooseAccess();
            workingSpacePage.selectAccessLvl(lvl);
            if (lvl.equals(PUBLIC)) {
                workingSpacePage.submitPublic();
            }
        }
        workingSpacePage.submitCreationBoard();
        Assert.assertTrue(boardPage.isOpened(boardName));
    }

    public void deleteBoard(String boardName) {
        workingSpacePage.clickToTheBoard(boardName);
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
        Assert.assertTrue(workingSpacePage.isOpened());
    }

}
