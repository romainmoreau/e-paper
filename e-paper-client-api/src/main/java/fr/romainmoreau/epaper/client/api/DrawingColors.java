package fr.romainmoreau.epaper.client.api;

public class DrawingColors {
	private final Color foreground;

	private final Color background;

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
}
