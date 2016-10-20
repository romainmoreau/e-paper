package fr.romainmoreau.epaper.client.api.table;

import fr.romainmoreau.epaper.client.api.Color;

public class Cell {
	private final int columnIndex;

	private final int rowIndex;

	private final int zIndex;

	private final Color backgroundColor;

	private final CellContent cellContent;

	public Cell(int columnIndex, int rowIndex, int zIndex, Color backgroundColor, CellContent cellContent) {
		this.columnIndex = columnIndex;
		this.rowIndex = rowIndex;
		this.zIndex = zIndex;
		this.backgroundColor = backgroundColor;
		this.cellContent = cellContent;
	}

	public int getColumnIndex() {
		return columnIndex;
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public int getZIndex() {
		return zIndex;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public CellContent getCellContent() {
		return cellContent;
	}
}
