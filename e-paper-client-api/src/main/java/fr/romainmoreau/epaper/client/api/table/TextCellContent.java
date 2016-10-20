package fr.romainmoreau.epaper.client.api.table;

import java.io.IOException;

import fr.romainmoreau.epaper.client.api.Color;
import fr.romainmoreau.epaper.client.api.EPaperException;
import fr.romainmoreau.epaper.client.api.FontSize;
import fr.romainmoreau.epaper.client.api.HorizontalAlignment;
import fr.romainmoreau.epaper.client.api.VerticalAlignment;

public class TextCellContent implements CellContent {
	private final Color textColor;

	private final Color backgroundColor;

	private final FontSize fontSize;

	private final int lineSpacing;

	private final HorizontalAlignment horizontalAlignment;

	private final VerticalAlignment verticalAlignment;

	private final String text;

	public TextCellContent(Color textColor, Color backgroundColor, FontSize fontSize, int lineSpacing,
			HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment, String text) {
		this.textColor = textColor;
		this.backgroundColor = backgroundColor;
		this.fontSize = fontSize;
		this.lineSpacing = lineSpacing;
		this.horizontalAlignment = horizontalAlignment;
		this.verticalAlignment = verticalAlignment;
		this.text = text;
	}

	@Override
	public void draw(int x0, int y0, int x1, int y1, CellContentDrawer cellContentDrawer)
			throws IOException, EPaperException {
		cellContentDrawer.drawText(x0, y0, x1, y1, textColor, backgroundColor, fontSize, lineSpacing,
				horizontalAlignment, verticalAlignment, text);
	}
}
