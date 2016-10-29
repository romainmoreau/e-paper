package fr.romainmoreau.epaper.client.common.uart;

import fr.romainmoreau.epaper.client.api.DisplayDirection;

public class CoordinatesUART {
	public static DisplayDirection getDisplayDirection(byte[] response) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append((char) response[0]);
		return DisplayDirection.values()[Integer.parseInt(stringBuffer.toString())];
	}
}
