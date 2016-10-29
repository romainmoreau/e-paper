package fr.romainmoreau.epaper.client.common.uart.command;

public class GetFontSizeCommand extends Command {
	public GetFontSizeCommand() {
		super(0x1C);
	}
}
