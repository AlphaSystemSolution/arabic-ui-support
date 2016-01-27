package com.alphasystem.fx.ui.util;

import de.jensd.fx.glyphs.GlyphIcon;
import de.jensd.fx.glyphs.GlyphIcons;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;

import java.lang.reflect.Constructor;

import static javafx.scene.Cursor.DEFAULT;
import static javafx.scene.Cursor.WAIT;
import static javafx.scene.control.ScrollPane.ScrollBarPolicy.AS_NEEDED;

/**
 * @author sali
 */
public final class UiUtilities {

    public static void defaultCursor(Node node) {
        changeCursor(node, DEFAULT);
    }

    public static void waitCursor(Node node) {
        changeCursor(node, WAIT);
    }

    public static ScrollPane wrapInScrollPane(Node node) {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(node);
        scrollPane.setVbarPolicy(AS_NEEDED);
        scrollPane.setHbarPolicy(AS_NEEDED);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        return scrollPane;
    }

    public static <T extends Enum<T> & GlyphIcons, V extends GlyphIcon<T>> V createIcon(T icon, String size,
                                                                                        Class<V> viewClass) {
        V view = null;
        if (icon != null) {
            try {
                Constructor<V> constructor = viewClass.getConstructor(icon.getClass());
                view = constructor.newInstance(icon);
                view.setSize(size);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return view;
    }

    private static void changeCursor(Node node, Cursor cursor) {
        if (node != null) {
            Scene scene = node.getScene();
            if (scene != null) {
                scene.setCursor(cursor);
            }
        }
    }

}
