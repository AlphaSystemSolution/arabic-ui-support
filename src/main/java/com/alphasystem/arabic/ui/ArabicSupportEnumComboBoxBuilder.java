package com.alphasystem.arabic.ui;

import com.alphasystem.arabic.model.ArabicSupportEnum;
import javafx.util.Builder;

import static com.alphasystem.arabic.ui.ListType.LABEL_AND_CODE;
import static java.lang.String.format;

/**
 * @author sali
 */
public class ArabicSupportEnumComboBoxBuilder implements Builder<ArabicSupportEnumComboBox> {

    private Class<?> clazz;
    private boolean required;
    private ListType type;

    public static ArabicSupportEnumComboBoxBuilder create() {
        return new ArabicSupportEnumComboBoxBuilder();
    }

    private Class<?> getClazz() {
        if (clazz == null) {
            clazz = Object.class;
        }
        return clazz;
    }

    public void clazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public void required(boolean required) {
        this.required = required;
    }

    private ListType getType() {
        if (type == null) {
            type = LABEL_AND_CODE;
        }
        return type;
    }

    public void type(ListType type) {
        this.type = type;
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public ArabicSupportEnumComboBox build() {
        final Class<?> clazz = getClazz();
        if (!clazz.isEnum() || !ArabicSupportEnum.class.isAssignableFrom(clazz)) {
            throw new RuntimeException(format("Unsupported class {%s}", clazz.getName()));
        }
        return new ArabicSupportEnumComboBox(clazz, required, getType());
    }
}
