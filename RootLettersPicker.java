package com.alphasystem.arabic.ui;

import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.ui.skin.RootLettersPickerSkin;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

/**
 * @author sali
 */
public class RootLettersPicker extends Control {

    private final ObjectProperty<ArabicLetterType> firstRadical = new SimpleObjectProperty<>();
    private final ObjectProperty<ArabicLetterType> secondRadical = new SimpleObjectProperty<>();
    private final ObjectProperty<ArabicLetterType> thirdRadical = new SimpleObjectProperty<>();
    private final ObjectProperty<ArabicLetterType> fourthRadical = new SimpleObjectProperty<>();

    public RootLettersPicker(){
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new RootLettersPickerSkin(this);
    }

    public final ArabicLetterType getFirstRadical() {
        return firstRadical.get();
    }

    public final ObjectProperty<ArabicLetterType> firstRadicalProperty() {
        return firstRadical;
    }

    public final void setFirstRadical(ArabicLetterType firstRadical) {
        this.firstRadical.set(firstRadical);
    }

    public final ArabicLetterType getSecondRadical() {
        return secondRadical.get();
    }

    public final ObjectProperty<ArabicLetterType> secondRadicalProperty() {
        return secondRadical;
    }

    public final void setSecondRadical(ArabicLetterType secondRadical) {
        this.secondRadical.set(secondRadical);
    }

    public final ArabicLetterType getThirdRadical() {
        return thirdRadical.get();
    }

    public final ObjectProperty<ArabicLetterType> thirdRadicalProperty() {
        return thirdRadical;
    }

    public final void setThirdRadical(ArabicLetterType thirdRadical) {
        this.thirdRadical.set(thirdRadical);
    }

    public final ArabicLetterType getFourthRadical() {
        return fourthRadical.get();
    }

    public final ObjectProperty<ArabicLetterType> fourthRadicalProperty() {
        return fourthRadical;
    }

    public final void setFourthRadical(ArabicLetterType fourthRadical) {
        this.fourthRadical.set(fourthRadical);
    }
}
