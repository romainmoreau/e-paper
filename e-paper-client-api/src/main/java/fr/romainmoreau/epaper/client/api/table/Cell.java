package fr.romainmoreau.epaper.client.api.table;

public class Cell {
	private final int columnIndex;

	private final int rowIndex;

	private final int zIndex;

	private final CellContent cellContent;

	public Cell(int columnIndex, int rowIndex, int zIndex, CellContent cellContent) {
		this.columnIndex = columnIndex;
		this.rowIndex = rowIndex;
		this.zIndex = zIndex;
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

	public CellContent getCellContent() {
		return cellContent;
	}
}
