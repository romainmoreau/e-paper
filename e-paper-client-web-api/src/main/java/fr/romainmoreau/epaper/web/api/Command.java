package fr.romainmoreau.epaper.web.api;

import java.io.IOException;

import fr.romainmoreau.epaper.client.common.EPaperClient;
import fr.romainmoreau.epaper.client.common.EPaperException;

public interface Command {
	void execute(EPaperClient ePaperClient) throws IOException, EPaperException;
}
