package com.alphasystem.arabic.ui.skin;

import com.alphasystem.arabic.model.ArabicSupport;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.ui.ArabicLabelView;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import static javafx.beans.binding.Bindings.when;
import static javafx.scene.paint.Color.*;

/**
 * @author sali
 */
public class ArabicLabelViewSkin extends SkinBase<ArabicLabelView> {

    public ArabicLabelViewSkin(final ArabicLabelView view) {
        super(view);

        StackPane stackPane = new StackPane();
        stackPane.setFocusTraversable(true);

        final Text label = new Text();
        label.setFont(view.getFont());
        label.fontProperty().bind(view.fontProperty());
        label.setOnMouseClicked(event -> makeSelection(view));
        view.labelProperty().addListener((o, oV, nV) -> {
            label.setText(getLabelText(nV));
        });
        label.setText(getLabelText(view.getLabel()));

        final Rectangle background = new Rectangle(view.getWidth(), view.getHeight());
        background.setFill(TRANSPARENT);
        background.setStrokeWidth(1);
        background.setArcWidth(6);
        background.setArcHeight(6);
        background.setOnMouseClicked(event -> makeSelection(view));
        background.widthProperty().bind(view.widthProperty());
        background.heightProperty().bind(view.heightProperty());
        view.disabledProperty().addListener((o, ov, nv) -> {
            Color strokeColor = nv ? LIGHTGRAY : (view.isSelected() ? RED : BLACK);
            background.setStroke(strokeColor);
        });
        view.selectedProperty().addListener((o, ov, nv) -> {
            Color strokeColor = nv ? RED : (view.isDisabled() ? LIGHTGRAY : BLACK);
            background.setStroke(strokeColor);
        });
        background.setStroke(view.isDisabled() ? LIGHTGRAY : (view.isSelected() ? RED : BLACK));
        background.strokeWidthProperty().bind(when(view.selectedProperty()).then(2).otherwise(1));
        stackPane.disableProperty().bind(view.disabledProperty());

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
