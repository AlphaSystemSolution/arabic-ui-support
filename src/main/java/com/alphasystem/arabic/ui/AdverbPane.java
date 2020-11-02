package com.alphasystem.arabic.ui;


import com.alphasystem.morphologicalanalysis.morphology.model.support.NounOfPlaceAndTime;

import static com.alphasystem.morphologicalanalysis.morphology.model.support.NounOfPlaceAndTime.values;

/**
 * @author sali
 */
public class AdverbPane extends ArabicSupportGroupPane<NounOfPlaceAndTime> {

    protected static final int NUM_OF_COLUMNS = 6;

    public AdverbPane() {
        this(values());
    }

    protected AdverbPane(NounOfPlaceAndTime[] srcValues) {
        this(NUM_OF_COLUMNS, srcValues);
    }

    protected AdverbPane(int numOfColumns, NounOfPlaceAndTime[] srcValues) {
        super(numOfColumns, srcValues);
    }

}
