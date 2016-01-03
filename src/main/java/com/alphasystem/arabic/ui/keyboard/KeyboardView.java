package com.alphasystem.arabic.ui.keyboard;

import com.alphasystem.arabic.ui.keyboard.skin.KeyboardSkin;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Control;

import static com.alphasystem.arabic.ui.keyboard.KeyboardView.OutputType.UNICODE;
import static com.alphasystem.util.AppUtil.getResource;


/**
 * @author sali
 */
public class KeyboardView extends Control {

    private final ObjectProperty<Node> target = new SimpleObjectProperty<>(null, "target");
    private final ObjectProperty<OutputType> outputType = new SimpleObjectProperty<>(UNICODE, "outputType");

    public KeyboardView() {
        setOutputType(UNICODE);
        setSkin(new KeyboardSkin(this));
        getStyleClass().add("popup");
    }

    @Override
    public String getUserAgentStylesheet() {
        return getResource("arabic-ui-support.css").toExternalForm();
    }

    public final Node getTarget() {
        return target.get();
    }

    public final void setTarget(Node target) {
        this.target.set(target);
    }

    public final ObjectProperty<Node> targetProperty() {
        return target;
    }

    public final OutputType getOutputType() {
        OutputType outputType = this.outputType.get();
        return (outputType == null) ? UNICODE : outputType;
    }

    public final void setOutputType(OutputType outputType) {
        this.outputType.set((outputType == null) ? UNICODE : outputType);
    }

    public final ObjectProperty<OutputType> outputTypeProperty() {
        return outputType;
    }

    public final void initAccelerators() {
        ((KeyboardSkin) getSkin()).setAccelerators();
    }

    public final void shiftPressed() {
        ((KeyboardSkin) getSkin()).shiftPressed();
    }

    public enum OutputType {
        UNICODE, HTML;
    }
}
