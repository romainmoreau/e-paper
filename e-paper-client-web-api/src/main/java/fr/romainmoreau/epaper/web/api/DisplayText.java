package fr.romainmoreau.epaper.web.api;

import java.io.IOException;

import javax.xml.bind.annotation.XmlAttribute;

import fr.romainmoreau.epaper.client.api.EPaperClient;
import fr.romainmoreau.epaper.client.api.EPaperException;

public class DisplayText implements Command {
	private int x;

	private int y;

	private String text;

	@Override
	public void execute(EPaperClient ePaperClient) throws IOException, EPaperException {
		ePaperClient.displayText(x, y, text);
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

	@XmlAttribute(required = true)
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
