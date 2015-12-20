package com.alphasystem.arabic.ui.skin;

import com.alphasystem.arabic.ui.AdverbPane;
import com.alphasystem.arabic.ui.NounOfPlaceTimesPicker;
import com.alphasystem.morphologicalanalysis.morphology.model.support.NounOfPlaceAndTime;

/**
 * @author sali
 */
public class NounOfPlaceAndTimesPickerSkin extends MultiValuedLabelPickerSkin<NounOfPlaceAndTime> {

    public NounOfPlaceAndTimesPickerSkin(NounOfPlaceTimesPicker control) {
        super(control, new AdverbPane());
    }
}
