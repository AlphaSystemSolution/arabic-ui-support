package com.alphasystem.arabic.ui;


import com.alphasystem.morphologicalanalysis.morphology.model.support.VerbalNoun;

import static com.alphasystem.morphologicalanalysis.morphology.model.support.VerbalNoun.*;

/**
 * @author sali
 */
public class VerbalNounPane extends ArabicSupportGroupPane<VerbalNoun> {

    public VerbalNounPane() {
        this(new VerbalNoun[]{VERBAL_NOUN_V1, VERBAL_NOUN_V2, VERBAL_NOUN_V3, VERBAL_NOUN_V4, VERBAL_NOUN_V5,
                VERBAL_NOUN_V6, VERBAL_NOUN_V7, VERBAL_NOUN_V8, VERBAL_NOUN_V9, VERBAL_NOUN_V10, VERBAL_NOUN_V11,
                VERBAL_NOUN_V12, VERBAL_NOUN_V13, VERBAL_NOUN_V14, VERBAL_NOUN_V15, VERBAL_NOUN_V27,
                VERBAL_NOUN_FORM_II, VERBAL_NOUN_FORM_III_V1, VERBAL_NOUN_FORM_III_V2, VERBAL_NOUN_FORM_IV,
                VERBAL_NOUN_FORM_V, VERBAL_NOUN_FORM_VI, VERBAL_NOUN_FORM_VII, VERBAL_NOUN_FORM_VIII, VERBAL_NOUN_FORM_IX,
                VERBAL_NOUN_FORM_X});
    }

    private VerbalNounPane(int numOfColumns, VerbalNoun[] srcValues) {
        super(numOfColumns, srcValues);
    }

    private VerbalNounPane(VerbalNoun[] srcValues) {
        this(DEFAULT_NUM_OF_COLUMNS, srcValues);
    }
}
