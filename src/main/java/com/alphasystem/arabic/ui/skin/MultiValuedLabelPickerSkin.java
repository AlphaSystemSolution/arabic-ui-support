package com.alphasystem.arabic.ui.skin;

import com.alphasystem.arabic.model.ArabicSupport;
import com.alphasystem.arabic.ui.ArabicSupportGroupPane;
import com.alphasystem.arabic.ui.MultiValuedLabelPicker;
import com.alphasystem.arabic.ui.util.FontAdapter;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.control.SkinBase;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Popup;

import java.util.Collection;

import static com.alphasystem.util.AppUtil.getResourceAsStream;

/**
 * @author sali
 */
public class MultiValuedLabelPickerSkin<T extends ArabicSupport> extends SkinBase<MultiValuedLabelPicker<T>> {

    private final ArabicSupportGroupPane<T> pickerPane;
    private final ArabicSupportGroupPane<T> viewPane;
    private final GridPane labelsGridPane = new GridPane();

    @SuppressWarnings({"unchecked"})
    protected MultiValuedLabelPickerSkin(MultiValuedLabelPicker<T> control,
                                         ArabicSupportGroupPane<T> pickerPane,
                                         ArabicSupportGroupPane<T> viewPane) {
        super(control);
        this.pickerPane = pickerPane;
        this.viewPane = viewPane;
        this.viewPane.getValues().clear();
        this.viewPane.getValues().addAll(getSkinnable().getValues());
        initializeSkin();
        getSkinnable().getValues().addListener((ListChangeListener<? super T>) c -> {
            while (c.next()) {
                this.viewPane.getValues().clear();
                this.pickerPane.setSelectedValues(null);
                if (c.getAddedSize() > 0) {
                    Collection<T> addedSubList = (Collection<T>) c.getAddedSubList();
                    this.viewPane.getValues().addAll(addedSubList);
                    this.pickerPane.setSelectedValues(addedSubList);
                }
            }
        });
    }

    private void initializeSkin() {
        final MultiValuedLabelPicker<T> control = getSkinnable();

        GridPane gridPane = new GridPane();
        gridPane.setHgap(1);
        gridPane.setAlignment(control.getAlignment());
        gridPane.alignmentProperty().bind(control.alignmentProperty());

        Popup pickerPopup = new Popup();
        pickerPopup.setAutoHide(true);
        pickerPopup.setHideOnEscape(true);
        pickerPopup.getContent().add(pickerPane);
        pickerPopup.setOnHiding(event -> {
            // do something on hiding
            ObservableList<T> selectedValues = pickerPane.getSelectedValues();
            control.getValues().clear();
            control.getValues().addAll(selectedValues);
            updateView();
        });

        labelsGridPane.setHgap(3);
        labelsGridPane.setVgap(3);
        updateView();

        Button pickerButton = new Button();
        pickerButton.setGraphic(new ImageView(new Image(getResourceAsStream(control.getImageName()))));
        pickerButton.setOnAction(event -> showPopup(pickerPopup, pickerButton));

        gridPane.add(viewPane, 0, 0);
        gridPane.add(pickerButton, 3, 0);
        getChildren().add(gridPane);
    }

    private void updateView() {
        pickerPane.setSelectedValues(null);
        labelsGridPane.getChildren().clear();
        ObservableList<T> values = getSkinnable().getValues();
        if (values != null && !values.isEmpty()) {
            viewPane.getValues().clear();
            viewPane.getValues().addAll(values);
        }
        pickerPane.setSelectedValues(values);
    }

    private void showPopup(Popup popup, Button button) {
        if (popup.isShowing()) {
            popup.hide();
        } else {
            final Bounds bounds = button.localToScreen(button.getBoundsInLocal());
            popup.show(button, bounds.getMinX(), bounds.getMinY() + bounds.getHeight());
        }
    }

    protected static class SelectedValuesView<T extends ArabicSupport> extends ArabicSupportGroupPane<T> {

        protected SelectedValuesView() {
            super(3, null);
            setPrefWidth(getComputedWidth());
            getStyleClass().addAll("view");
        }

        @Override
        protected void initToggleGroup() {
            toggleGroup.setWidth(48);
            toggleGroup.setHeight(40);
            toggleGroup.setDisable(true);
            toggleGroup.setFont(FontAdapter.getInstance().getArabicRegularFont(24));
        }
    }

}
