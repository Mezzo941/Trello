package steps;

import org.testng.Assert;
import pages.BoardPage;

public class BoardSteps {

    private final BoardPage boardPage;

    public BoardSteps(BoardPage boardPage) {
        this.boardPage = boardPage;
    }

    public void addNewCard(String listName, String title) {
        boardPage.clickAddCard(listName);
        boardPage.addCardTitle(title);
        boardPage.submitAddCard();
        boardPage.cancelAdditionCard();
        Assert.assertTrue(boardPage.isCardExists(listName, title));
    }

    public void deleteCard(String listName, String title) {
        boardPage.clickOnTheCard(listName, title);
        boardPage.clickArchiving();
        boardPage.clickDeleteCard();
        boardPage.confirmDeletionOfTheCard();
        Assert.assertFalse(boardPage.isCardExists(listName, title));
    }

    public void addNewList(String title) {
        boardPage.clickToAddNewList();
        boardPage.inputListTitle(title);
        boardPage.submitAddList();
        Assert.assertTrue(boardPage.isListExists(title));
    }

    public void archiveList(String title) {
        boardPage.clickListMenu(title);
        boardPage.clickArchiveList();
        Assert.assertFalse(boardPage.isListExists(title));
    }
}
