package com.alphasystem.arabic.ui;

import com.alphasystem.arabic.ui.skin.NounOfPlaceAndTimesPickerSkin;
import com.alphasystem.arabic.ui.util.FontAdapter;
import com.alphasystem.morphologicalanalysis.morphology.model.support.NounOfPlaceAndTime;
import javafx.scene.control.Skin;

/**
 * @author sali
 */
public class NounOfPlaceTimesPicker extends MultiValuedLabelPicker<NounOfPlaceAndTime> {

    public NounOfPlaceTimesPicker(FontAdapter fontAdapter) {
        super(fontAdapter);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new NounOfPlaceAndTimesPickerSkin(fontAdapter, this);
    }

    @Override
    public String getImageName() {
        return "images.noun-of-place-and-time-icon.png";
    }
}
