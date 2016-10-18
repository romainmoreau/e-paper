package fr.romainmoreau.epaper.jaxb.api.table;

import javax.xml.bind.annotation.XmlAttribute;

public class Column {
	private double width;

	public fr.romainmoreau.epaper.client.api.table.Column toColumn() {
		return new fr.romainmoreau.epaper.client.api.table.Column(width);
	}

	@XmlAttribute(required = true)
	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}
}
