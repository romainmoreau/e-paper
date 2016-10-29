package fr.romainmoreau.epaper.client.common;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import fr.romainmoreau.epaper.client.api.Color;
import fr.romainmoreau.epaper.client.api.DrawingColors;
import fr.romainmoreau.epaper.client.api.EPaperValidationException;

public class Colors {
	public static Color fromRgb(int rgb) {
		java.awt.Color color = new java.awt.Color(rgb);
		return Color.values()[(int) Math
				.floor((0.21 * color.getRed() + 0.72 * color.getGreen() + 0.07 * color.getBlue()) / 64d)];
	}

	public static Map<Color, List<Point>> getColorPointsMap(InputStream inputStream) throws IOException {
		BufferedImage image = ImageIO.read(inputStream);
		Map<Color, List<Point>> points = new HashMap<>();
		for (Color color : Color.values()) {
			points.put(color, new ArrayList<>());
		}
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				points.get(Colors.fromRgb(image.getRGB(x, y))).add(new Point(x, y));
			}
		}
		return points;
	}

	public static void validateDrawingColors(DrawingColors drawingColors) throws EPaperValidationException {
		if (drawingColors == null) {
			throw new EPaperValidationException("Drawing colors null");
		}
		if (drawingColors.getBackground() == null) {
			throw new EPaperValidationException("Background drawing color null");
		}
		if (drawingColors.getForeground() == null) {
			throw new EPaperValidationException("Foreground drawing color null");
		}
	}
}
