package fr.romainmoreau.epaper.client.common.uart.command;

import java.nio.ByteBuffer;

public class DrawLineCommand extends Command {
	public DrawLineCommand(int x0, int y0, int x1, int y1) {
		this((short) x0, (short) y0, (short) x1, (short) y1);
	}

	public DrawLineCommand(short x0, short y0, short x1, short y1) {
		super(0x22, getParametersOrData(x0, y0, x1, y1));
	}

	private static byte[] getParametersOrData(short x0, short y0, short x1, short y1) {
		byte[] parametersOrData = new byte[8];
		ByteBuffer byteBuffer = ByteBuffer.wrap(parametersOrData);
		byteBuffer.putShort(x0);
		byteBuffer.putShort(y0);
		byteBuffer.putShort(x1);
		byteBuffer.putShort(y1);
		return parametersOrData;
	}
}
