package com.alphasystem.arabic.ui.skin;

import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.ui.ArabicLabelView;
import com.alphasystem.arabic.ui.RootLettersPicker;
import com.alphasystem.arabic.ui.RootLettersPickerKeyBoard;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.SkinBase;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Popup;

import static com.alphasystem.arabic.model.ArabicLetterType.SPACE;
import static com.alphasystem.arabic.model.ArabicWord.concatenate;
import static com.alphasystem.arabic.model.ArabicWord.getWord;
import static com.alphasystem.util.AppUtil.getResourceAsStream;

/**
 * @author sali
 */
public class RootLettersPickerSkin extends SkinBase<RootLettersPicker> {

    private static final ArabicWord WORD_SPACE = getWord(SPACE, SPACE, SPACE, SPACE);

    public RootLettersPickerSkin(RootLettersPicker control) {
        super(control);
        initializeSkin();
    }

    private void initializeSkin() {
        FlowPane flowPane = new FlowPane();
        flowPane.setAlignment(getSkinnable().getAlignment());
        flowPane.alignmentProperty().bind(getSkinnable().alignmentProperty());

        RootLettersPickerKeyBoard keyBoard = new RootLettersPickerKeyBoard();

        Popup keyboardPopup = new Popup();
        keyboardPopup.setAutoHide(true);
        keyboardPopup.setHideOnEscape(true);
        keyboardPopup.getContent().add(keyBoard);
        keyboardPopup.setOnHiding(event -> {
            ArabicLetterType[] rootLetters = keyBoard.getRootLetters();
            final RootLettersPicker skinnable = getSkinnable();
            skinnable.setRootLetters(rootLetters[0], rootLetters[1], rootLetters[2], rootLetters[3]);
        });

        ArabicLabelView label = new ArabicLabelView();
        label.setDisable(true);
        label.setLabelWidth(196);
        label.setLabelHeight(32);
        label.setFont(Font.font("Arabic Typesetting", FontWeight.EXTRA_BOLD, 30));

        updateView(keyBoard, label);

        Button pickerButton = new Button();
        pickerButton.setGraphic(new ImageView(new Image(getResourceAsStream("images.root-letters-icon.png"))));
        pickerButton.setOnAction(event -> showPopup(keyboardPopup, pickerButton));

        flowPane.getChildren().addAll(label, pickerButton);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(flowPane);

        getChildren().add(borderPane);
    }

    private void updateView(RootLettersPickerKeyBoard keyBoard, ArabicLabelView label) {
        final RootLettersPicker skinnable = getSkinnable();
        updateLabel(keyBoard, label, skinnable.getFirstRadical(), skinnable.getSecondRadical(),
                skinnable.getThirdRadical(), skinnable.getFourthRadical());
        skinnable.firstRadicalProperty().addListener((o, ov, nv) -> {
            updateLabel(keyBoard, label, nv, skinnable.getSecondRadical(), skinnable.getThirdRadical(),
                    skinnable.getFourthRadical());
        });
        skinnable.secondRadicalProperty().addListener((o, ov, nv) -> {
            updateLabel(keyBoard, label, skinnable.getFirstRadical(), nv, skinnable.getThirdRadical(),
                    skinnable.getFourthRadical());
        });
        skinnable.thirdRadicalProperty().addListener((o, ov, nv) -> {
            updateLabel(keyBoard, label, skinnable.getFirstRadical(), skinnable.getSecondRadical(), nv,
                    skinnable.getFourthRadical());
        });
        skinnable.fourthRadicalProperty().addListener((o, ov, nv) -> {
            updateLabel(keyBoard, label, skinnable.getFirstRadical(), skinnable.getSecondRadical(),
                    skinnable.getThirdRadical(), nv);
        });
    }

    private void updateLabel(RootLettersPickerKeyBoard keyBoard, ArabicLabelView label, ArabicLetterType firstRadical,
                             ArabicLetterType secondRadical, ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        keyBoard.setRootLetters(firstRadical, secondRadical, thirdRadical, fourthRadical);

        ArabicWord word = new ArabicWord();
        word = (firstRadical == null) ? word : getWord(firstRadical);
        word = (secondRadical == null) ? word : concatenate(word, WORD_SPACE, getWord(secondRadical));
        word = (thirdRadical == null) ? word : concatenate(word, WORD_SPACE, getWord(thirdRadical));
        word = (fourthRadical == null) ? word : concatenate(word, WORD_SPACE, getWord(fourthRadical));
        label.setLabel(word);
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
