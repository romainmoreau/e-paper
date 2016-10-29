package fr.romainmoreau.epaper.client.common;

import fr.romainmoreau.epaper.client.api.DisplayDirection;
import fr.romainmoreau.epaper.client.api.EPaperClient;
import fr.romainmoreau.epaper.client.api.EPaperValidationException;

public class Coordinates {
	public static int getTopLeftX(int x0, int x1) {
		return Math.min(x0, x1);
	}

	public static int getTopLeftY(int y0, int y1) {
		return Math.min(y0, y1);
	}

	public static int getBottomRightX(int x0, int x1) {
		return Math.max(x0, x1);
	}

	public static int getBottomRightY(int y0, int y1) {
		return Math.max(y0, y1);
	}

	public static void validateCoordinates(int x, int y) throws EPaperValidationException {
		if (x < 0 || x >= EPaperClient.WIDTH) {
			throw new EPaperValidationException("Invalid x: " + x);
		}
		if (y < 0 || y >= EPaperClient.HEIGHT) {
			throw new EPaperValidationException("Invalid y: " + y);
		}
	}

	public static void validateDisplayDirection(DisplayDirection displayDirection) throws EPaperValidationException {
		if (displayDirection == null) {
			throw new EPaperValidationException("Empty display direction");
		}
	}
}
