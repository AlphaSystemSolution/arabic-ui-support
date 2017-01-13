package com.alphasystem.arabic.ui;

import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.ui.skin.RootLettersPickerKeyBoardSkin;
import com.alphasystem.arabic.ui.util.FontUtilities;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.text.Font;

import static com.alphasystem.util.AppUtil.getResource;

/**
 * @author sali
 */
public class RootLettersPickerKeyBoard extends Control {

    private static final int SPACING = 5;

    private final ObjectProperty<ArabicLetterType> firstRadical = new SimpleObjectProperty<>(this, "firstRadical", ArabicLetterType.FA);
    private final ObjectProperty<ArabicLetterType> secondRadical = new SimpleObjectProperty<>(this, "secondRadical", ArabicLetterType.AIN);
    private final ObjectProperty<ArabicLetterType> thirdRadical = new SimpleObjectProperty<>(this, "thirdRadical", ArabicLetterType.LAM);
    private final ObjectProperty<ArabicLetterType> fourthRadical = new SimpleObjectProperty<>(this, "fourthRadical");
    private final DoubleProperty selectedLabelWidth = new SimpleDoubleProperty(this, "selectedLabelWidth", 32);
    private final DoubleProperty selectedLabelHeight = new SimpleDoubleProperty(this, "selectedLabelWidth", 32);
    private final DoubleProperty keyboardButtonWidth = new SimpleDoubleProperty(this, "keyboardButtonWidth", 48);
    private final DoubleProperty keyboardButtonHeight = new SimpleDoubleProperty(this, "keyboardButtonHeight", 36);
    private final ObjectProperty<Font> font = new SimpleObjectProperty<>(this, "font", FontUtilities.ARABIC_FONT_24);
    private final DoubleProperty spacing = new SimpleDoubleProperty(this, "spacing", 5);
    private final ReadOnlyObjectWrapper<RootLetters> rootLetters = new ReadOnlyObjectWrapper<>(this, "rootLetters", new RootLetters());

    public RootLettersPickerKeyBoard() {
        firstRadicalProperty().addListener((observable, oldValue, newValue) -> rootLetters.get().setFirstRadical(newValue));
        secondRadicalProperty().addListener((observable, oldValue, newValue) -> rootLetters.get().setSecondRadical(newValue));
        thirdRadicalProperty().addListener((observable, oldValue, newValue) -> rootLetters.get().setThirdRadical(newValue));
        fourthRadicalProperty().addListener((observable, oldValue, newValue) -> rootLetters.get().setFourthRadical(newValue));
        setPadding(new Insets(SPACING, SPACING, SPACING, SPACING));
        setSpacing(SPACING);
        setStyle("-fx-base: beige;");
        setSkin(createDefaultSkin());
    }

    @Override
    public String getUserAgentStylesheet() {
        return getResource("arabic-ui-support.css").toExternalForm();
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new RootLettersPickerKeyBoardSkin(this);
    }

    public final ObjectProperty<ArabicLetterType> firstRadicalProperty() {
        return firstRadical;
    }

    public final void setFirstRadical(ArabicLetterType firstRadical) {
        this.firstRadical.set(firstRadical);
    }

    public final ObjectProperty<ArabicLetterType> secondRadicalProperty() {
        return secondRadical;
    }

    public final void setSecondRadical(ArabicLetterType secondRadical) {
        this.secondRadical.set(secondRadical);
    }

    public final ObjectProperty<ArabicLetterType> thirdRadicalProperty() {
        return thirdRadical;
    }

    public final void setThirdRadical(ArabicLetterType thirdRadical) {
        this.thirdRadical.set(thirdRadical);
    }

    public final ObjectProperty<ArabicLetterType> fourthRadicalProperty() {
        return fourthRadical;
    }

    public final void setFourthRadical(ArabicLetterType fourthRadical) {
        this.fourthRadical.set(fourthRadical);
    }

    public final double getSelectedLabelWidth() {
        return selectedLabelWidth.get();
    }

    public final DoubleProperty selectedLabelWidthProperty() {
        return selectedLabelWidth;
    }

    public final void setSelectedLabelWidth(double selectedLabelWidth) {
        this.selectedLabelWidth.set(selectedLabelWidth);
    }

    public final double getSelectedLabelHeight() {
        return selectedLabelHeight.get();
    }

    public final DoubleProperty selectedLabelHeightProperty() {
        return selectedLabelHeight;
    }

    public final void setSelectedLabelHeight(double selectedLabelHeight) {
        this.selectedLabelHeight.set(selectedLabelHeight);
    }

    public final double getKeyboardButtonWidth() {
        return keyboardButtonWidth.get();
    }

    public final DoubleProperty keyboardButtonWidthProperty() {
        return keyboardButtonWidth;
    }

    public final void setKeyboardButtonWidth(double keyboardButtonWidth) {
        this.keyboardButtonWidth.set(keyboardButtonWidth);
    }

    public final double getKeyboardButtonHeight() {
        return keyboardButtonHeight.get();
    }

    public final DoubleProperty keyboardButtonHeightProperty() {
        return keyboardButtonHeight;
    }

    public final void setKeyboardButtonHeight(double keyboardButtonHeight) {
        this.keyboardButtonHeight.set(keyboardButtonHeight);
    }

    public final Font getFont() {
        return font.get();
    }

    public final ObjectProperty<Font> fontProperty() {
        return font;
    }

    public final void setFont(Font font) {
        this.font.set(font);
    }

    public final double getSpacing() {
        return spacing.get();
    }

    public final DoubleProperty spacingProperty() {
        return spacing;
    }

    public final void setSpacing(double spacing) {
        this.spacing.set(spacing);
    }

    public final RootLetters getRootLetters() {
        return rootLetters.get();
    }

    public final void setRootLetters(final ArabicLetterType firstRadical, final ArabicLetterType secondRadical,
                                     final ArabicLetterType thirdRadical, final ArabicLetterType fourthRadical) {
        setFirstRadical((firstRadical == null) ? ArabicLetterType.FA : firstRadical);
        setSecondRadical((secondRadical == null) ? ArabicLetterType.AIN : secondRadical);
        setThirdRadical((thirdRadical == null) ? ArabicLetterType.LAM : thirdRadical);
        setFourthRadical(fourthRadical);
    }

    public final void setRootLetters(final RootLetters rootLetters) {
        setFirstRadical((rootLetters == null) ? ArabicLetterType.FA : rootLetters.getFirstRadical());
        setSecondRadical((rootLetters == null) ? ArabicLetterType.AIN : rootLetters.getSecondRadical());
        setThirdRadical((rootLetters == null) ? ArabicLetterType.LAM : rootLetters.getThirdRadical());
        setFourthRadical((rootLetters == null) ? null : rootLetters.getFourthRadical());
    }

}
