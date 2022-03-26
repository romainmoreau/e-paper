package fr.romainmoreau.epaper.jaxb.api;

import java.io.IOException;

import fr.romainmoreau.epaper.client.api.EPaperClient;
import fr.romainmoreau.epaper.client.api.EPaperException;
import jakarta.xml.bind.annotation.XmlAttribute;

public class FillRectangle implements Command {
	private int x0;

	private int y0;

	private int x1;

	private int y1;

	@Override
	public void execute(EPaperClient ePaperClient) throws IOException, EPaperException {
		ePaperClient.fillRectangle(x0, y0, x1, y1);
	}

	@XmlAttribute(required = true)
	public int getX0() {
		return x0;
	}

	public void setX0(int x0) {
		this.x0 = x0;
	}

	@XmlAttribute(required = true)
	public int getY0() {
		return y0;
	}

	public void setY0(int y0) {
		this.y0 = y0;
	}

	@XmlAttribute(required = true)
	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	@XmlAttribute(required = true)
	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}
}
