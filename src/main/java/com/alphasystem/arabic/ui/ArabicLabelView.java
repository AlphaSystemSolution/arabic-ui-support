package com.alphasystem.arabic.ui;

import com.alphasystem.arabic.model.ArabicSupport;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.ui.skin.ArabicLabelViewSkin;
import javafx.beans.property.*;
import javafx.geometry.Pos;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import static com.alphasystem.arabic.ui.ArabicLabelToggleGroup.DEFAULT_STROKE;
import static com.alphasystem.fx.ui.util.FontConstants.ARABIC_FONT_36;
import static javafx.geometry.Pos.CENTER;
import static javafx.scene.paint.Color.*;

/**
 * @author sali
 */
public class ArabicLabelView extends Control {

    private static final int DEFAULT_WIDTH = 64;
    private static final int DEFAULT_HEIGHT = 64;

    private final ReadOnlyBooleanWrapper selected = new ReadOnlyBooleanWrapper(false, "selected");
    private final BooleanProperty select = new SimpleBooleanProperty();
    private final ObjectProperty<Font> font = new SimpleObjectProperty<>(ARABIC_FONT_36, "font");
    private final ObjectProperty<Paint> stroke = new SimpleObjectProperty<>(DEFAULT_STROKE, "stroke");
    private final ObjectProperty<Paint> unSelectedStroke = new SimpleObjectProperty<>(BLACK, "unSelectedStroke");
    private final ObjectProperty<Paint> selectedStroke = new SimpleObjectProperty<>(RED, "unSelectedStroke");
    private final ObjectProperty<Paint> disabledStroke = new SimpleObjectProperty<>(LIGHTGRAY, "unSelectedStroke");
    private final ObjectProperty<Pos> alignment = new SimpleObjectProperty<>(CENTER, "alignment");
    private final ObjectProperty<ArabicSupport> label = new SimpleObjectProperty<>(null, "label");
    private final StringProperty text = new SimpleStringProperty(null, "text");
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
            setText(getLabelText(nV));
        });
        setLabel(label);
        setText(getLabelText(label));
        setWidth(DEFAULT_WIDTH);
        setHeight(DEFAULT_HEIGHT);
        setFont(ARABIC_FONT_36);
        setStroke(DEFAULT_STROKE);
        setUnSelectedStroke(BLACK);
        setSelectedStroke(RED);
        setDisabledStroke(LIGHTGRAY);
        setAlignment(CENTER);
        select.addListener((observable, oldValue, newValue) -> makeSelection(newValue));
        setSelect(false);
    }

    private static String getLabelText(ArabicSupport value) {
        String text = "";
        if (value != null) {
            ArabicWord arabicWord = value.toLabel();
            text = (arabicWord == null) ? "" : arabicWord.toUnicode();
        }
        return text;
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

    void setSelected(boolean s) {
        selected.set(s);
    }

    public final ReadOnlyBooleanProperty selectedProperty() {
        return selected.getReadOnlyProperty();
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
        this.font.set((font == null) ? ARABIC_FONT_36 : font);
    }

    public final ObjectProperty<Font> fontProperty() {
        return font;
    }

    public final Paint getStroke() {
        return stroke.get();
    }

    public final void setStroke(Paint stroke) {
        this.stroke.set((stroke == null) ? DEFAULT_STROKE : stroke);
    }

    public final ObjectProperty<Paint> strokeProperty() {
        return stroke;
    }

    public final Paint getUnSelectedStroke() {
        return unSelectedStroke.get();
    }

    public final void setUnSelectedStroke(Paint unSelectedStroke) {
        this.unSelectedStroke.set((unSelectedStroke == null) ? BLACK : unSelectedStroke);
    }

    public final ObjectProperty<Paint> unSelectedStrokeProperty() {
        return unSelectedStroke;
    }

    public final Paint getSelectedStroke() {
        return selectedStroke.get();
    }

    public final void setSelectedStroke(Paint selectedStroke) {
        this.selectedStroke.set((selectedStroke == null) ? RED : selectedStroke);
    }

    public final ObjectProperty<Paint> selectedStrokeProperty() {
        return selectedStroke;
    }

    public final Paint getDisabledStroke() {
        return disabledStroke.get();
    }

    public final void setDisabledStroke(Paint disabledStroke) {
        this.disabledStroke.set((disabledStroke == null) ? LIGHTGRAY : disabledStroke);
    }

    public final ObjectProperty<Paint> disabledStrokeProperty() {
        return disabledStroke;
    }

    public final Pos getAlignment() {
        return alignment.get();
    }

    public final void setAlignment(Pos alignment) {
        this.alignment.set((alignment == null) ? CENTER : alignment);
    }

    public final ObjectProperty<Pos> alignmentProperty() {
        return alignment;
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

    public final String getText() {
        return text.get();
    }

    public final void setText(String text) {
        this.text.set(text);
    }

    public final StringProperty textProperty() {
        return text;
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
