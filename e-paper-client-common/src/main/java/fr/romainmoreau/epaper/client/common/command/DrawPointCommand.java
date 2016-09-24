package fr.romainmoreau.epaper.client.common.command;

import java.nio.ByteBuffer;

public class DrawPointCommand extends Command {
	public DrawPointCommand(int x, int y) {
		this((short) x, (short) y);
	}

	public DrawPointCommand(short x, short y) {
		super(0x20, getParametersOrData(x, y));
	}

	private static byte[] getParametersOrData(short x, short y) {
		byte[] parametersOrData = new byte[4];
		ByteBuffer byteBuffer = ByteBuffer.wrap(parametersOrData);
		byteBuffer.putShort(x);
		byteBuffer.putShort(y);
		return parametersOrData;
	}
}
