package com.alphasystem.arabic.ui.skin;

import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.ui.ArabicLabelView;
import com.alphasystem.arabic.ui.RootLettersPicker;
import com.alphasystem.arabic.ui.RootLettersPickerKeyBoard;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.control.SkinBase;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Popup;

import static com.alphasystem.arabic.model.ArabicLetters.WORD_TATWEEL;
import static com.alphasystem.arabic.model.ArabicWord.concatenate;
import static com.alphasystem.arabic.model.ArabicWord.getWord;
import static com.alphasystem.util.AppUtil.getResourceAsStream;

/**
 * @author sali
 */
public class RootLettersPickerSkin extends SkinBase<RootLettersPicker> {

    public RootLettersPickerSkin(RootLettersPicker control) {
        super(control);
        initializeSkin();
    }

    private void initializeSkin() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(1);
        gridPane.setAlignment(getSkinnable().getAlignment());
        gridPane.alignmentProperty().bind(getSkinnable().alignmentProperty());

        RootLettersPickerKeyBoard keyBoard = new RootLettersPickerKeyBoard();

        Popup keyboardPopup = new Popup();
        keyboardPopup.setAutoHide(true);
        keyboardPopup.setHideOnEscape(true);
        keyboardPopup.getContent().add(keyBoard);
        keyboardPopup.setOnHiding(event -> {
            ArabicLetterType[] rootLettersArray = keyBoard.getRootLetters();
            final RootLettersPicker skinnable = getSkinnable();
            RootLetters rootLetters = skinnable.getRootLetters();
            if (rootLetters == null) {
                rootLetters = new RootLetters();
            }
            rootLetters.setFirstRadical(rootLettersArray[0]);
            rootLetters.setSecondRadical(rootLettersArray[1]);
            rootLetters.setThirdRadical(rootLettersArray[2]);
            rootLetters.setFourthRadical(rootLettersArray[3]);
            skinnable.setRootLetters(null);
            skinnable.setRootLetters(rootLetters);
        });

        ArabicLabelView label = new ArabicLabelView();
        label.setDisable(true);
        label.setWidth(160);
        label.setHeight(32);
        label.setFont(Font.font("Arabic Typesetting", 26));

        updateView(keyBoard, label);

        Button pickerButton = new Button();
        pickerButton.setGraphic(new ImageView(new Image(getResourceAsStream("images.root-letters-icon.png"))));
        pickerButton.setOnAction(event -> showPopup(keyboardPopup, pickerButton));

        gridPane.add(label, 0, 0);
        gridPane.add(pickerButton, 3, 0);

        getChildren().add(gridPane);
    }

    private void updateView(RootLettersPickerKeyBoard keyBoard, ArabicLabelView label) {
        final RootLettersPicker skinnable = getSkinnable();
        updateLabel(keyBoard, label, skinnable.getRootLetters());
        skinnable.rootLettersProperty().addListener((o, ov, nv) -> {
            updateLabel(keyBoard, label, nv);
        });
    }

    private void updateLabel(RootLettersPickerKeyBoard keyBoard, ArabicLabelView label, RootLetters rootLetters) {
        if (rootLetters != null) {
            ArabicLetterType firstRadical = rootLetters.getFirstRadical();
            ArabicLetterType secondRadical = rootLetters.getSecondRadical();
            ArabicLetterType thirdRadical = rootLetters.getThirdRadical();
            ArabicLetterType fourthRadical = rootLetters.getFourthRadical();
            keyBoard.setRootLetters(firstRadical, secondRadical, thirdRadical, fourthRadical);

            ArabicWord word = new ArabicWord();
            word = (firstRadical == null) ? word : getWord(firstRadical);
            word = (secondRadical == null) ? word : concatenate(word, WORD_TATWEEL, getWord(secondRadical));
            word = (thirdRadical == null) ? word : concatenate(word, WORD_TATWEEL, getWord(thirdRadical));
            word = (fourthRadical == null) ? word : concatenate(word, WORD_TATWEEL, getWord(fourthRadical));
            label.setLabel(word);
        }


    }

    private void showPopup(Popup popup, Button button) {
        if (popup.isShowing()) {
            popup.hide();
        } else {
            final Bounds bounds = button.localToScreen(button.getBoundsInLocal());
            popup.show(button, bounds.getMinX(), bounds.getMinY() + bounds.getHeight());
        }
    }

}