package com.alphasystem.arabic.ui.keyboard.skin;

import com.alphasystem.arabic.ui.keyboard.ArabicKeyboard;
import com.alphasystem.arabic.ui.keyboard.KeyboardView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.SkinBase;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;

import static java.lang.String.format;
import static javafx.geometry.Pos.CENTER;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.FontPosture.REGULAR;
import static javafx.scene.text.FontWeight.NORMAL;

/**
 * @author sali
 */
public class ArabicKeyboardSkin extends SkinBase<ArabicKeyboard> {

    private final SkinView skinView = new SkinView();

    public ArabicKeyboardSkin(ArabicKeyboard control) {
        super(control);
        getChildren().add(skinView);

        final ArabicKeyboard view = getSkinnable();
        skinView.textArea.textProperty().addListener((o, ov, nv) -> {
            view.setUnicodeValue(nv);
            view.setHtmlCodeValue(toHtmlCodeString(nv));
        });
    }

    private static String toHtmlCodeString(char unicode) {
        String s = format("%04x", (int) unicode);
        return format("&#x%s;", s);
    }

    private static String toHtmlCodeString(String unicodeValue) {
        StringBuilder builder = new StringBuilder();
        if (unicodeValue == null) {
            return null;
        }
        final char[] chars = unicodeValue.toCharArray();
        for (char c : chars) {
            builder.append(toHtmlCodeString(c));
        }
        return builder.toString();
    }

    public KeyboardView getKeyboard() {
        return skinView.keyboard;
    }

    class SkinView extends VBox {

        @FXML
        KeyboardView keyboard;

        @FXML
        TextArea textArea;

        SkinView() {
            init();
            setSpacing(5);
            setPadding(new Insets(10));
            setAlignment(CENTER);
        }

        private void init() {
            URL fxmlURL = getClass().getResource("/fxml/ArabicKeyboard.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @FXML
        void initialize() {
            textArea.setFont(font("Arabic Typesetting", NORMAL, REGULAR, 36));
            keyboard.setTarget(textArea);
            keyboard.setOnMouseClicked(event -> keyboard.requestFocus());
        }
    }
}
