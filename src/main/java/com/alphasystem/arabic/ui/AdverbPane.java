package com.alphasystem.arabic.ui;


import com.alphasystem.arabic.ui.util.FontAdapter;
import com.alphasystem.morphologicalanalysis.morphology.model.support.NounOfPlaceAndTime;

import static com.alphasystem.morphologicalanalysis.morphology.model.support.NounOfPlaceAndTime.values;

/**
 * @author sali
 */
public class AdverbPane extends ArabicSupportGroupPane<NounOfPlaceAndTime> {

    protected static final int NUM_OF_COLUMNS = 6;

    public AdverbPane(FontAdapter fontAdapter) {
        this(fontAdapter, values());
    }

    protected AdverbPane(FontAdapter fontAdapter, NounOfPlaceAndTime[] srcValues) {
        this(NUM_OF_COLUMNS, fontAdapter, srcValues);
    }

    protected AdverbPane(int numOfColumns, FontAdapter fontAdapter, NounOfPlaceAndTime[] srcValues) {
        super(numOfColumns, fontAdapter, srcValues);
    }

}
