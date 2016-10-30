package fr.romainmoreau.epaper.jaxb.api;

import java.io.IOException;

import javax.xml.bind.annotation.XmlAttribute;

import fr.romainmoreau.epaper.client.api.DisplayDirection;
import fr.romainmoreau.epaper.client.api.EPaperClient;
import fr.romainmoreau.epaper.client.api.EPaperException;

public class SetDisplayDirection implements Command {
	private DisplayDirection displayDirection;

	@Override
	public void execute(EPaperClient ePaperClient) throws IOException, EPaperException {
		ePaperClient.setDisplayDirection(displayDirection);
	}

	@XmlAttribute(required = true)
	public DisplayDirection getDisplayDirection() {
		return displayDirection;
	}

	public void setDisplayDirection(DisplayDirection displayDirection) {
		this.displayDirection = displayDirection;
	}
}
