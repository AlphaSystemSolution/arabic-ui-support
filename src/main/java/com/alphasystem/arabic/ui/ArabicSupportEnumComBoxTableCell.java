package com.alphasystem.arabic.ui;

import com.alphasystem.arabic.model.ArabicSupportEnum;
import com.alphasystem.arabic.ui.util.UIUserPreferences;
import com.alphasystem.util.GenericPreferences;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import static com.alphasystem.arabic.ui.ComboBoxHelper.createComboBox;
import static com.alphasystem.arabic.ui.ListType.*;
import static java.lang.String.format;
import static javafx.scene.control.ContentDisplay.GRAPHIC_ONLY;

/**
 * @author sali
 */
public class ArabicSupportEnumComBoxTableCell<S, T extends Enum<T> & ArabicSupportEnum> extends ComboBoxTableCell<S, T> {

    private final Text labelText;
    private final Text arabicText;
    private final ListType type;
    private final ComboBox<T> comboBox;

    public ArabicSupportEnumComBoxTableCell(TableColumn<S, T> column, T... values) {
        this(column, LABEL_AND_CODE, values);
    }

    public ArabicSupportEnumComBoxTableCell(TableColumn<S, T> column, ListType type, T... values) {
        super(values);
        setContentDisplay(GRAPHIC_ONLY);
        this.type = type;
        labelText = new Text();
        final UIUserPreferences preferences = GenericPreferences.getInstance(UIUserPreferences.class);
        labelText.setFont(preferences.getEnglishFont());
        arabicText = new Text();
        arabicText.setFont(preferences.getArabicFont());
        comboBox = createComboBox(values);
        comboBox.editableProperty().bind(column.editableProperty());
        comboBox.disableProperty().bind(column.editableProperty().not());
    }

    @Override
    public void startEdit() {
        if (!isEditable() || !getTableView().isEditable() || !getTableColumn().isEditable()) {
            return;
        }

        comboBox.getSelectionModel().select(getItem());

        super.startEdit();
        setText(null);
        setGraphic(comboBox);
    }

    @Override
    public void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);

        TextFlow textFlow = new TextFlow();
        Node graphic = null;
        if (item != null && !empty) {
            labelText.setText(format("(%s) ", item.getCode()));
            arabicText.setText(item.toLabel().toUnicode());
            if (type.equals(LABEL_ONLY)) {
                textFlow.getChildren().add(arabicText);
            } else if (type.equals(CODE_ONLY)) {
                textFlow.getChildren().add(labelText);
            } else {
                textFlow.getChildren().addAll(labelText, arabicText);
            }
            graphic = new Group(textFlow);
        }
        setGraphic(graphic);
    }
}
