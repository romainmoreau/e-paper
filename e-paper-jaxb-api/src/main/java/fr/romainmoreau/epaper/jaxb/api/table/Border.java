package fr.romainmoreau.epaper.jaxb.api.table;

import fr.romainmoreau.epaper.client.api.Color;
import jakarta.xml.bind.annotation.XmlAttribute;

public class Border {
	private int size;

	private Color color;

	public fr.romainmoreau.epaper.client.api.table.Border toBorder() {
		return new fr.romainmoreau.epaper.client.api.table.Border(size, color);
	}

	@XmlAttribute(required = true)
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@XmlAttribute(required = true)
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
