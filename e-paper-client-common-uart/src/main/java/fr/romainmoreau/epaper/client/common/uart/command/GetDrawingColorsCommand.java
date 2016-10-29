package fr.romainmoreau.epaper.client.common.uart.command;

public class GetDrawingColorsCommand extends Command {
	public GetDrawingColorsCommand() {
		super(0x11);
	}
}
