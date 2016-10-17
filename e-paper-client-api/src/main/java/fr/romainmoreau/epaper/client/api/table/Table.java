package fr.romainmoreau.epaper.client.api.table;

import java.util.ArrayList;
import java.util.List;

public class Table {
	private final List<Column> columns;

	private final List<Border> verticalBorders;

	private final List<Row> rows;

	private final List<Border> horizontalBorders;

	private final List<Cell> cells;

	public Table() {
		this.columns = new ArrayList<>();
		this.verticalBorders = new ArrayList<>();
		this.rows = new ArrayList<>();
		this.horizontalBorders = new ArrayList<>();
		this.cells = new ArrayList<>();
	}

	public List<Column> getColumns() {
		return columns;
	}

	public List<Border> getVerticalBorders() {
		return verticalBorders;
	}

	public List<Row> getRows() {
		return rows;
	}

	public List<Border> getHorizontalBorders() {
		return horizontalBorders;
	}

	public List<Cell> getCells() {
		return cells;
	}
}
