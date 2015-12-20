package com.alphasystem.arabic.ui.skin;

import com.alphasystem.arabic.ui.VerbalNounPane;
import com.alphasystem.arabic.ui.VerbalNounsPicker;
import com.alphasystem.morphologicalanalysis.morphology.model.support.VerbalNoun;

/**
 * @author sali
 */
public class VerbalNounsPickerSkin extends MultiValuedLabelPickerSkin<VerbalNoun> {

    public VerbalNounsPickerSkin(VerbalNounsPicker control) {
        super(control, new VerbalNounPane());
    }
}
