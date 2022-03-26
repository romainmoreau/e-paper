package fr.romainmoreau.epaper.jaxb.api;

import java.io.IOException;

import fr.romainmoreau.epaper.client.api.Color;
import fr.romainmoreau.epaper.client.api.DrawingColors;
import fr.romainmoreau.epaper.client.api.EPaperClient;
import fr.romainmoreau.epaper.client.api.EPaperException;
import jakarta.xml.bind.annotation.XmlAttribute;

public class SetDrawingColors implements Command {
	private Color foreground;

	private Color background;

	@Override
	public void execute(EPaperClient ePaperClient) throws IOException, EPaperException {
		ePaperClient.setDrawingColors(new DrawingColors(foreground, background));
	}

	@XmlAttribute(required = true)
	public Color getForeground() {
		return foreground;
	}

	public void setForeground(Color foreground) {
		this.foreground = foreground;
	}

	@XmlAttribute(required = true)
	public Color getBackground() {
		return background;
	}

	public void setBackground(Color background) {
		this.background = background;
	}
}
