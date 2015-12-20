package com.alphasystem.arabic.ui;

import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;
import com.alphasystem.morphologicalanalysis.morphology.model.support.NounOfPlaceAndTime;
import com.alphasystem.morphologicalanalysis.morphology.model.support.VerbalNoun;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
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
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());

        VBox vBox = new VBox();
        vBox.setSpacing(10);

        RootLettersPicker rootLettersPicker = new RootLettersPicker();
        rootLettersPicker.setRootLetters(new RootLetters(ArabicLetterType.NOON, ArabicLetterType.SAD, ArabicLetterType.RA));
        vBox.getChildren().add(rootLettersPicker);

        VerbalNounsPicker verbalNounsPicker = new VerbalNounsPicker();
        verbalNounsPicker.getValues().addAll(VerbalNoun.VERBAL_NOUN_V1, VerbalNoun.VERBAL_NOUN_V10);
        vBox.getChildren().add(verbalNounsPicker);

        NounOfPlaceTimesPicker nounOfPlaceTimesPicker = new NounOfPlaceTimesPicker();
        nounOfPlaceTimesPicker.getValues().addAll(NounOfPlaceAndTime.NOUN_OF_PLACE_AND_TIME_V1,
                NounOfPlaceAndTime.NOUN_OF_PLACE_AND_TIME_V2, NounOfPlaceAndTime.NOUN_OF_PLACE_AND_TIME_V3);
        vBox.getChildren().add(nounOfPlaceTimesPicker);

        Scene scene = new Scene(vBox);
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
