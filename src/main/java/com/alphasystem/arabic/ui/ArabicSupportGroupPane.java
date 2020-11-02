package com.alphasystem.arabic.ui;

import com.alphasystem.arabic.model.ArabicSupport;
import com.alphasystem.arabic.ui.util.FontAdapter;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.util.Collection;
import java.util.List;

import static com.alphasystem.fx.ui.util.FontConstants.ARABIC_FONT_30;
import static com.alphasystem.util.AppUtil.getResource;
import static javafx.collections.FXCollections.observableArrayList;
import static org.apache.commons.lang3.ArrayUtils.*;

/**
 * @author sali
 */
public abstract class ArabicSupportGroupPane<T extends ArabicSupport> extends VBox {

    protected static final int DEFAULT_NUM_OF_COLUMNS = 8;
    private static final double SPACING = 10.0;

    protected final ArabicLabelToggleGroup toggleGroup = new ArabicLabelToggleGroup();
    protected final ObservableList<T> values = observableArrayList();
    protected final int numOfColumns;
    protected final FontAdapter fontAdapter;

    protected ArabicSupportGroupPane(FontAdapter fontAdapter, T[] srcValues) {
        this(DEFAULT_NUM_OF_COLUMNS, fontAdapter, srcValues);
    }

    @SuppressWarnings({"unchecked"})
    protected ArabicSupportGroupPane(int numOfColumns, FontAdapter fontAdapter, T[] srcValues) {
        this.fontAdapter = fontAdapter;
        initToggleGroup();

        this.numOfColumns = (numOfColumns <= 0) ? DEFAULT_NUM_OF_COLUMNS : numOfColumns;
        setSpacing(SPACING);
        initialize(srcValues);
        setMinWidth(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setPrefWidth(getComputedWidth() + 120);

        if (srcValues != null) {
            values.addAll(srcValues);
        }
        values.addListener((ListChangeListener<? super T>) c -> {
            while (c.next()) {
                getChildren().clear();
                if (c.getAddedSize() > 0) {
                    List<T> addedSubList = (List<T>) c.getAddedSubList();
                    initialize(addedSubList.toArray((T[]) new ArabicSupport[addedSubList.size()]));
                }
            }
        });

        getStyleClass().addAll("popup");
    }

    public static double roundTo100(double srcValue) {
        return (double) ((((int) srcValue) + 99) / 100) * 100;
    }

    protected double getComputedWidth() {
        double width = toggleGroup.getWidth();
        return roundTo100((width + SPACING) * numOfColumns);
    }

    public final ObservableList<T> getValues() {
        return values;
    }

    protected void initialize(T[] srcValues) {
        if (srcValues == null) {
            return;
        }
        ArabicSupport[] values = new ArabicSupport[0];
        values = addAll(values, srcValues);
        while (values.length % numOfColumns != 0) {
            values = add(values, null);
        }

        int startIndex = 0;
        int endIndex = numOfColumns;
        while (startIndex < values.length) {
            ArabicSupport[] subarray = subarray(values, startIndex, endIndex);
            reverse(subarray);

            FlowPane flowPane = new FlowPane();
            flowPane.setHgap(SPACING);

            for (ArabicSupport label : subarray) {
                ArabicLabelView view = new ArabicLabelView(label, fontAdapter);
                view.setGroup(toggleGroup);
                flowPane.getChildren().add(view);
            }
            getChildren().add(flowPane);
            startIndex = endIndex;
            endIndex += numOfColumns;
        }
    }

    protected void initToggleGroup() {
        toggleGroup.setWidth(92);
        toggleGroup.setHeight(48);
        toggleGroup.setFont(ARABIC_FONT_30);
    }

    @Override
    public String getUserAgentStylesheet() {
        return getResource("arabic-ui-support.css").toExternalForm();
    }

    @SuppressWarnings({"unchecked"})
    public final ObservableList<T> getSelectedValues() {
        ObservableList<T> values = observableArrayList();
        toggleGroup.getSelectedValues().forEach(view -> values.add((T) view.getLabel()));
        return values;
    }

    /**
     * Resets the group with new st of values. This method first un-select any previously selected values and
     * the select new values.
     *
     * @param selectedValues values to be reset in toggle group
     */
    public final void setSelectedValues(Collection<T> selectedValues) {
        int len = (selectedValues == null) ? 0 : selectedValues.size();
        ArabicSupport[] values = (len <= 0) ? null : selectedValues.toArray(new ArabicSupport[len]);
        toggleGroup.reset(values);
    }

}
