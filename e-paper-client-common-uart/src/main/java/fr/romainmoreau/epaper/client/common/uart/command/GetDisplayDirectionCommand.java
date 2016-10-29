package fr.romainmoreau.epaper.client.common.uart.command;

public class GetDisplayDirectionCommand extends Command {
	public GetDisplayDirectionCommand() {
		super(0x0C);
	}
}
