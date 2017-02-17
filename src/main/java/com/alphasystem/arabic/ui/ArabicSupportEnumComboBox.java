package com.alphasystem.arabic.ui;

import com.alphasystem.arabic.model.ArabicSupportEnum;
import javafx.beans.NamedArg;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import static java.lang.Double.MAX_VALUE;
import static org.apache.commons.lang3.ArrayUtils.isEmpty;

/**
 * @author sali
 */
public class ArabicSupportEnumComboBox<T extends Enum<T> & ArabicSupportEnum> extends ComboBox<T> {

    public ArabicSupportEnumComboBox(@NamedArg("clazz") Class<T> _class, @NamedArg("required") boolean required,
                                     @NamedArg("type") ListType type) {
        super(getItems(required, _class.getEnumConstants()));

        setCellFactory(new ArabicSupportEnumCellFactory<>(type));
        setButtonCell(new ArabicSupportEnumListCell<>(type));
        getSelectionModel().select(0);
        setMaxWidth(MAX_VALUE);
        setMaxHeight(MAX_VALUE);
    }

    @SafeVarargs
    private static <T extends Enum<T> & ArabicSupportEnum> ObservableList<T> getItems(boolean required,
                                                                                      T... values) {
        final ObservableList<T> list = FXCollections.observableArrayList();
        if (!required) {
            list.add(null);
        }
        if (!isEmpty(values)) {
            list.addAll(values);
        }
        return list;
    }
}
