package fr.romainmoreau.epaper.client.common.uart;

import fr.romainmoreau.epaper.client.api.Color;
import fr.romainmoreau.epaper.client.api.DrawingColors;

public class ColorsUART {
	public static DrawingColors getDrawingColors(byte[] response) {
		return new DrawingColors(getColor(response[0]), getColor(response[1]));
	}

	private static Color getColor(byte value) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append((char) value);
		return Color.values()[Integer.parseInt(stringBuffer.toString())];
	}
}
