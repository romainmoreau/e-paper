package fr.romainmoreau.epaper.client.common.uart.command;

public class HandshakeCommand extends Command {
	public HandshakeCommand() {
		super(0x00);
	}
}
