package fr.romainmoreau.epaper.client.api;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public interface EPaperClient extends Closeable {
	static final byte[] RESPONSE_OK = "OK".getBytes(StandardCharsets.US_ASCII);

	void drawImage(int x, int y, InputStream inputStream) throws IOException, EPaperException;

	void refreshAndUpdate() throws IOException, EPaperException;

	void clear() throws IOException, EPaperException;

	void drawPoint(int x, int y) throws IOException, EPaperException;

	void drawLine(int x0, int y0, int x1, int y1) throws IOException, EPaperException;

	void displayText(int x, int y, String text) throws IOException, EPaperException;

	DrawingColors getDrawingColors() throws IOException, EPaperException;

	void setDrawingColors(DrawingColors drawingColors) throws IOException, EPaperException;
}
