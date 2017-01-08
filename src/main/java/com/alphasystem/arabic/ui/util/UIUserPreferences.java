package com.alphasystem.arabic.ui.util;

import com.alphasystem.util.GenericPreferences;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.List;
import java.util.prefs.Preferences;

/**
 * @author sali
 */
public abstract class UIUserPreferences extends GenericPreferences {

    private static final String FONT_NODE_NAME = "FONT_PREFERENCES";
    private static final String KFGQPC_UTHMAN_TAHA_NASKH = "KFGQPC Uthman Taha Naskh";
    private static final String TRADITIONAL_ARABIC = "Traditional Arabic";
    private static final String ARABIC_TYPESETTING = "Arabic Typesetting";
    private static final String ARIAL = "Arial";
    private static final String DEFAULT_FONT_NAME = getDefaultArabicFontName();
    private static final String ARABIC_FONT_NAME_KEY = "arabicFontName";
    private static final String ARABIC_FONT_SIZE = "arabicFontSize";
    private static final String ENGLISH_FONT_NAME = "englishFontName";
    private static final String ENGLISH_FONT_SIZE = "englishFontSize";

    protected UIUserPreferences(Class<?> c) {
        super(c);
    }

    protected Preferences getFontNode() {
        return getNode(FONT_NODE_NAME);
    }

    public String getArabicFontName() {
        return getFontNode().get(ARABIC_FONT_NAME_KEY, DEFAULT_FONT_NAME);
    }

    public void setArabicFontName(String name) {
        getFontNode().put(ARABIC_FONT_NAME_KEY, name);
    }

    public long getArabicFontSize(){
        return getFontNode().getLong(ARABIC_FONT_SIZE, 20);
    }

    public void setArabicFontSize(long size){
        getFontNode().putLong(ARABIC_FONT_SIZE, size);
    }

    public String getEnglishFontName(){
        return getFontNode().get(ENGLISH_FONT_NAME, "Candara");
    }

    public void setEnglishFontName(String name){
        getFontNode().put(ENGLISH_FONT_NAME, name);
    }

    public long getEnglishFontSize(){
        return getFontNode().getLong(ENGLISH_FONT_SIZE, 12);
    }

    public void setEnglishFontSize(long size){
        getFontNode().putLong(ENGLISH_FONT_SIZE, size);
    }

    public Font getArabicFont(){
        return Font.font(getArabicFontName(), FontWeight.NORMAL, FontPosture.REGULAR, getArabicFontSize());
    }

    public Font getEnglishFont(){
        return Font.font(getEnglishFontName(), FontWeight.NORMAL, FontPosture.REGULAR, getEnglishFontSize());
    }

    private static String getDefaultArabicFontName() {
        final List<String> families = Font.getFamilies();
        if (families.contains(KFGQPC_UTHMAN_TAHA_NASKH)) {
            return KFGQPC_UTHMAN_TAHA_NASKH;
        }
        if (families.contains(TRADITIONAL_ARABIC)) {
            return TRADITIONAL_ARABIC;
        }
        if (families.contains(ARABIC_TYPESETTING)) {
            return ARABIC_TYPESETTING;
        }
        return ARIAL;
    }
}
