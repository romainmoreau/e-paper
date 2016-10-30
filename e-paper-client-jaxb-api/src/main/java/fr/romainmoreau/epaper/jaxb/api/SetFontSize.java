package fr.romainmoreau.epaper.jaxb.api;

import java.io.IOException;

import javax.xml.bind.annotation.XmlAttribute;

import fr.romainmoreau.epaper.client.api.EPaperClient;
import fr.romainmoreau.epaper.client.api.EPaperException;
import fr.romainmoreau.epaper.client.api.FontSize;

public class SetFontSize implements Command {
	private FontSize fontSize;

	@Override
	public void execute(EPaperClient ePaperClient) throws IOException, EPaperException {
		ePaperClient.setFontSize(fontSize);
	}

	@XmlAttribute(required = true)
	public FontSize getFontSize() {
		return fontSize;
	}

	public void setFontSize(FontSize fontSize) {
		this.fontSize = fontSize;
	}
}
