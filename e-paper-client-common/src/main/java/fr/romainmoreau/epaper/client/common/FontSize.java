package fr.romainmoreau.epaper.client.common;

public enum FontSize {
	// TODO: calculate letter spacing for 48 and 64
	DOTS_32(32, 1), DOTS_48(48, 1), DOTS_64(64, 1);

	private final int height;

	private final int letterSpacing;

	public static FontSize getFontSize(byte[] response) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append((char) response[0]);
		return FontSize.values()[Integer.parseInt(stringBuffer.toString()) - 1];
	}

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
