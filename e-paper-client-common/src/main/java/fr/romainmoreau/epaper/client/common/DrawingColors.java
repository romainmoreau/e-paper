package fr.romainmoreau.epaper.client.common;

public class DrawingColors {
	private final Color foreground;

	private final Color background;

	public DrawingColors(byte[] response) {
		this(getColor(response[0]), getColor(response[1]));
	}

	private static Color getColor(byte value) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append((char) value);
		return Color.values()[Integer.parseInt(stringBuffer.toString())];
	}

	public DrawingColors(Color foreground, Color background) {
		this.foreground = foreground;
		this.background = background;
	}

	public Color getForeground() {
		return foreground;
	}

	public Color getBackground() {
		return background;
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
