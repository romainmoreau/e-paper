package fr.romainmoreau.epaper.jaxb.api;

import java.io.IOException;

import fr.romainmoreau.epaper.client.api.EPaperClient;
import fr.romainmoreau.epaper.client.api.EPaperException;

public interface Command {
	void execute(EPaperClient ePaperClient) throws IOException, EPaperException;
}
