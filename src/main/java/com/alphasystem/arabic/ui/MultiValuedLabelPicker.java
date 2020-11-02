package com.alphasystem.arabic.ui;

import com.alphasystem.arabic.model.ArabicSupport;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Control;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * @author sali
 */
public abstract class MultiValuedLabelPicker<T extends ArabicSupport> extends Control {

    protected final ObservableList<T> values = observableArrayList();
    protected final ObjectProperty<Pos> alignment = new SimpleObjectProperty<>(Pos.CENTER_LEFT);

    public final ObservableList<T> getValues() {
        return values;
    }

    public final Pos getAlignment() {
        return alignment.get();
    }

    public final void setAlignment(Pos alignment) {
        this.alignment.set(alignment);
    }

    public final ObjectProperty<Pos> alignmentProperty() {
        return alignment;
    }

    public abstract String getImageName();
}
