package steps;

import org.testng.Assert;
import pages.BoardPage;
import pages.CardPage;

public class BoardSteps {

    private final BoardPage boardPage;
    private final CardPage cardPage;

    public BoardSteps(BoardPage boardPage, CardPage cardPage) {
        this.boardPage = boardPage;
        this.cardPage = cardPage;
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
        Assert.assertTrue(cardPage.isOpened(title));
        cardPage.clickArchiving();
        cardPage.clickDeleteCard();
        cardPage.confirmDeletionOfTheCard();
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
