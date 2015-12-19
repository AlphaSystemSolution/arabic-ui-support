package com.alphasystem.arabic.ui;

import com.alphasystem.arabic.ui.skin.RootLettersPickerSkin;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

/**
 * @author sali
 */
public class RootLettersPicker extends Control {

    private final ObjectProperty<RootLetters> rootLetters = new SimpleObjectProperty<>(null, "rootLetters");
    private final ObjectProperty<Pos> alignment = new SimpleObjectProperty<>(Pos.CENTER_LEFT);

    public RootLettersPicker() {
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new RootLettersPickerSkin(this);
    }

    public final RootLetters getRootLetters() {
        return rootLetters.get();
    }

    public final void setRootLetters(RootLetters rootLetters) {
        this.rootLetters.set(rootLetters);
    }

    public final ObjectProperty<RootLetters> rootLettersProperty() {
        return rootLetters;
    }

    public final Pos getAlignment() {
        return alignment.get();
    }

    public final void setAlignment(Pos alignment) {
        this.alignment.set(alignment);
    }

    public final ObjectProperty<Pos> alignmentProperty() {
        return alignment;
    }

}
