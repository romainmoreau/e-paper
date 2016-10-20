package fr.romainmoreau.epaper.client.api.table;

import java.io.IOException;

import fr.romainmoreau.epaper.client.api.EPaperException;
import fr.romainmoreau.epaper.client.api.FontSize;
import fr.romainmoreau.epaper.client.api.HorizontalAlignment;
import fr.romainmoreau.epaper.client.api.VerticalAlignment;

public interface CellContentDrawer {
	void drawPadded(int x0, int y0, int x1, int y1, int leftPadding, int rightPadding, int topPadding,
			int bottomPadding, CellContent cellContent) throws IOException, EPaperException;

	void drawText(int x0, int y0, int x1, int y1, FontSize fontSize, int lineSpacing,
			HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment, String text)
			throws IOException, EPaperException;
}
