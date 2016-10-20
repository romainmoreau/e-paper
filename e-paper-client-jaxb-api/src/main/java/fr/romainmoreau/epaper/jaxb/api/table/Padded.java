package fr.romainmoreau.epaper.jaxb.api.table;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

public class Padded implements CellContent {
	private int leftPadding;

	private int rightPadding;

	private int topPadding;

	private int bottomPadding;

	private CellContent cellContent;

	@Override
	public fr.romainmoreau.epaper.client.api.table.CellContent toCellContent() {
		return new fr.romainmoreau.epaper.client.api.table.PaddedCellContent(leftPadding, rightPadding, topPadding,
				bottomPadding, cellContent.toCellContent());
	}

	@XmlAttribute(required = true)
	public int getLeftPadding() {
		return leftPadding;
	}

	public void setLeftPadding(int leftPadding) {
		this.leftPadding = leftPadding;
	}

	@XmlAttribute(required = true)
	public int getRightPadding() {
		return rightPadding;
	}

	public void setRightPadding(int rightPadding) {
		this.rightPadding = rightPadding;
	}

	@XmlAttribute(required = true)
	public int getTopPadding() {
		return topPadding;
	}

	public void setTopPadding(int topPadding) {
		this.topPadding = topPadding;
	}

	@XmlAttribute(required = true)
	public int getBottomPadding() {
		return bottomPadding;
	}

	public void setBottomPadding(int bottomPadding) {
		this.bottomPadding = bottomPadding;
	}

	@XmlElements({ @XmlElement(name = "padded", type = Padded.class), @XmlElement(name = "text", type = Text.class) })
	public CellContent getCellContent() {
		return cellContent;
	}

	public void setCellContent(CellContent cellContent) {
		this.cellContent = cellContent;
	}
}
