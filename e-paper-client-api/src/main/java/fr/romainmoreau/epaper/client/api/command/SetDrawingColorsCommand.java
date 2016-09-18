package fr.romainmoreau.epaper.client.api.command;

import java.nio.ByteBuffer;

import fr.romainmoreau.epaper.client.api.DrawingColors;

public class SetDrawingColorsCommand extends Command {
	public SetDrawingColorsCommand(DrawingColors drawingColors) {
		super(0x10, getParametersOrData(drawingColors));
	}

	private static byte[] getParametersOrData(DrawingColors drawingColors) {
		byte[] parametersOrData = new byte[2];
		ByteBuffer byteBuffer = ByteBuffer.wrap(parametersOrData);
		byteBuffer.put((byte) drawingColors.getForeground().ordinal());
		byteBuffer.put((byte) drawingColors.getBackground().ordinal());
		return parametersOrData;
	}
}
