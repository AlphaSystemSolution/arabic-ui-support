package com.alphasystem.arabic.ui;

import com.alphasystem.arabic.ui.skin.NounOfPlaceAndTimesPickerSkin;
import com.alphasystem.morphologicalanalysis.morphology.model.support.NounOfPlaceAndTime;
import javafx.scene.control.Skin;

/**
 * @author sali
 */
public class NounOfPlaceTimesPicker extends MultiValuedLabelPicker<NounOfPlaceAndTime> {

    public NounOfPlaceTimesPicker() {
        super();
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new NounOfPlaceAndTimesPickerSkin(this);
    }

    @Override
    public String getImageName() {
        return "images.noun-of-place-and-time-icon.png";
    }
}
