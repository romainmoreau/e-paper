package fr.romainmoreau.epaper.client.common;

import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import fr.romainmoreau.epaper.client.api.Color;
import fr.romainmoreau.epaper.client.api.DrawingColors;
import fr.romainmoreau.epaper.client.api.EPaperClient;
import fr.romainmoreau.epaper.client.api.EPaperException;
import fr.romainmoreau.epaper.client.api.FontSize;
import fr.romainmoreau.epaper.client.api.HorizontalAlignment;
import fr.romainmoreau.epaper.client.api.VerticalAlignment;
import fr.romainmoreau.epaper.client.api.table.CellContent;
import fr.romainmoreau.epaper.client.api.table.CellContentDrawer;
import fr.romainmoreau.epaper.client.api.table.Table;
import fr.romainmoreau.epaper.client.common.table.DrawableBorder;
import fr.romainmoreau.epaper.client.common.table.DrawableCell;
import fr.romainmoreau.epaper.client.common.table.DrawableTable;
import fr.romainmoreau.epaper.client.common.table.Tables;

public abstract class AbstractEPaperClient implements EPaperClient, CellContentDrawer {
	@Override
	public synchronized void drawImage(int x, int y, InputStream inputStream) throws IOException, EPaperException {
		Coordinates.validateCoordinates(x, y);
		Map<Color, List<Point>> colorPointsMap = Colors.getColorPointsMap(inputStream);
		for (Color color : Color.values()) {
			setDrawingColors(new DrawingColors(color, Color.WHITE));
			for (Point point : colorPointsMap.get(color)) {
				drawPoint(x + point.x, y + point.y);
			}
		}
	}

	@Override
	public synchronized void drawPadded(int x0, int y0, int x1, int y1, int leftPadding, int rightPadding,
			int topPadding, int bottomPadding, CellContent cellContent) throws IOException, EPaperException {
		int topLeftX = Coordinates.getTopLeftX(x0, x1);
		int topLeftY = Coordinates.getTopLeftY(y0, y1);
		int bottomRightX = Coordinates.getBottomRightX(x0, x1);
		int bottomRightY = Coordinates.getBottomRightY(y0, y1);
		cellContent.draw(topLeftX + leftPadding, topLeftY + topPadding, bottomRightX - rightPadding,
				bottomRightY - bottomPadding, this);
	}

	@Override
	public synchronized void drawText(int x0, int y0, int x1, int y1, Color textColor, Color backgroundColor,
			FontSize fontSize, int lineSpacing, HorizontalAlignment horizontalAlignment,
			VerticalAlignment verticalAlignment, String text) throws IOException, EPaperException {
		setDrawingColors(new DrawingColors(textColor, backgroundColor));
		displayText(x0, y0, x1, y1, fontSize, lineSpacing, horizontalAlignment, verticalAlignment, text);
	}

	@Override
	public synchronized void drawTable(int x0, int y0, int x1, int y1, Table table)
			throws IOException, EPaperException {
		Coordinates.validateCoordinates(x0, y0);
		Coordinates.validateCoordinates(x1, y1);
		int topLeftX = Coordinates.getTopLeftX(x0, x1);
		int topLeftY = Coordinates.getTopLeftY(y0, y1);
		int width = Coordinates.getBottomRightX(x0, x1) - topLeftX + 1;
		int height = Coordinates.getBottomRightY(y0, y1) - topLeftY + 1;
		Tables.validateTable(width, height, table);
		DrawableTable drawableTable = Tables.getDrawableTable(width, height, topLeftX, topLeftY, table);
		for (DrawableCell drawableCell : drawableTable.getDrawableCells()) {
			if (drawableCell.getBackgroundColor() != null) {
				setDrawingColors(
						new DrawingColors(drawableCell.getBackgroundColor(), drawableCell.getBackgroundColor()));
				fillRectangle(drawableCell.getX0(), drawableCell.getY0(), drawableCell.getX1(), drawableCell.getY1());
			}
			for (CellContent cellContent : drawableCell.getCellContents()) {
				cellContent.draw(drawableCell.getX0(), drawableCell.getY0(), drawableCell.getX1(), drawableCell.getY1(),
						this);
			}
		}
		for (Entry<Color, List<DrawableBorder>> colorDrawableBorderListEntry : drawableTable.getDrawableBorders()
				.stream().collect(Collectors.groupingBy(DrawableBorder::getColor)).entrySet()) {
			setDrawingColors(
					new DrawingColors(colorDrawableBorderListEntry.getKey(), colorDrawableBorderListEntry.getKey()));
			for (DrawableBorder drawableBorder : colorDrawableBorderListEntry.getValue()) {
				fillRectangle(drawableBorder.getX0(), drawableBorder.getY0(), drawableBorder.getX1(),
						drawableBorder.getY1());
			}
		}
	}

	@Override
	public synchronized void displayText(int x0, int y0, int x1, int y1, FontSize fontSize, int lineSpacing,
			HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment, String text)
			throws IOException, EPaperException {
		if (text == null || text.isEmpty()) {
			return;
		}
		Coordinates.validateCoordinates(x0, y0);
		Coordinates.validateCoordinates(x1, y1);
		Lines.validateFontSize(fontSize);
		Lines.validateSpacing(lineSpacing);
		Lines.validateHorizontalAlignment(horizontalAlignment);
		Lines.validateVerticalAlignment(verticalAlignment);
		Lines.validateAdvancedText(text);
		int topLeftX = Coordinates.getTopLeftX(x0, x1);
		int topLeftY = Coordinates.getTopLeftY(y0, y1);
		int width = Coordinates.getBottomRightX(x0, x1) - topLeftX + 1;
		int height = Coordinates.getBottomRightY(y0, y1) - topLeftY + 1;
		List<Line> lines = Lines.getLines(width, height, topLeftX, topLeftY, fontSize, lineSpacing, horizontalAlignment,
				verticalAlignment, text);
		setFontSize(fontSize);
		for (Line line : lines) {
			displayText(line.getX(), line.getY(), line.getText());
		}
	}
}
