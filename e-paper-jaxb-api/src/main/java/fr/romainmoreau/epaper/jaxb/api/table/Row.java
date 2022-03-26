package fr.romainmoreau.epaper.jaxb.api.table;

import jakarta.xml.bind.annotation.XmlAttribute;

public class Row {
	private double height;

	public fr.romainmoreau.epaper.client.api.table.Row toRow() {
		return new fr.romainmoreau.epaper.client.api.table.Row(height);
	}

	@XmlAttribute(required = true)
	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
}
