package fr.romainmoreau.epaper.client.common.table;

import java.util.List;

import fr.romainmoreau.epaper.client.api.Color;
import fr.romainmoreau.epaper.client.api.table.CellContent;

public class DrawableCell {
	private final int x0;

	private final int y0;

	private final int x1;

	private final int y1;

	private final Color backgroundColor;

	private final List<CellContent> cellContents;

	public DrawableCell(int x0, int y0, int x1, int y1, Color backgroundColor, List<CellContent> cellContents) {
		this.x0 = x0;
		this.y0 = y0;
		this.x1 = x1;
		this.y1 = y1;
		this.backgroundColor = backgroundColor;
		this.cellContents = cellContents;
	}

	public int getX0() {
		return x0;
	}

	public int getY0() {
		return y0;
	}

	public int getX1() {
		return x1;
	}

	public int getY1() {
		return y1;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public List<CellContent> getCellContents() {
		return cellContents;
	}
}
