package fr.romainmoreau.epaper.client.common.uart.command;

public class ClearCommand extends Command {
	public ClearCommand() {
		super(0x2E);
	}
}
