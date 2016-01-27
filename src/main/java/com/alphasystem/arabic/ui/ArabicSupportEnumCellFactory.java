package com.alphasystem.arabic.ui;

import com.alphasystem.arabic.model.ArabicSupportEnum;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * @author sali
 */
public class ArabicSupportEnumCellFactory<T extends Enum<T> & ArabicSupportEnum> implements
        Callback<ListView<T>, ListCell<T>> {

    private final ListType type;

    public ArabicSupportEnumCellFactory(ListType type) {
        this.type = type;
    }

    @Override
    public ListCell<T> call(ListView<T> param) {
        return new ArabicSupportEnumListCell<>(type);
    }

}

