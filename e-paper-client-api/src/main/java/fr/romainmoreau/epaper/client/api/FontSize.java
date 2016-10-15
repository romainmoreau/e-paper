package fr.romainmoreau.epaper.client.api;

public enum FontSize {
	DOTS_32(32, 1), DOTS_48(48, 1), DOTS_64(64, 1);

	private final int height;

	private final int letterSpacing;

	private FontSize(int height, int letterSpacing) {
		this.height = height;
		this.letterSpacing = letterSpacing;
	}

	public int getHeight() {
		return height;
	}

	public int getLetterSpacing() {
		return letterSpacing;
	}
}
