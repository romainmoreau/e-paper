package fr.romainmoreau.epaper.client.web;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.springframework.web.client.RestTemplate;

import fr.romainmoreau.epaper.client.api.DisplayDirection;
import fr.romainmoreau.epaper.client.api.DrawingColors;
import fr.romainmoreau.epaper.client.api.EPaperException;
import fr.romainmoreau.epaper.client.api.FontSize;
import fr.romainmoreau.epaper.client.common.AbstractEPaperClient;
import fr.romainmoreau.epaper.client.common.Colors;
import fr.romainmoreau.epaper.client.common.Coordinates;
import fr.romainmoreau.epaper.client.common.Lines;
import fr.romainmoreau.epaper.jaxb.api.Clear;
import fr.romainmoreau.epaper.jaxb.api.Command;
import fr.romainmoreau.epaper.jaxb.api.Commands;
import fr.romainmoreau.epaper.jaxb.api.DisplayText;
import fr.romainmoreau.epaper.jaxb.api.DrawLine;
import fr.romainmoreau.epaper.jaxb.api.DrawPoint;
import fr.romainmoreau.epaper.jaxb.api.DrawRectangle;
import fr.romainmoreau.epaper.jaxb.api.FillRectangle;
import fr.romainmoreau.epaper.jaxb.api.RefreshAndUpdate;
import fr.romainmoreau.epaper.jaxb.api.SetDisplayDirection;
import fr.romainmoreau.epaper.jaxb.api.SetDrawingColors;
import fr.romainmoreau.epaper.jaxb.api.SetFontSize;

public class WebEPaperClient extends AbstractEPaperClient {
	private final RestTemplate restTemplate;

	private final URI uri;

	public WebEPaperClient(String protocol, String host, int port) throws MalformedURLException, URISyntaxException {
		restTemplate = new RestTemplate();
		uri = new URI(protocol, null, host, port, "/", null, null);
	}

	@Override
	public synchronized void clear() throws IOException, EPaperException {
		sendCommand(new Clear());
	}

	@Override
	public synchronized void close() throws IOException {
	}

	@Override
	public synchronized void refreshAndUpdate() throws IOException, EPaperException {
		sendCommand(new RefreshAndUpdate());
	}

	@Override
	public synchronized void drawPoint(int x, int y) throws IOException, EPaperException {
		Coordinates.validateCoordinates(x, y);
		DrawPoint drawPoint = new DrawPoint();
		drawPoint.setX(x);
		drawPoint.setY(y);
		sendCommand(drawPoint);
	}

	@Override
	public synchronized void drawLine(int x0, int y0, int x1, int y1) throws IOException, EPaperException {
		Coordinates.validateCoordinates(x0, y0);
		Coordinates.validateCoordinates(x1, y1);
		DrawLine drawLine = new DrawLine();
		drawLine.setX0(x0);
		drawLine.setX1(x1);
		drawLine.setY0(y0);
		drawLine.setY1(y1);
		sendCommand(drawLine);
	}

	@Override
	public synchronized void drawRectangle(int x0, int y0, int x1, int y1) throws IOException, EPaperException {
		Coordinates.validateCoordinates(x0, y0);
		Coordinates.validateCoordinates(x1, y1);
		DrawRectangle drawRectangle = new DrawRectangle();
		drawRectangle.setX0(x0);
		drawRectangle.setX1(x1);
		drawRectangle.setY0(y0);
		drawRectangle.setY1(y1);
		sendCommand(drawRectangle);
	}

	@Override
	public synchronized void fillRectangle(int x0, int y0, int x1, int y1) throws IOException, EPaperException {
		Coordinates.validateCoordinates(x0, y0);
		Coordinates.validateCoordinates(x1, y1);
		FillRectangle fillRectangle = new FillRectangle();
		fillRectangle.setX0(x0);
		fillRectangle.setX1(x1);
		fillRectangle.setY0(y0);
		fillRectangle.setY1(y1);
		sendCommand(fillRectangle);
	}

	@Override
	public synchronized void displayText(int x, int y, String text) throws IOException, EPaperException {
		Coordinates.validateCoordinates(x, y);
		Lines.validateText(text);
		DisplayText displayText = new DisplayText();
		displayText.setX(x);
		displayText.setY(y);
		displayText.setText(text);
		sendCommand(displayText);
	}

	@Override
	public synchronized DrawingColors getDrawingColors() throws IOException, EPaperException {
		throw new IllegalStateException("Not supported");
	}

	@Override
	public synchronized void setDrawingColors(DrawingColors drawingColors) throws IOException, EPaperException {
		Colors.validateDrawingColors(drawingColors);
		SetDrawingColors setDrawingColors = new SetDrawingColors();
		setDrawingColors.setBackground(drawingColors.getBackground());
		setDrawingColors.setForeground(drawingColors.getForeground());
		sendCommand(setDrawingColors);
	}

	@Override
	public synchronized FontSize getFontSize() throws IOException, EPaperException {
		throw new IllegalStateException("Not supported");
	}

	@Override
	public synchronized void setFontSize(FontSize fontSize) throws IOException, EPaperException {
		Lines.validateFontSize(fontSize);
		SetFontSize setFontSize = new SetFontSize();
		setFontSize.setFontSize(fontSize);
		sendCommand(setFontSize);
	}

	@Override
	public synchronized void setDisplayDirection(DisplayDirection displayDirection)
			throws IOException, EPaperException {
		Coordinates.validateDisplayDirection(displayDirection);
		SetDisplayDirection setDisplayDirection = new SetDisplayDirection();
		setDisplayDirection.setDisplayDirection(displayDirection);
		sendCommand(setDisplayDirection);
	}

	@Override
	public synchronized DisplayDirection getDisplayDirection() throws IOException, EPaperException {
		throw new IllegalStateException("Not supported");
	}

	private void sendCommand(Command command) {
		Commands commands = new Commands();
		commands.setCommands(Arrays.asList(command));
		sendCommands(commands);
	}

	private void sendCommands(Commands commands) {
		restTemplate.postForObject(uri, commands, String.class);
	}
}
