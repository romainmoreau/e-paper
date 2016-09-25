package fr.romainmoreau.epaper.client.common.command;

import java.nio.ByteBuffer;

import fr.romainmoreau.epaper.client.api.FontSize;

public class SetFontSizeCommand extends Command {
	public SetFontSizeCommand(FontSize fontSize) {
		super(0x1E, getParametersOrData(fontSize));
	}

	private static byte[] getParametersOrData(FontSize fontSize) {
		byte[] parametersOrData = new byte[1];
		ByteBuffer byteBuffer = ByteBuffer.wrap(parametersOrData);
		byteBuffer.put((byte) (fontSize.ordinal() + 1));
		return parametersOrData;
	}
}
