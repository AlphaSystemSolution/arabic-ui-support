package com.alphasystem.arabic.ui.util;

import com.alphasystem.util.AppUtil;
import com.alphasystem.util.GenericPreferences;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.prefs.Preferences;

/**
 * @author sali
 */
public abstract class UIUserPreferences extends GenericPreferences {

    private static final String FONT_NODE_NAME = "FONT_PREFERENCES";
    private static final String FILE_NODE_NAME = "FILE";
    private static final String ARABIC_FONT_NAME_KEY = "arabicFontName";
    private static final String ARABIC_FONT_SIZE = "arabicFontSize";
    private static final String ENGLISH_FONT_NAME = "englishFontName";
    private static final String ENGLISH_FONT_SIZE = "englishFontSize";
    private static final String INITIAL_DIRECTORY_KEY = "initialDirectory";

    protected UIUserPreferences(Class<?> c) {
        super(c);
    }

    protected abstract String nodePrefix();

    protected Preferences getFontNode() {
        return getNode(nodePrefix(), FONT_NODE_NAME);
    }

    protected Preferences getFileNode() {
        return getNode(nodePrefix(), FILE_NODE_NAME);
    }

    public String getArabicFontName() {
        return getFontNode().get(ARABIC_FONT_NAME_KEY, FontUtilities.getDefaultArabicFontName());
    }

    public void setArabicFontName(String name) {
        if (StringUtils.isEmpty(name)) {
            return;
        }
        getFontNode().put(ARABIC_FONT_NAME_KEY, name);
    }

    public long getArabicFontSize() {
        return getFontNode().getLong(ARABIC_FONT_SIZE, FontUtilities.DEFAULT_ARABIC_FONT_SIZE);
    }

    public void setArabicFontSize(long size) {
        if (size <= 0) {
            return;
        }
        getFontNode().putLong(ARABIC_FONT_SIZE, size);
    }

    public String getEnglishFontName() {
        return getFontNode().get(ENGLISH_FONT_NAME, FontUtilities.getDefaultEnglishFont());
    }

    public void setEnglishFontName(String name) {
        if (StringUtils.isEmpty(name)) {
            return;
        }
        getFontNode().put(ENGLISH_FONT_NAME, name);
    }

    public long getEnglishFontSize() {
        return getFontNode().getLong(ENGLISH_FONT_SIZE, FontUtilities.DEFAULT_ENGLISH_FONT_SIZE);
    }

    public void setEnglishFontSize(long size) {
        if (size <= 0) {
            return;
        }
        getFontNode().putLong(ENGLISH_FONT_SIZE, size);
    }

    public File getInitialDirectory() {
        return new File(getFileNode().get(INITIAL_DIRECTORY_KEY, AppUtil.USER_HOME_DIR.getAbsolutePath()));
    }

    public void setInitialDirectory(File dir) {
        if (dir == null) {
            return;
        }
        getFileNode().put(INITIAL_DIRECTORY_KEY, dir.getAbsolutePath());
    }

    public Font getArabicFont() {
        return Font.font(getArabicFontName(), FontWeight.NORMAL, FontPosture.REGULAR, getArabicFontSize());
    }

    public Font getArabicFont20() {
        return Font.font(getArabicFontName(), FontWeight.NORMAL, FontPosture.REGULAR, 20);
    }

    public Font getArabicFont24() {
        return Font.font(getArabicFontName(), FontWeight.NORMAL, FontPosture.REGULAR, 24);
    }

    public Font getArabicFont30() {
        return Font.font(getArabicFontName(), FontWeight.NORMAL, FontPosture.REGULAR, 30);
    }

    public Font getArabicFont36() {
        return Font.font(getArabicFontName(), FontWeight.NORMAL, FontPosture.REGULAR, 36);
    }

    public Font getEnglishFont() {
        return Font.font(getEnglishFontName(), FontWeight.NORMAL, FontPosture.REGULAR, getEnglishFontSize());
    }

    public Font getEnglishFont12() {
        return Font.font(getEnglishFontName(), FontWeight.NORMAL, FontPosture.REGULAR, 12);
    }

    public Font getEnglishFont14() {
        return Font.font(getEnglishFontName(), FontWeight.NORMAL, FontPosture.REGULAR, 14);
    }

}
