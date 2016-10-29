package fr.romainmoreau.epaper.client.common.uart.command;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class DisplayTextCommand extends Command {
	public DisplayTextCommand(int x, int y, String text) {
		this((short) x, (short) y, text);
	}

	public DisplayTextCommand(short x, short y, String text) {
		super(0x30, getParametersOrData(x, y, text));
	}

	private static byte[] getParametersOrData(short x, short y, String text) {
		byte[] textBytes = text.getBytes(StandardCharsets.US_ASCII);
		byte[] parametersOrData = new byte[textBytes.length + 5];
		ByteBuffer byteBuffer = ByteBuffer.wrap(parametersOrData);
		byteBuffer.putShort(x);
		byteBuffer.putShort(y);
		byteBuffer.put(textBytes);
		byteBuffer.put((byte) 0x00);
		return parametersOrData;
	}
}
