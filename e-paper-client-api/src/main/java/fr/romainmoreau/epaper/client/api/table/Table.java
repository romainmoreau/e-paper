package fr.romainmoreau.epaper.client.api.table;

import java.util.ArrayList;
import java.util.List;

import fr.romainmoreau.epaper.client.api.Color;

public class Table {
	private final List<Column> columns;

	private final List<Row> rows;

	private final List<Cell> cells;

	private final int borderSize;

	private final Color borderColor;

	public Table(int borderSize, Color borderColor) {
		this.columns = new ArrayList<>();
		this.rows = new ArrayList<>();
		this.cells = new ArrayList<>();
		this.borderSize = borderSize;
		this.borderColor = borderColor;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public List<Row> getRows() {
		return rows;
	}

	public List<Cell> getCells() {
		return cells;
	}

	public int getBorderSize() {
		return borderSize;
	}

	public Color getBorderColor() {
		return borderColor;
	}
}
