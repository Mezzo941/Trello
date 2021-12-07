package steps;

import pages.BoardPage;

public class BoardSteps {

    private final BoardPage boardPage;

    public BoardSteps(BoardPage boardPage) {
        this.boardPage = boardPage;
    }

    public void addCard(String listName, String title) {
        boardPage.clickAddCard(listName);
        boardPage.addCardTitle(title);
        boardPage.submitAddCard();
    }

    public void deleteCard(String listName, String title) {
        boardPage.clickOnTheCard(listName, title);
        boardPage.clickArchiving();
        boardPage.clickDeleteCard();
        boardPage.confirmDeletionOfTheCard();
    }

}
