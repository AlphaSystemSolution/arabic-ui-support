package com.alphasystem.arabic.ui;

import com.alphasystem.arabic.ui.skin.VerbalNounsPickerSkin;
import com.alphasystem.arabic.ui.util.FontAdapter;
import com.alphasystem.morphologicalanalysis.morphology.model.support.VerbalNoun;
import javafx.scene.control.Skin;

/**
 * @author sali
 */
public class VerbalNounsPicker extends MultiValuedLabelPicker<VerbalNoun> {

    public VerbalNounsPicker(FontAdapter fontAdapter) {
        super(fontAdapter);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new VerbalNounsPickerSkin(this);
    }

    @Override
    public String getImageName() {
        return "images.verbal-noun-icon.png";
    }

}
