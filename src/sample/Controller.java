package sample;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import sample.data.Flashcard;
import sample.data.FlashcardsManager;

public class Controller {
    public JFXTextField frontSideTextField;
    public JFXTextField backSideTextField;
    public JFXTextField frontSideLanguageTextField;
    public JFXTextField backSideLanguageTextField;
    public AnchorPane addFlashcardAnchorPane;

    public JFXTextField frontSideEditTextField;
    public JFXTextField backSideEditTextField;
    public JFXTextField frontSideLanguageEditTextField;
    public JFXTextField backSideLanguageEditTextField;
    public AnchorPane editFlashcardAnchorPane;

    int flashcardNumb = 0;

    public Rectangle rectangleOne;
    public Rectangle rectangleTwo;
    public JFXCheckBox showHideCheckBox;
    public AnchorPane anchorFront;
    public Label frontSideLabel;
    public Label frontSideLangLabel;
    public AnchorPane anchorBack;
    public Label backSideLangLabel;
    public Label backSideLabel;

    public FlashcardsManager manager = new FlashcardsManager();

    public void changeRectangles(ActionEvent actionEvent) {
        boolean isSelected = showHideCheckBox.isSelected();

        anchorFront.setVisible(!isSelected);
    }

    public void initialize() {
        if (manager.getFlashcards().size()>1)
            changeFC();
    }

    public void nextCard(ActionEvent actionEvent) {
        int sizeFC = manager.getFlashcards().size();
        sizeFC--;

        if (flashcardNumb < sizeFC) {
            this.flashcardNumb++;
            changeFC();
        }

        addFlashcardAnchorPane.setVisible(false);
    }

    public void prevCard(ActionEvent actionEvent) {
        if (flashcardNumb > 0) {
            this.flashcardNumb--;
            changeFC();
        }
    }

    public void changeFC() {
        Flashcard fc = manager.getFlashcards().get(flashcardNumb);
        frontSideLabel.setText(fc.getFrontSide());
        frontSideLangLabel.setText(fc.getFrontSideLang());
        backSideLabel.setText(fc.getBackSide());
        backSideLangLabel.setText(fc.getBackSideLang());
    }

    public void openAddFlashcard(ActionEvent actionEvent) {
        addFlashcardAnchorPane.setVisible(true);
    }

    public void addFlashcardCancel(ActionEvent actionEvent) {
        closeAddFlashCardAnchorPane();
    }

    public void addFlashcard(ActionEvent actionEvent) {
        manager.addFlashcard(frontSideTextField.getText(), backSideTextField.getText(), frontSideLanguageTextField.getText(), backSideLanguageTextField.getText());
        closeAddFlashCardAnchorPane();
    }

    public void closeAddFlashCardAnchorPane() {
        addFlashcardAnchorPane.setVisible(false);
        frontSideTextField.setText("");
        backSideTextField.setText("");
        frontSideLanguageTextField.setText("");
        backSideLanguageTextField.setText("");
    }


    public void openEditFlashcard(ActionEvent actionEvent) {
        Flashcard fc = manager.getFlashcards().get(flashcardNumb);
        frontSideEditTextField.setText(fc.getFrontSide());
        backSideEditTextField.setText(fc.getBackSide());
        frontSideLanguageEditTextField.setText(fc.getFrontSideLang());
        backSideLanguageEditTextField.setText(fc.getBackSideLang());

        editFlashcardAnchorPane.setVisible(true);
    }

    public void editFlashcardCancel(ActionEvent actionEvent) {
        editFlashcardAnchorPane.setVisible(false);
    }

    public void editFlashcard(ActionEvent actionEvent) {
        Flashcard fc = manager.getFlashcards().get(flashcardNumb);
        manager.editFlashcard(fc.getId(), frontSideEditTextField.getText(), backSideEditTextField.getText(), frontSideLanguageEditTextField.getText(), backSideLanguageEditTextField.getText());

        editFlashcardAnchorPane.setVisible(false);
    }


    public void deleteFlashcard(ActionEvent actionEvent) {
        Flashcard fc = manager.getFlashcards().get(flashcardNumb);
        manager.deleteFlashcard(fc.getId());
    }
}
