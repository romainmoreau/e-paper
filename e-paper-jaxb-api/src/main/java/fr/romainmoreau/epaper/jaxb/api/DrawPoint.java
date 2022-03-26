package fr.romainmoreau.epaper.jaxb.api;

import java.io.IOException;

import fr.romainmoreau.epaper.client.api.EPaperClient;
import fr.romainmoreau.epaper.client.api.EPaperException;
import jakarta.xml.bind.annotation.XmlAttribute;

public class DrawPoint implements Command {
	private int x;

	private int y;

	@Override
	public void execute(EPaperClient ePaperClient) throws IOException, EPaperException {
		ePaperClient.drawPoint(x, y);
	}

	@XmlAttribute(required = true)
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	@XmlAttribute(required = true)
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
