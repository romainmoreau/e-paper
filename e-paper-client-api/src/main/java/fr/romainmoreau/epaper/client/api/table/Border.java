package fr.romainmoreau.epaper.client.api.table;

import fr.romainmoreau.epaper.client.api.Color;

public class Border {
	private final int size;

	private final Color color;

	public Border(int size, Color color) {
		this.size = size;
		this.color = color;
	}

	public int getSize() {
		return size;
	}

	public Color getColor() {
		return color;
	}
}
