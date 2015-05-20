/**
 * 
 */
package com.alphasystem.arabic.ui;

import static com.alphasystem.arabic.ui.util.Constants.ARABIC_FONT_BOLD_28;
import static com.alphasystem.arabic.ui.util.Constants.ARABIC_FONT_NAME;
import static java.awt.ComponentOrientation.RIGHT_TO_LEFT;
import static java.lang.String.format;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.alphasystem.arabic.model.ArabicSupportEnum;

/**
 * @author sali
 * 
 */
public class ArabicSupportCellRenderer extends JLabel implements
		TableCellRenderer {

	private static final long serialVersionUID = -8641356085859990990L;

	public ArabicSupportCellRenderer() {
		// MUST do this for background to show up.
		setOpaque(true);
		setFont(ARABIC_FONT_BOLD_28);
		setComponentOrientation(RIGHT_TO_LEFT);
		setHorizontalAlignment(CENTER);
		setHorizontalTextPosition(CENTER);
		setVerticalAlignment(CENTER);
		setVerticalTextPosition(CENTER);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
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
		Color foreground = table.getForeground();
		Color background = table.getBackground();
		if (isSelected) {
			foreground = table.getSelectionForeground();
			background = table.getSelectionBackground();
		}
		setForeground(foreground);
		setBackground(background);
		return this;
	}

}
