package com.alphasystem.arabic.ui;

import de.jensd.fx.glyphs.GlyphIcon;
import de.jensd.fx.glyphs.GlyphIcons;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import de.jensd.fx.glyphs.octicons.OctIcon;
import de.jensd.fx.glyphs.octicons.OctIconView;
import de.jensd.fx.glyphs.weathericons.WeatherIcon;
import de.jensd.fx.glyphs.weathericons.WeatherIconView;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.lang.reflect.Constructor;

import static com.alphasystem.arabic.ui.util.UiUtilities.wrapInScrollPane;
import static javafx.geometry.Pos.CENTER;
import static javafx.scene.control.TabPane.TabClosingPolicy.UNAVAILABLE;
import static javafx.scene.text.FontPosture.REGULAR;
import static javafx.scene.text.FontWeight.BLACK;

/**
 * @author sali
 */
public class FontAwesomeView extends BorderPane {

    public static final int NUM_OF_COLUMNS = 8;
    public static final int VGAP = 5;
    public static final int HGAP = 2;
    public static final int WIDTH = 196;

    public FontAwesomeView() {
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(UNAVAILABLE);
        tabPane.getTabs().addAll(createTab(FontAwesomeIcon.values(), FontAwesomeIconView.class),
                createTab(MaterialDesignIcon.values(), MaterialDesignIconView.class),
                createTab(MaterialIcon.values(), MaterialIconView.class),
                createTab(OctIcon.values(), OctIconView.class),
                createTab(WeatherIcon.values(), WeatherIconView.class));

        setCenter(tabPane);
    }

    private <T extends Enum<T> & GlyphIcons, V extends GlyphIcon<T>> Tab createTab(T[] icons, Class<V> viewClass) {
        return new Tab(icons[0].getClass().getSimpleName(), initializeIcons(icons, viewClass));
    }

    @SuppressWarnings({"unchecked"})
    private <T extends Enum<T> & GlyphIcons, V extends GlyphIcon<T>> Node initializeIcons(T[] icons, Class<V> viewClass) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(HGAP);
        gridPane.setVgap(VGAP);
        gridPane.setAlignment(CENTER);

        int length = icons.length;
        while (length % NUM_OF_COLUMNS != 0) {
            length++;
        }
        int column = 0;
        int row = -1;
        for (int i = 0; i < length; i++) {
            if (i % NUM_OF_COLUMNS == 0) {
                column = 0;
                row++;
            }
            T icon = i < icons.length ? icons[i] : null;
            gridPane.add(createButton(icon, viewClass), column, row);
            column++;
        }

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(wrapInScrollPane(gridPane));
        return borderPane;
    }

    private <T extends Enum<T> & GlyphIcons, V extends GlyphIcon<T>> V createIcon(T icon, Class<V> viewClass) {
        V view = null;
        if (icon != null) {
            try {
                Constructor<V> constructor = viewClass.getConstructor(icon.getClass());
                view = constructor.newInstance(icon);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return view;
    }

    private <T extends Enum<T> & GlyphIcons, V extends GlyphIcon<T>> Button createButton(T icon, Class<V> viewClass) {
        Button button = new Button();
        button.setFont(Font.font("Candara", BLACK, REGULAR, 10.0));
        button.setTextAlignment(TextAlignment.CENTER);
        button.setAlignment(CENTER);
        button.setMaxWidth(WIDTH);
        button.setMinWidth(WIDTH);
        if (icon != null) {
            button.setGraphic(createIcon(icon, viewClass));
            button.setText(icon.name());
        }
        return button;
    }


}