package com.alphasystem.arabic.ui.util;

import javafx.scene.text.Font;

import static java.lang.System.getProperty;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.FontPosture.REGULAR;
import static javafx.scene.text.FontWeight.BLACK;
import static javafx.scene.text.FontWeight.NORMAL;

/**
 * @author sali
 */
public interface FontConstants {

    String ARABIC_FONT_NAME_KEY = "arabic.font.name";
    String ENGLISH_FONT_NAME_KEY = "english.font.name";
    String ARABIC_FONT_NAME = getProperty(ARABIC_FONT_NAME_KEY, "Arabic Typesetting");
    String ENGLISH_FONT_NAME = getProperty(ENGLISH_FONT_NAME_KEY, "Candara");

    // Arabic Fonts
    Font ARABIC_FONT_36 = font(ARABIC_FONT_NAME, NORMAL, REGULAR, 36.0);
    Font ARABIC_FONT_30 = font(ARABIC_FONT_NAME, NORMAL, REGULAR, 30);
    Font ARABIC_FONT_26 = font(ARABIC_FONT_NAME, NORMAL, REGULAR, 26);
    Font ARABIC_FONT_24 = font(ARABIC_FONT_NAME, NORMAL, REGULAR, 24);
    Font ARABIC_FONT_20 = font(ARABIC_FONT_NAME, NORMAL, REGULAR, 20);
    Font ARABIC_FONT_14 = font(ARABIC_FONT_NAME, NORMAL, REGULAR, 14);

    // English Fonts
    Font ENGLISH_FONT_14 = font(ENGLISH_FONT_NAME, BLACK, REGULAR, 14);
}
