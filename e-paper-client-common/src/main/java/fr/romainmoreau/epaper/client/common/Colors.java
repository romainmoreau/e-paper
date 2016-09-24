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

public class Colors {
	public static Map<Color, List<Point>> getColorPointsMap(InputStream inputStream) throws IOException {
		BufferedImage image = ImageIO.read(inputStream);
		Map<Color, List<Point>> points = new HashMap<>();
		for (Color color : Color.values()) {
			points.put(color, new ArrayList<>());
		}
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				points.get(Color.fromRgb(image.getRGB(x, y))).add(new Point(x, y));
			}
		}
		return points;
	}
}
