package com.alphasystem.fx.ui.util;

import de.jensd.fx.glyphs.GlyphIcons;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;

import static de.jensd.fx.glyphs.GlyphsDude.setIcon;
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

    public static <B extends ButtonBase> B populateButton(B source, String label, GlyphIcons icon, String tooltip,
                                                          EventHandler<ActionEvent> action) {
        populateLabeled(source, label, icon, tooltip);
        if (action != null) {
            source.setOnAction(action);
        }
        return source;
    }

    public static <B extends ButtonBase> B populateButton(B source, GlyphIcons icon, String tooltip,
                                                          EventHandler<ActionEvent> action) {
        return populateButton(source, null, icon, tooltip, action);
    }

    public static MenuItem populateMenuItem(MenuItem source, String label, GlyphIcons icon, EventHandler<ActionEvent> action) {
        if (label != null) {
            source.setText(label);
        }
        if (icon != null) {
            setIcon(source, icon);
        }
        if (action != null) {
            source.setOnAction(action);
        }
        return source;
    }

    private static void populateLabeled(Labeled source, String label, GlyphIcons icon, String tooltip) {
        if (label != null) {
            source.setText(label);
        }
        if (tooltip != null) {
            source.setTooltip(new Tooltip(tooltip));
        }
        if (icon != null) {
            setIcon(source, icon);
        }
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
