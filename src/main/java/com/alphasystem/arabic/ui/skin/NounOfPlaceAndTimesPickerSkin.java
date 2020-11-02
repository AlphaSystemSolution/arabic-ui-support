package com.alphasystem.arabic.ui.skin;

import com.alphasystem.arabic.ui.AdverbPane;
import com.alphasystem.arabic.ui.NounOfPlaceTimesPicker;
import com.alphasystem.arabic.ui.util.FontAdapter;
import com.alphasystem.morphologicalanalysis.morphology.model.support.NounOfPlaceAndTime;

/**
 * @author sali
 */
public class NounOfPlaceAndTimesPickerSkin extends MultiValuedLabelPickerSkin<NounOfPlaceAndTime> {

    public NounOfPlaceAndTimesPickerSkin(FontAdapter fontAdapter, NounOfPlaceTimesPicker control) {
        super(control, new AdverbPane(fontAdapter), new NounOfPlaceAndTimesPickerViewer(fontAdapter));
    }

    private static class NounOfPlaceAndTimesPickerViewer extends SelectedValuesView<NounOfPlaceAndTime> {

        public NounOfPlaceAndTimesPickerViewer(FontAdapter fontAdapter) {
            super(fontAdapter);
        }

    }
}
