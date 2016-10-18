package fr.romainmoreau.epaper.jaxb.api;

import java.io.IOException;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

import fr.romainmoreau.epaper.client.api.EPaperClient;
import fr.romainmoreau.epaper.client.api.EPaperException;
import fr.romainmoreau.epaper.client.api.FontSize;
import fr.romainmoreau.epaper.client.api.HorizontalAlignment;
import fr.romainmoreau.epaper.client.api.VerticalAlignment;

public class DisplayTextRectangle implements Command {
	private int x0;

	private int y0;

	private int x1;

	private int y1;

	private FontSize fontSize;

	private int lineSpacing;

	private HorizontalAlignment horizontalAlignment;

	private VerticalAlignment verticalAlignment;

	private String text;

	@Override
	public void execute(EPaperClient ePaperClient) throws IOException, EPaperException {
		ePaperClient.displayText(x0, y0, x1, y1, fontSize, lineSpacing, horizontalAlignment, verticalAlignment, text);
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

	@XmlAttribute(required = true)
	public FontSize getFontSize() {
		return fontSize;
	}

	public void setFontSize(FontSize fontSize) {
		this.fontSize = fontSize;
	}

	@XmlAttribute(required = true)
	public int getLineSpacing() {
		return lineSpacing;
	}

	public void setLineSpacing(int lineSpacing) {
		this.lineSpacing = lineSpacing;
	}

	@XmlAttribute(required = true)
	public HorizontalAlignment getHorizontalAlignment() {
		return horizontalAlignment;
	}

	public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
		this.horizontalAlignment = horizontalAlignment;
	}

	@XmlAttribute(required = true)
	public VerticalAlignment getVerticalAlignment() {
		return verticalAlignment;
	}

	public void setVerticalAlignment(VerticalAlignment verticalAlignment) {
		this.verticalAlignment = verticalAlignment;
	}

	@XmlValue
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
