package fr.romainmoreau.epaper.jaxb.api.table;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

public class Padded implements CellContent {
	private int padding;

	private CellContent cellContent;

	@Override
	public fr.romainmoreau.epaper.client.api.table.CellContent toCellContent() {
		return new fr.romainmoreau.epaper.client.api.table.PaddedCellContent(padding, cellContent.toCellContent());
	}

	@XmlAttribute(required = true)
	public int getPadding() {
		return padding;
	}

	public void setPadding(int padding) {
		this.padding = padding;
	}

	@XmlElements({ @XmlElement(name = "padded", type = Padded.class), @XmlElement(name = "text", type = Text.class) })
	public CellContent getCellContent() {
		return cellContent;
	}

	public void setCellContent(CellContent cellContent) {
		this.cellContent = cellContent;
	}
}
