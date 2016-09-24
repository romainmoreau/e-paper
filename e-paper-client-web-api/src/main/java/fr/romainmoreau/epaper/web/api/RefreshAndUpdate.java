package fr.romainmoreau.epaper.web.api;

import java.io.IOException;

import fr.romainmoreau.epaper.client.common.EPaperClient;
import fr.romainmoreau.epaper.client.common.EPaperException;

public class RefreshAndUpdate implements Command {
	@Override
	public void execute(EPaperClient ePaperClient) throws IOException, EPaperException {
		ePaperClient.refreshAndUpdate();
	}
}
