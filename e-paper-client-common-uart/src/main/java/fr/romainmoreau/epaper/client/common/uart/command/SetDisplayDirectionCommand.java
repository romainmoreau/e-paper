package fr.romainmoreau.epaper.client.common.uart.command;

import java.nio.ByteBuffer;

import fr.romainmoreau.epaper.client.api.DisplayDirection;

public class SetDisplayDirectionCommand extends Command {
	public SetDisplayDirectionCommand(DisplayDirection displayDirection) {
		super(0x0D, getParametersOrData(displayDirection));
	}

	private static byte[] getParametersOrData(DisplayDirection displayDirection) {
		byte[] parametersOrData = new byte[1];
		ByteBuffer byteBuffer = ByteBuffer.wrap(parametersOrData);
		byteBuffer.put((byte) displayDirection.ordinal());
		return parametersOrData;
	}
}
