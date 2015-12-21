package com.alphasystem.arabic.ui.util;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;

import static javafx.scene.Cursor.DEFAULT;
import static javafx.scene.Cursor.WAIT;

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

    private static void changeCursor(Node node, Cursor cursor) {
        if (node != null) {
            Scene scene = node.getScene();
            if (scene != null) {
                scene.setCursor(cursor);
            }
        }
    }
}
