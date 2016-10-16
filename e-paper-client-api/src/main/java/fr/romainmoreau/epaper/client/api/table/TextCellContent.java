package fr.romainmoreau.epaper.client.api.table;

import java.io.IOException;

import fr.romainmoreau.epaper.client.api.EPaperException;
import fr.romainmoreau.epaper.client.api.FontSize;
import fr.romainmoreau.epaper.client.api.HorizontalAlignment;
import fr.romainmoreau.epaper.client.api.VerticalAlignment;

public class TextCellContent implements CellContent {
	private final FontSize fontSize;

	private final int lineSpacing;

	private final HorizontalAlignment horizontalAlignment;

	private final VerticalAlignment verticalAlignment;

	private final String text;

	public TextCellContent(FontSize fontSize, int lineSpacing, HorizontalAlignment horizontalAlignment,
			VerticalAlignment verticalAlignment, String text) {
		this.fontSize = fontSize;
		this.lineSpacing = lineSpacing;
		this.horizontalAlignment = horizontalAlignment;
		this.verticalAlignment = verticalAlignment;
		this.text = text;
	}

	@Override
	public void draw(int x0, int y0, int x1, int y1, CellContentDrawer cellContentDrawer)
			throws IOException, EPaperException {
		cellContentDrawer.drawText(x0, y0, x1, y1, fontSize, lineSpacing, horizontalAlignment, verticalAlignment, text);
	}
}
