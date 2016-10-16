package fr.romainmoreau.epaper.client.common.table;

import java.util.ArrayList;
import java.util.Collection;

public class DrawableTable {
	private final Collection<DrawableBorder> drawableBorders;

	private final Collection<DrawableCell> drawableCells;

	public DrawableTable() {
		this.drawableBorders = new ArrayList<>();
		this.drawableCells = new ArrayList<>();
	}

	public Collection<DrawableBorder> getDrawableBorders() {
		return drawableBorders;
	}

	public Collection<DrawableCell> getDrawableCells() {
		return drawableCells;
	}
}
