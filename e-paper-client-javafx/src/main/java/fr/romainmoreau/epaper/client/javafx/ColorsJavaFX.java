package fr.romainmoreau.epaper.client.javafx;

import javafx.scene.paint.Color;

public class ColorsJavaFX {
	public static Color getColor(fr.romainmoreau.epaper.client.api.Color color) {
		switch (color) {
		case BLACK:
			return Color.BLACK;
		case DARK_GRAY:
			return Color.DARKGRAY;
		case LIGHT_GRAY:
			return Color.LIGHTGRAY;
		case WHITE:
			return Color.WHITE;
		default:
			throw new IllegalStateException("Unsupported color");
		}
	}
}
