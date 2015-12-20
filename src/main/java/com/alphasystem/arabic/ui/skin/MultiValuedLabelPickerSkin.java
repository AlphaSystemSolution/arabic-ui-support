package com.alphasystem.arabic.ui.skin;

import com.alphasystem.arabic.model.ArabicSupport;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.ui.ArabicLabelView;
import com.alphasystem.arabic.ui.ArabicSupportGroupPane;
import com.alphasystem.arabic.ui.MultiValuedLabelPicker;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.control.SkinBase;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Popup;

import java.util.List;

import static com.alphasystem.arabic.model.ArabicWord.addTatweel;
import static com.alphasystem.arabic.model.ArabicWord.concatenateWithAnd;
import static com.alphasystem.arabic.ui.util.FontConstants.ARABIC_FONT_26;
import static com.alphasystem.util.AppUtil.getResourceAsStream;

/**
 * @author sali
 */
public class MultiValuedLabelPickerSkin<T extends ArabicSupport> extends SkinBase<MultiValuedLabelPicker<T>> {

    private final ArabicSupportGroupPane<T> pickerPane;

    protected MultiValuedLabelPickerSkin(MultiValuedLabelPicker<T> control, ArabicSupportGroupPane<T> pickerPane) {
        super(control);
        this.pickerPane = pickerPane;
        initializeSkin();
    }

    private void initializeSkin() {
        final MultiValuedLabelPicker<T> skinnable = getSkinnable();

        GridPane gridPane = new GridPane();
        gridPane.setHgap(1);
        gridPane.setAlignment(skinnable.getAlignment());
        gridPane.alignmentProperty().bind(skinnable.alignmentProperty());

        Popup pickerPopup = new Popup();
        pickerPopup.setAutoHide(true);
        pickerPopup.setHideOnEscape(true);
        pickerPopup.getContent().add(pickerPane);
        pickerPopup.setOnHiding(event -> {
            // do something on hiding
            ObservableList<T> selectedValues = pickerPane.getSelectedValues();
            MultiValuedLabelPicker<T> control = skinnable;
            control.getValues().clear();
            control.getValues().addAll(selectedValues);
        });

        ArabicLabelView label = new ArabicLabelView();
        label.setDisable(true);
        label.setWidth(200);
        label.setHeight(40);
        label.setFont(ARABIC_FONT_26);

        updateView(label);

        Button pickerButton = new Button();
        pickerButton.setGraphic(new ImageView(new Image(getResourceAsStream(skinnable.getImageName()))));
        pickerButton.setOnAction(event -> showPopup(pickerPopup, pickerButton));

        gridPane.add(label, 0, 0);
        gridPane.add(pickerButton, 3, 0);
        getChildren().add(gridPane);
    }

    @SuppressWarnings({"unchecked"})
    private void updateView(final ArabicLabelView label) {
        pickerPane.setSelectedValues(null);
        MultiValuedLabelPicker<T> control = getSkinnable();
        ObservableList<T> values = control.getValues();
        updateLabel(values, label);
        pickerPane.setSelectedValues(values);
        values.addListener((ListChangeListener<? super T>) c -> {
            while (c.next()) {
                // first remove current values
                updateLabel(null, label);
                if (c.getAddedSize() > 0) {
                    updateLabel((List<T>) c.getAddedSubList(), label);
                }
            }
        });
    }

    private void updateLabel(List<T> values, ArabicLabelView label) {
        if (values == null || values.isEmpty()) {
            label.setLabel(null);
            return;
        }

        ArabicWord result = values.get(0).getLabel();
        if (result == null) {
            result = new ArabicWord();
        }
        result = addTatweel(result);
        for (int i = 1; i < values.size(); i++) {
            ArabicWord tmp = values.get(i).getLabel();
            if (tmp == null) {
                continue;
            }
            tmp = addTatweel(tmp);
            result = concatenateWithAnd(result, tmp);
        }
        label.setLabel(result);
    }

    private void showPopup(Popup popup, Button button) {
        if (popup.isShowing()) {
            popup.hide();
        } else {
            final Bounds bounds = button.localToScreen(button.getBoundsInLocal());
            popup.show(button, bounds.getMinX(), bounds.getMinY() + bounds.getHeight());
        }
    }
}
