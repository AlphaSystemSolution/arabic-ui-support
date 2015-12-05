package com.alphasystem.arabic.ui;

import com.alphasystem.arabic.model.ArabicSupport;
import com.alphasystem.arabic.ui.skin.ArabicLabelViewSkin;
import javafx.beans.property.*;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.text.Font;

import static com.alphasystem.arabic.ui.ArabicLabelToggleGroup.DEFAULT_FONT;

/**
 * @author sali
 */
public class ArabicLabelView extends Control {

    private static final int DEFAULT_WIDTH = 64;
    private static final int DEFAULT_HEIGHT = 64;

    private final DoubleProperty labelWidth = new SimpleDoubleProperty(DEFAULT_WIDTH, "width");
    private final DoubleProperty labelHeight = new SimpleDoubleProperty(DEFAULT_HEIGHT, "height");
    private final ReadOnlyBooleanWrapper selected = new ReadOnlyBooleanWrapper(false, "selected");
    private final BooleanProperty select = new SimpleBooleanProperty();
    private final ObjectProperty<Font> font = new SimpleObjectProperty<>(DEFAULT_FONT, "font");
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
        setLabelWidth(DEFAULT_WIDTH);
        setLabelHeight(DEFAULT_HEIGHT);
        setFont(DEFAULT_FONT);
        select.addListener((observable, oldValue, newValue) -> makeSelection(newValue));
        setSelect(false);
    }

    @Override
    protected Skin<ArabicLabelView> createDefaultSkin() {
        return new ArabicLabelViewSkin(this);
    }

    public final double getLabelHeight() {
        return labelHeight.get();
    }

    public final void setLabelHeight(double labelHeight) {
        this.labelHeight.set(labelHeight);
    }

    public final DoubleProperty labelHeightProperty() {
        return labelHeight;
    }

    public final double getLabelWidth() {
        return labelWidth.get();
    }

    public final void setLabelWidth(double labelWidth) {
        this.labelWidth.set(labelWidth);
    }

    public final DoubleProperty labelWidthProperty() {
        return labelWidth;
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
        this.font.set(font);
    }

    public final ObjectProperty<Font> fontProperty() {
        return font;
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
