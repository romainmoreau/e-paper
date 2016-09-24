package fr.romainmoreau.epaper.client.common;

public enum Color {
	BLACK, DARK_GRAY, LIGHT_GRAY, WHITE;

	public static Color fromRgb(int rgb) {
		java.awt.Color color = new java.awt.Color(rgb);
		return Color.values()[(int) Math
				.floor((0.21 * color.getRed() + 0.72 * color.getGreen() + 0.07 * color.getBlue()) / 64d)];
	}
}
