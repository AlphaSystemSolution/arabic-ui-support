package com.alphasystem.arabic.ui;

import com.alphasystem.arabic.ui.keyboard.ArabicKeyboard;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * @author sali
 */
public class TestApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Test");

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        // primaryStage.setWidth(bounds.getWidth());
        //primaryStage.setHeight(bounds.getHeight());

        primaryStage.setWidth(800);
        primaryStage.setHeight(600);

        Scene scene = new Scene(new ArabicKeyboard());
        //primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
