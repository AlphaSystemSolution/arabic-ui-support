package com.alphasystem.arabic.ui;

import com.alphasystem.arabic.model.ArabicSupport;
import com.alphasystem.arabic.ui.skin.ArabicLabelViewSkin;
import javafx.beans.property.*;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import static com.alphasystem.arabic.ui.ArabicLabelToggleGroup.DEFAULT_STROKE;
import static com.alphasystem.arabic.ui.ArabicLabelToggleGroup.DEFAULT_FONT;
import static javafx.scene.paint.Color.*;

/**
 * @author sali
 */
public class ArabicLabelView extends Control {

    private static final int DEFAULT_WIDTH = 64;
    private static final int DEFAULT_HEIGHT = 64;

    private final ReadOnlyBooleanWrapper selected = new ReadOnlyBooleanWrapper(false, "selected");
    private final BooleanProperty select = new SimpleBooleanProperty();
    private final ObjectProperty<Font> font = new SimpleObjectProperty<>(DEFAULT_FONT, "font");
    private final ObjectProperty<Paint> stroke = new SimpleObjectProperty<>(DEFAULT_STROKE, "stroke");
    private final ObjectProperty<Paint> unSelectedStroke = new SimpleObjectProperty<>(BLACK, "unSelectedStroke");
    private final ObjectProperty<Paint> selectedStroke = new SimpleObjectProperty<>(RED, "unSelectedStroke");
    private final ObjectProperty<Paint> disabledStroke = new SimpleObjectProperty<>(LIGHTGRAY, "unSelectedStroke");
    private final ObjectProperty<ArabicSupport> label = new SimpleObjectProperty<>(null, "label");
    private final ObjectProperty<ArabicLabelToggleGroup> group = new SimpleObjectProperty<>(null, "group");

    /**
     * Default Constructor
     */
    public ArabicLabelView() {
        this(null);
    }

    public ArabicLabelView(ArabicSupport label) {
        labelProperty().addListener((o, oV, nV) -> {
            setUserData(nV);
        });
        setLabel(label);
        setWidth(DEFAULT_WIDTH);
        setHeight(DEFAULT_HEIGHT);
        setFont(DEFAULT_FONT);
        setStroke(DEFAULT_STROKE);
        setUnSelectedStroke(BLACK);
        setSelectedStroke(RED);
        setDisabledStroke(LIGHTGRAY);
        select.addListener((observable, oldValue, newValue) -> makeSelection(newValue));
        setSelect(false);
    }

    @Override
    protected Skin<ArabicLabelView> createDefaultSkin() {
        return new ArabicLabelViewSkin(this);
    }

    @Override
    public void setHeight(double value) {
        super.setHeight((value <= 0) ? DEFAULT_HEIGHT : value);
    }

    @Override
    public void setWidth(double value) {
        super.setWidth((value <= 0) ? DEFAULT_WIDTH : value);
    }

    public final boolean isSelected() {
        return selected.get();
    }

    public final ReadOnlyBooleanProperty selectedProperty() {
        return selected.getReadOnlyProperty();
    }

    void setSelected(boolean s) {
        selected.set(s);
    }

    public final void setSelect(boolean select) {
        this.select.set(select);
    }

    public final ArabicLabelToggleGroup getGroup() {
        return group == null ? null : group.get();
    }

    public final void setGroup(ArabicLabelToggleGroup group) {
        this.group.set(group);
        getGroup().getToggles().add(this);
    }

    public final ObjectProperty<ArabicLabelToggleGroup> groupProperty() {
        return group;
    }

    public final Font getFont() {
        return font.get();
    }

    public final void setFont(Font font) {
        this.font.set((font == null) ? DEFAULT_FONT : font);
    }

    public final ObjectProperty<Font> fontProperty() {
        return font;
    }

    public final Paint getStroke() {
        return stroke.get();
    }

    public final ObjectProperty<Paint> strokeProperty() {
        return stroke;
    }

    public final void setStroke(Paint stroke) {
        this.stroke.set((stroke == null) ? DEFAULT_STROKE : stroke);
    }

    public final Paint getUnSelectedStroke() {
        return unSelectedStroke.get();
    }

    public final ObjectProperty<Paint> unSelectedStrokeProperty() {
        return unSelectedStroke;
    }

    public final void setUnSelectedStroke(Paint unSelectedStroke) {
        this.unSelectedStroke.set((unSelectedStroke == null) ? BLACK : unSelectedStroke);
    }

    public final Paint getSelectedStroke() {
        return selectedStroke.get();
    }

    public final ObjectProperty<Paint> selectedStrokeProperty() {
        return selectedStroke;
    }

    public final void setSelectedStroke(Paint selectedStroke) {
        this.selectedStroke.set((selectedStroke == null) ? RED : selectedStroke);
    }

    public final Paint getDisabledStroke() {
        return disabledStroke.get();
    }

    public final ObjectProperty<Paint> disabledStrokeProperty() {
        return disabledStroke;
    }

    public final void setDisabledStroke(Paint disabledStroke) {
        this.disabledStroke.set((disabledStroke == null) ? LIGHTGRAY : disabledStroke);
    }

    public final ArabicSupport getLabel() {
        return label.get();
    }

    public final void setLabel(ArabicSupport label) {
        this.label.set(label);
    }

    public final ObjectProperty<ArabicSupport> labelProperty() {
        return label;
    }

    private void makeSelection(Boolean value) {
        if (isDisabled()) {
            setSelect(false);
            return;
        }
        ArabicLabelToggleGroup group = getGroup();
        value = (value == null) ? !isSelected() : value;
        if (group != null) {
            group.setSelected(this, value);
        }
    }

    @Override
    public String toString() {
        ArabicSupport label = getLabel();
        return label == null ? super.toString() : label.toString();
    }
}
