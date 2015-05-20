/**
 * 
 */
package com.alphasystem.arabic.ui;

import static com.alphasystem.arabic.ui.util.Constants.ARABIC_FONT_NAME;
import static java.awt.ComponentOrientation.RIGHT_TO_LEFT;
import static java.lang.String.format;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.alphasystem.arabic.model.ArabicSupportEnum;

/**
 * @author sali
 * 
 */
public class ArabicSupportListCellRenderer extends JLabel implements
		ListCellRenderer {

	private static final long serialVersionUID = -4068753602998119446L;

	public ArabicSupportListCellRenderer() {
		setOpaque(true);
		setComponentOrientation(RIGHT_TO_LEFT);
		setHorizontalAlignment(CENTER);
		setVerticalAlignment(CENTER);
		setHorizontalTextPosition(CENTER);
		setVerticalTextPosition(CENTER);
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		String text = "";
		if (value != null
				&& ArabicSupportEnum.class.isAssignableFrom(value.getClass())) {
			ArabicSupportEnum ase = (ArabicSupportEnum) value;

			String englishPart = format(
					"<span style=\"font-family:Candara; font-size:14\"> (%s) </span>",
					ase.getCode());
			String arabicPart = format(
					"<span style=\"font-family:%s; font-size: 20; font-weight: bold;\">%s</span>",
					ARABIC_FONT_NAME, ase.getLabel().toUnicode());
			text = format(
					"<html><body><div><nobr>%s%s</nobr></div></body></html>",
					arabicPart, englishPart);
		}

		setText(text);
		if (isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		} else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}
		return this;
	}

}
