package fr.romainmoreau.epaper.client.api;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

import fr.romainmoreau.epaper.client.api.table.Table;

public interface EPaperClient extends Closeable {
	static final int WIDTH = 800;

	static final int HEIGHT = 600;

	void drawImage(int x, int y, InputStream inputStream) throws IOException, EPaperException;

	void refreshAndUpdate() throws IOException, EPaperException;

	void clear() throws IOException, EPaperException;

	void drawPoint(int x, int y) throws IOException, EPaperException;

	void drawLine(int x0, int y0, int x1, int y1) throws IOException, EPaperException;

	void drawRectangle(int x0, int y0, int x1, int y1) throws IOException, EPaperException;

	void drawTable(int x0, int y0, int x1, int y1, Table table) throws IOException, EPaperException;

	void fillRectangle(int x0, int y0, int x1, int y1) throws IOException, EPaperException;

	void displayText(int x, int y, String text) throws IOException, EPaperException;

	void displayText(int x0, int y0, int x1, int y1, FontSize fontSize, int lineSpacing,
			HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment, String text)
			throws IOException, EPaperException;

	DrawingColors getDrawingColors() throws IOException, EPaperException;

	void setDrawingColors(DrawingColors drawingColors) throws IOException, EPaperException;

	FontSize getFontSize() throws IOException, EPaperException;

	void setFontSize(FontSize fontSize) throws IOException, EPaperException;

	void setDisplayDirection(DisplayDirection displayDirection) throws IOException, EPaperException;

	DisplayDirection getDisplayDirection() throws IOException, EPaperException;
}
