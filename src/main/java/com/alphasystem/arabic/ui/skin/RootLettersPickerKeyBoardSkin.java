package com.alphasystem.arabic.ui.skin;

import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.ui.ArabicLabelToggleGroup;
import com.alphasystem.arabic.ui.ArabicLabelView;
import com.alphasystem.arabic.ui.RootLettersPickerKeyBoard;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import static com.alphasystem.arabic.model.ArabicLetterType.*;
import static com.alphasystem.arabic.model.ArabicLetterType.DTHA;
import static com.alphasystem.arabic.model.ArabicLetterType.ZAIN;
import static javafx.geometry.NodeOrientation.RIGHT_TO_LEFT;

/**
 * @author sali
 */
public class RootLettersPickerKeyBoardSkin extends SkinBase<RootLettersPickerKeyBoard> {

    private static final ArabicLetterType[] ROW_1 = {DDAD, SAD, THA, QAF, FA, GHAIN, AIN, HHA, KHA, HA, JEEM, DAL, THAL};
    private static final ArabicLetterType[] ROW_2 = {SHEEN, SEEN, YA, BA, LAM, ALIF, TA, NOON, MEEM, KAF, TTA, null, null};
    private static final ArabicLetterType[] ROW_3 = {YA_HAMZA_ABOVE, HAMZA, WAW_HAMZA_ABOVE, RA, ALIF_MAKSURA,
            TA_MARBUTA, WAW, ZAIN, DTHA, null, null};

    /**
     * Constructor for all SkinBase instances.
     *
     * @param control The control for which this Skin should attach to.
     */
    public RootLettersPickerKeyBoardSkin(RootLettersPickerKeyBoard control) {
        super(control);
        getChildren().setAll(new SkinView(control));
    }

    private class SkinView extends VBox {

        private int currentIndex;
        private ArabicLabelView currentView;
        private final RootLettersPickerKeyBoard control;
        private final ArabicLabelToggleGroup group = new ArabicLabelToggleGroup();
        private final ArabicLabelView[] labels = new ArabicLabelView[4];
        private final ArabicLabelView firstRadicalLabel = new ArabicLabelView();
        private final ArabicLabelView secondRadicalLabel = new ArabicLabelView();
        private final ArabicLabelView thirdRadicalLabel = new ArabicLabelView();
        private final ArabicLabelView fourthRadicalLabel = new ArabicLabelView();

        private SkinView(RootLettersPickerKeyBoard control) {
            this.control = control;
            initialize();
        }

        private void initialize() {
            setPadding(control.getPadding());
            paddingProperty().bind(control.paddingProperty());
            setSpacing(control.getSpacing());
            spacingProperty().bind(control.spacingProperty());

            labels[0] = firstRadicalLabel;
            labels[1] = secondRadicalLabel;
            labels[2] = thirdRadicalLabel;
            labels[3] = fourthRadicalLabel;

            // initialize label group
            initializeLabelGroup();

            // initialize labels
            initializeFirstRadical();
            initializeSecondRadical();
            initializeThirdRadical();
            initializeFourthRadical();

            selectFirst();

            FlowPane rootLettersPane = new FlowPane();
            rootLettersPane.setHgap(control.getSpacing());
            rootLettersPane.hgapProperty().bind(control.spacingProperty());
            rootLettersPane.setNodeOrientation(RIGHT_TO_LEFT);
            rootLettersPane.getChildren().addAll(firstRadicalLabel, secondRadicalLabel, thirdRadicalLabel, fourthRadicalLabel);
            rootLettersPane.setAlignment(Pos.TOP_CENTER);

            getChildren().addAll(rootLettersPane, createRow1(), createRow2(), createRow3());
            setFocusTraversable(true);

            getStyleClass().addAll("popup");
        }

        private void initializeLabelGroup() {
            group.setMultipleSelect(false);
            group.setWidth(control.getSelectedLabelWidth());
            group.setHeight(control.getSelectedLabelHeight());
            group.setFont(control.getFont());
            group.widthProperty().bind(control.selectedLabelWidthProperty());
            group.heightProperty().bind(control.selectedLabelHeightProperty());
            group.fontProperty().bind(control.fontProperty());
        }

        private void initializeFirstRadical() {
            firstRadicalLabel.setLabel(control.firstRadicalProperty().get());
            firstRadicalLabel.setGroup(group);
            firstRadicalLabel.selectedProperty().addListener((observable, oldValue, newValue) -> {
                currentIndex = 0;
                currentView = firstRadicalLabel;
            });
            firstRadicalLabel.labelProperty().addListener((observable, oldValue, newValue) -> control.setFirstRadical((ArabicLetterType) newValue));
            control.firstRadicalProperty().addListener((observable, oldValue, newValue) -> firstRadicalLabel.setLabel(newValue));
        }

        private void initializeSecondRadical() {
            secondRadicalLabel.setLabel(control.secondRadicalProperty().get());
            secondRadicalLabel.setGroup(group);
            secondRadicalLabel.selectedProperty().addListener((observable, oldValue, newValue) -> {
                currentIndex = 1;
                currentView = secondRadicalLabel;
            });
            secondRadicalLabel.labelProperty().addListener((observable, oldValue, newValue) -> control.setSecondRadical((ArabicLetterType) newValue));
            control.secondRadicalProperty().addListener((observable, oldValue, newValue) -> secondRadicalLabel.setLabel(newValue));
        }

