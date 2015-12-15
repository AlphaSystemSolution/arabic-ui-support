package com.alphasystem.arabic.ui.skin;

import com.alphasystem.arabic.model.ArabicSupport;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.ui.ArabicLabelView;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import static javafx.beans.binding.Bindings.when;
import static javafx.scene.paint.Color.TRANSPARENT;
import static javafx.scene.text.TextAlignment.RIGHT;

/**
 * @author sali
 */
public class ArabicLabelViewSkin extends SkinBase<ArabicLabelView> {

    public ArabicLabelViewSkin(final ArabicLabelView view) {
        super(view);

        StackPane stackPane = new StackPane();
        stackPane.setAlignment(view.getAlignment());
        stackPane.setFocusTraversable(true);

        final Text label = new Text();
        label.setFont(view.getFont());
        label.fontProperty().bind(view.fontProperty());
        label.setOnMouseClicked(event -> makeSelection(view));
        view.labelProperty().addListener((o, oV, nV) -> {
            label.setText(getLabelText(nV));
        });
        label.setText(getLabelText(view.getLabel()));
        label.setStroke(view.getStroke());
        label.setTextAlignment(RIGHT);

        final Rectangle background = new Rectangle(view.getWidth(), view.getHeight());
        background.setFill(TRANSPARENT);
        background.setStrokeWidth(1);
        background.setArcWidth(6);
        background.setArcHeight(6);
        background.setOnMouseClicked(event -> makeSelection(view));
        background.widthProperty().bind(view.widthProperty());
        background.heightProperty().bind(view.heightProperty());
        view.disabledProperty().addListener((o, ov, nv) -> {
            Paint strokeColor = nv ? view.getDisabledStroke() : (view.isSelected() ? view.getSelectedStroke() :
                    view.getUnSelectedStroke());
            background.setStroke(strokeColor);
        });
        view.selectedProperty().addListener((o, ov, nv) -> {
            Paint strokeColor = nv ? view.getSelectedStroke() : (view.isDisabled() ? view.getDisabledStroke()
                    : view.getUnSelectedStroke());
            background.setStroke(strokeColor);
        });
        label.strokeProperty().bind(view.strokeProperty());
        background.setStroke(view.isDisabled() ? view.getDisabledStroke() : (view.isSelected() ?
                view.getSelectedStroke() : view.getUnSelectedStroke()));
        background.strokeWidthProperty().bind(when(view.selectedProperty()).then(2).otherwise(1));
        stackPane.disableProperty().bind(view.disabledProperty());
        stackPane.alignmentProperty().bind(view.alignmentProperty());

        stackPane.getChildren().addAll(background, label);
        getChildren().add(stackPane);
    }

    private static String getLabelText(ArabicSupport value) {
        String text = "";
        if (value != null) {
            ArabicWord arabicWord = value.getLabel();
            text = arabicWord == null ? "" : arabicWord.toUnicode();
        }
        return text;
    }

    private void makeSelection(ArabicLabelView view) {
        view.setSelect(!view.isSelected());
    }
}
