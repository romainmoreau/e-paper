package fr.romainmoreau.epaper.jaxb.api.table;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

import fr.romainmoreau.epaper.client.api.Color;

public class Cell {
	private int columnIndex;

	private int rowIndex;

	private int zIndex;

	private Color backgroundColor;

	private CellContent cellContent;

	public fr.romainmoreau.epaper.client.api.table.Cell toCell() {
		return new fr.romainmoreau.epaper.client.api.table.Cell(columnIndex, rowIndex, zIndex, backgroundColor,
				cellContent.toCellContent());
	}

	@XmlAttribute(required = true)
	public int getColumnIndex() {
		return columnIndex;
	}

	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}

	@XmlAttribute(required = true)
	public int getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	@XmlAttribute(required = true)
	public int getZIndex() {
		return zIndex;
	}

	public void setZIndex(int zIndex) {
		this.zIndex = zIndex;
	}

	@XmlAttribute(required = false)
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	@XmlElements({ @XmlElement(name = "padded", type = Padded.class), @XmlElement(name = "text", type = Text.class) })
	public CellContent getCellContent() {
		return cellContent;
	}

	public void setCellContent(CellContent cellContent) {
		this.cellContent = cellContent;
	}
}
