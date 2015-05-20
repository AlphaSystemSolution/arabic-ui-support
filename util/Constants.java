/**
 * 
 */
package com.alphasystem.arabic.ui.util;

import static java.awt.Font.BOLD;
import static java.awt.Font.PLAIN;
import static java.lang.System.getProperty;

import java.awt.Font;

/**
 * @author sali
 * 
 */
public final class Constants {

	public static String ARABIC_FONT_NAME = getProperty("arabic-font-name",
			"Traditional Arabic");

	public static final Font ARABIC_FONT_PLAIN_16 = new Font(ARABIC_FONT_NAME,
			PLAIN, 16);

	public static final Font ARABIC_FONT_PLAIN_20 = new Font(ARABIC_FONT_NAME,
			PLAIN, 20);

	public static final Font ARABIC_FONT_BOLD_20 = new Font(ARABIC_FONT_NAME,
			BOLD, 20);

	public static final Font ARABIC_FONT_BOLD_24 = new Font(ARABIC_FONT_NAME,
			BOLD, 24);

	public static final Font ARABIC_FONT_BOLD_28 = new Font(ARABIC_FONT_NAME,
			BOLD, 28);

	public static final Font ARABIC_FONT_PLAIN_28 = new Font(ARABIC_FONT_NAME,
			PLAIN, 28);

	public static final Font ARABIC_FONT_PLAIN_72 = new Font(ARABIC_FONT_NAME,
			PLAIN, 72);
}
