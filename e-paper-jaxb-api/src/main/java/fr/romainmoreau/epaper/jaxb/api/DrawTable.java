package fr.romainmoreau.epaper.jaxb.api;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import fr.romainmoreau.epaper.client.api.Color;
import fr.romainmoreau.epaper.client.api.EPaperClient;
import fr.romainmoreau.epaper.client.api.EPaperException;
import fr.romainmoreau.epaper.jaxb.api.table.Border;
import fr.romainmoreau.epaper.jaxb.api.table.Cell;
import fr.romainmoreau.epaper.jaxb.api.table.Column;
import fr.romainmoreau.epaper.jaxb.api.table.Row;

public class DrawTable implements Command {
	private int x0;

	private int y0;

	private int x1;

	private int y1;

	private List<Column> columns;

	private List<Border> verticalBorders;

	private List<Row> rows;

	private List<Border> horizontalBorders;

	private List<Cell> cells;

	private int borderSize;

	private Color borderColor;

	@Override
	public void execute(EPaperClient ePaperClient) throws IOException, EPaperException {
		ePaperClient.drawTable(x0, y0, x1, y1, toTable());
	}

	public fr.romainmoreau.epaper.client.api.table.Table toTable() {
		fr.romainmoreau.epaper.client.api.table.Table table = new fr.romainmoreau.epaper.client.api.table.Table();
		if (columns != null) {
			List<fr.romainmoreau.epaper.client.api.table.Column> columns = table.getColumns();
			this.columns.stream().map(Column::toColumn).forEach(columns::add);
		}
		if (verticalBorders != null) {
			List<fr.romainmoreau.epaper.client.api.table.Border> verticalBorders = table.getVerticalBorders();
			this.verticalBorders.stream().map(Border::toBorder).forEach(verticalBorders::add);
		}
		if (rows != null) {
			List<fr.romainmoreau.epaper.client.api.table.Row> rows = table.getRows();
			this.rows.stream().map(Row::toRow).forEach(rows::add);
		}
		if (horizontalBorders != null) {
			List<fr.romainmoreau.epaper.client.api.table.Border> horizontalBorders = table.getHorizontalBorders();
			this.horizontalBorders.stream().map(Border::toBorder).forEach(horizontalBorders::add);
		}
		if (cells != null) {
			List<fr.romainmoreau.epaper.client.api.table.Cell> cells = table.getCells();
			this.cells.stream().map(Cell::toCell).forEach(cells::add);
		}
		return table;
	}

	@XmlAttribute(required = true)
	public int getX0() {
		return x0;
	}

	public void setX0(int x0) {
		this.x0 = x0;
	}

	@XmlAttribute(required = true)
	public int getY0() {
		return y0;
	}

	public void setY0(int y0) {
		this.y0 = y0;
	}

	@XmlAttribute(required = true)
	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	@XmlAttribute(required = true)
	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	@XmlElement(name = "column")
	@XmlElementWrapper(name = "columns")
	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	@XmlElement(name = "border")
	@XmlElementWrapper(name = "verticalBorders")
	public List<Border> getVerticalBorders() {
		return verticalBorders;
	}

	public void setVerticalBorders(List<Border> verticalBorders) {
		this.verticalBorders = verticalBorders;
	}

	@XmlElement(name = "row")
	@XmlElementWrapper(name = "rows")
	public List<Row> getRows() {
		return rows;
	}

	public void setRows(List<Row> rows) {
		this.rows = rows;
	}

	@XmlElement(name = "border")
	@XmlElementWrapper(name = "horizontalBorders")
	public List<Border> getHorizontalBorders() {
		return horizontalBorders;
	}

	public void setHorizontalBorders(List<Border> horizontalBorders) {
		this.horizontalBorders = horizontalBorders;
	}

	@XmlElement(name = "cell")
	@XmlElementWrapper(name = "cells")
	public List<Cell> getCells() {
		return cells;
	}

	public void setCells(List<Cell> cells) {
		this.cells = cells;
	}

	@XmlAttribute(required = true)
	public int getBorderSize() {
		return borderSize;
	}

	public void setBorderSize(int borderSize) {
		this.borderSize = borderSize;
	}

	@XmlAttribute(required = true)
	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}
}
