package fr.romainmoreau.epaper.client.common.uart.command;

public class GetBaudRateCommand extends Command {
	public GetBaudRateCommand() {
		super(0x02);
	}
}
