package com.alphasystem.arabic.ui;

import javafx.util.Builder;
import javafx.util.BuilderFactory;

/**
 * @author sali
 */
public class ArabicSupportEnumComboBoxBuilderFactory implements BuilderFactory {

    @Override
    public Builder<?> getBuilder(Class<?> type) {
        return ((type != null) && type.getName().equals(ArabicSupportEnumComboBoxBuilder.class.getName())) ?
                new ArabicSupportEnumComboBoxBuilder() : null;
    }
}