        private void initializeThirdRadical() {
            thirdRadicalLabel.setLabel(control.thirdRadicalProperty().get());
            thirdRadicalLabel.setGroup(group);
            thirdRadicalLabel.selectedProperty().addListener((observable, oldValue, newValue) -> {
                currentIndex = 2;
                currentView = thirdRadicalLabel;
            });
            thirdRadicalLabel.labelProperty().addListener((observable, oldValue, newValue) -> control.setThirdRadical((ArabicLetterType) newValue));
            control.thirdRadicalProperty().addListener((observable, oldValue, newValue) -> thirdRadicalLabel.setLabel(newValue));
        }

        private void initializeFourthRadical() {
            fourthRadicalLabel.setLabel(control.fourthRadicalProperty().get());
            fourthRadicalLabel.setGroup(group);
            fourthRadicalLabel.selectedProperty().addListener((observable, oldValue, newValue) -> {
                currentIndex = 3;
                currentView = fourthRadicalLabel;
            });
            fourthRadicalLabel.labelProperty().addListener((observable, oldValue, newValue) -> control.setFourthRadical((ArabicLetterType) newValue));
            control.fourthRadicalProperty().addListener((observable, oldValue, newValue) -> fourthRadicalLabel.setLabel(newValue));
        }

        private void selectFirst() {
            currentIndex = 0;
            currentView = firstRadicalLabel;
            currentView.setSelect(true);
        }

        private FlowPane createRow(ArabicLetterType[] row) {
            FlowPane flowPane = new FlowPane();
            flowPane.setHgap(control.getSpacing());
            flowPane.hgapProperty().bind(control.spacingProperty());
            flowPane.setMinWidth(700);

            for (ArabicLetterType arabicLetterType : row) {
                flowPane.getChildren().add(createKeyBoardButton(arabicLetterType));
            }
            return flowPane;
        }

        private FlowPane createRow1() {
            return createRow(ROW_1);
        }

        private FlowPane createRow2() {
            return createRow(ROW_2);
        }

        private FlowPane createRow3() {
            final FlowPane flowPane = createRow(ROW_3);
            flowPane.getChildren().add(createClearButton());
            return flowPane;
        }

        private Button createClearButton() {
            Button clearButton = new Button("    Clear   ");
            clearButton.setStyle(control.getStyle());
            setClearButtonPrefSize(clearButton, control.getKeyboardButtonWidth(), control.getKeyboardButtonHeight(), control.getSpacing());
            control.keyboardButtonWidthProperty().addListener((observable, oldValue, newValue) ->
                    setClearButtonPrefSize(clearButton, (Double) newValue, control.getKeyboardButtonHeight(), control.getSpacing()));
            control.keyboardButtonHeightProperty().addListener((observable, oldValue, newValue) ->
                    setClearButtonPrefSize(clearButton, control.getKeyboardButtonWidth(), (Double) newValue, control.getSpacing()));
            control.spacingProperty().addListener((observable, oldValue, newValue) ->
                    setClearButtonPrefSize(clearButton, control.getKeyboardButtonWidth(), control.getKeyboardButtonHeight(), (Double) newValue));
            clearButton.setMinSize(USE_PREF_SIZE, USE_PREF_SIZE);
            clearButton.setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
            clearButton.setOnAction(event -> {
                firstRadicalLabel.setLabel(ArabicLetterType.FA);
                secondRadicalLabel.setLabel(ArabicLetterType.AIN);
                thirdRadicalLabel.setLabel(ArabicLetterType.LAM);
                fourthRadicalLabel.setLabel(null);
                selectFirst();
            });
            return clearButton;
        }

        private void setClearButtonPrefSize(Button clearButton, double width, double height, double spacing) {
            clearButton.setPrefSize(width * 2 + spacing, height);
        }

        private Button createKeyBoardButton(ArabicLetterType letter) {
            Button button = new Button();
            button.setStyle(control.getStyle());
            boolean disable = (letter == null);
            button.setDisable(disable);
            button.setPrefSize(control.getKeyboardButtonWidth(), control.getKeyboardButtonHeight());
            button.setMinSize(USE_PREF_SIZE, USE_PREF_SIZE);
            button.setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
            button.prefWidthProperty().bind(control.keyboardButtonWidthProperty());
            button.prefHeightProperty().bindBidirectional(control.keyboardButtonHeightProperty());
            Text graphic = null;
            if (!disable) {
                graphic = new Text(letter.toUnicode());
                graphic.setFont(control.getFont());
                graphic.fontProperty().bind(control.fontProperty());
            }
            button.setGraphic(graphic);
            button.setOnAction(event -> clickKeyboardButtonAction(letter));
            return button;
        }

        private void clickKeyboardButtonAction(ArabicLetterType letter) {
            ArabicLabelView selectedLabel = group.getSelectedLabel();
            if (selectedLabel != null) {
                selectedLabel.setLabel(letter);
                selectedLabel.setSelect(false);
            }

            currentIndex = (currentIndex + 1) % 4;
            currentView = labels[currentIndex];
            currentView.setSelect(true);
        }
    }
}
