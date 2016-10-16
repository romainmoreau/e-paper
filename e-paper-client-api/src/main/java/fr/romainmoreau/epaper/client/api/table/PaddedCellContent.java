package fr.romainmoreau.epaper.client.api.table;

import java.io.IOException;

import fr.romainmoreau.epaper.client.api.EPaperException;

public class PaddedCellContent implements CellContent {
	private final int padding;

	private final CellContent cellContent;

	public PaddedCellContent(int padding, CellContent cellContent) {
		this.padding = padding;
		this.cellContent = cellContent;
	}

	@Override
	public void draw(int x0, int y0, int x1, int y1, CellContentDrawer cellContentDrawer)
			throws IOException, EPaperException {
		cellContentDrawer.drawPadded(x0, y0, x1, y1, padding, cellContent);
	}
}
