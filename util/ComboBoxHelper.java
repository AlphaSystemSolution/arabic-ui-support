/**
 * 
 */
package com.alphasystem.arabic.ui.util;

import static org.apache.commons.lang3.ArrayUtils.isEmpty;

import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import com.alphasystem.arabic.model.ArabicSupportEnum;
import com.alphasystem.arabic.ui.ArabicSupportListCellRenderer;

/**
 * @author sali
 * 
 */
public final class ComboBoxHelper {

	public static JComboBox getComboBox(DefaultComboBoxModel model) {
		JComboBox comboBox = new JComboBox(model);
		comboBox.setRenderer(new ArabicSupportListCellRenderer());
		return comboBox;
	}

	public static <T extends ArabicSupportEnum> DefaultComboBoxModel getComboBoxModel(
			T[] values, boolean allowEmptyValue) {
		Vector<T> vector = new Vector<T>();
		if (allowEmptyValue) {
			vector.add(null);
		}

		if (!isEmpty(values)) {
			for (T value : values) {
				vector.add(value);
			}
		}

		return new DefaultComboBoxModel(vector);
	}

	public static <T extends ArabicSupportEnum> T getSelectedValue(
			Class<T> enumClass, JComboBox comboBox) {
		DefaultComboBoxModel model = (DefaultComboBoxModel) comboBox.getModel();
		@SuppressWarnings("unchecked")
		T selectedItem = (T) model.getSelectedItem();
		return selectedItem;
	}
}
