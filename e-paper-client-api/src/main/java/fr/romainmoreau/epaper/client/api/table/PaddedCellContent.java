package fr.romainmoreau.epaper.client.api.table;

import java.io.IOException;

import fr.romainmoreau.epaper.client.api.EPaperException;

public class PaddedCellContent implements CellContent {
	private final int leftPadding;

	private final int rightPadding;

	private final int topPadding;

	private final int bottomPadding;

	private final CellContent cellContent;

	public PaddedCellContent(int leftPadding, int rightPadding, int topPadding, int bottomPadding,
			CellContent cellContent) {
		this.leftPadding = leftPadding;
		this.rightPadding = rightPadding;
		this.topPadding = topPadding;
		this.bottomPadding = bottomPadding;
		this.cellContent = cellContent;
	}

	@Override
	public void draw(int x0, int y0, int x1, int y1, CellContentDrawer cellContentDrawer)
			throws IOException, EPaperException {
		cellContentDrawer.drawPadded(x0, y0, x1, y1, leftPadding, rightPadding, topPadding, bottomPadding, cellContent);
	}
}
