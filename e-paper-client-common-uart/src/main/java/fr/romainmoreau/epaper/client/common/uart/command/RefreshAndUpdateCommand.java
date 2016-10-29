package fr.romainmoreau.epaper.client.common.uart.command;

public class RefreshAndUpdateCommand extends Command {
	public RefreshAndUpdateCommand() {
		super(0x0A);
	}
}
