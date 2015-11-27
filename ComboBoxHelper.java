package com.alphasystem.arabic.ui;

import com.alphasystem.arabic.model.ArabicSupportEnum;
import javafx.scene.control.ComboBox;

import static com.alphasystem.arabic.ui.ListType.LABEL_AND_CODE;
import static java.lang.Double.MAX_VALUE;

/**
 * @author sali
 */
public final class ComboBoxHelper {

    @SafeVarargs
    public static <T extends ArabicSupportEnum> ComboBox<T> createComboBox(boolean addEmptyValue,
                                                                            ListType type, T... values) {
        ComboBox<T> comboBox = new ComboBox<>();
        if (addEmptyValue) {
            comboBox.getItems().add(null);
        }
        comboBox.getItems().addAll(values);
        comboBox.setCellFactory(new ArabicSupportEnumCellFactory<>(type));
        comboBox.setButtonCell(new ArabicSupportEnumListCell<>(type));
        comboBox.getSelectionModel().select(0);
        comboBox.setMaxWidth(MAX_VALUE);
        comboBox.setMaxHeight(MAX_VALUE);
        return comboBox;
    }

    @SafeVarargs
    public static <T extends ArabicSupportEnum> ComboBox<T> createComboBox(boolean addEmptyValue, T... values) {
        return createComboBox(addEmptyValue, LABEL_AND_CODE, values);
    }

    @SafeVarargs
    public static <T extends ArabicSupportEnum> ComboBox<T> createComboBox(ListType type, T... values) {
        return createComboBox(false, type, values);
    }

    @SafeVarargs
    public static <T extends ArabicSupportEnum> ComboBox<T> createComboBox(T... values) {
        return createComboBox(LABEL_AND_CODE, values);
    }
}
