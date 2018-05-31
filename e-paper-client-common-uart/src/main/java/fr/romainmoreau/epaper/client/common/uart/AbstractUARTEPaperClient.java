package fr.romainmoreau.epaper.client.common.uart;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import fr.romainmoreau.epaper.client.api.DisplayDirection;
import fr.romainmoreau.epaper.client.api.DrawingColors;
import fr.romainmoreau.epaper.client.api.EPaperException;
import fr.romainmoreau.epaper.client.api.EPaperResponseException;
import fr.romainmoreau.epaper.client.api.FontSize;
import fr.romainmoreau.epaper.client.common.AbstractEPaperClient;
import fr.romainmoreau.epaper.client.common.Colors;
import fr.romainmoreau.epaper.client.common.Coordinates;
import fr.romainmoreau.epaper.client.common.Lines;
import fr.romainmoreau.epaper.client.common.uart.command.ClearCommand;
import fr.romainmoreau.epaper.client.common.uart.command.Command;
import fr.romainmoreau.epaper.client.common.uart.command.DisplayTextCommand;
import fr.romainmoreau.epaper.client.common.uart.command.DrawLineCommand;
import fr.romainmoreau.epaper.client.common.uart.command.DrawPointCommand;
import fr.romainmoreau.epaper.client.common.uart.command.DrawRectangleCommand;
import fr.romainmoreau.epaper.client.common.uart.command.FillRectangleCommand;
import fr.romainmoreau.epaper.client.common.uart.command.GetDisplayDirectionCommand;
import fr.romainmoreau.epaper.client.common.uart.command.GetDrawingColorsCommand;
import fr.romainmoreau.epaper.client.common.uart.command.GetFontSizeCommand;
import fr.romainmoreau.epaper.client.common.uart.command.RefreshAndUpdateCommand;
import fr.romainmoreau.epaper.client.common.uart.command.SetDisplayDirectionCommand;
import fr.romainmoreau.epaper.client.common.uart.command.SetDrawingColorsCommand;
import fr.romainmoreau.epaper.client.common.uart.command.SetFontSizeCommand;

public abstract class AbstractUARTEPaperClient extends AbstractEPaperClient {
	private static final byte[] RESPONSE_OK = "OK".getBytes(StandardCharsets.US_ASCII);

	private final long timeout;

	private final long receiveTimeout;

	public AbstractUARTEPaperClient(long timeout, long receiveTimeout) {
		this.timeout = timeout;
		this.receiveTimeout = receiveTimeout;
	}

	@Override
	public synchronized void refreshAndUpdate() throws IOException, EPaperException {
		sendCommand(new RefreshAndUpdateCommand());
		waitForResponse();
		checkResponseOK();
	}

	@Override
	public synchronized void clear() throws IOException, EPaperException {
		sendCommand(new ClearCommand());
		waitForResponse();
		checkResponseOK();
	}

	@Override
	public synchronized void drawPoint(int x, int y) throws IOException, EPaperException {
		Coordinates.validateCoordinates(x, y);
		sendCommand(new DrawPointCommand(x, y));
		waitForResponse();
		checkResponseOK();
	}

	@Override
	public synchronized void drawLine(int x0, int y0, int x1, int y1) throws IOException, EPaperException {
		Coordinates.validateCoordinates(x0, y0);
		Coordinates.validateCoordinates(x1, y1);
		sendCommand(new DrawLineCommand(x0, y0, x1, y1));
		waitForResponse();
		checkResponseOK();
	}

	@Override
	public synchronized void drawRectangle(int x0, int y0, int x1, int y1) throws IOException, EPaperException {
		Coordinates.validateCoordinates(x0, y0);
		Coordinates.validateCoordinates(x1, y1);
		sendCommand(new DrawRectangleCommand(x0, y0, x1, y1));
		waitForResponse();
		checkResponseOK();
	}

	@Override
	public synchronized void fillRectangle(int x0, int y0, int x1, int y1) throws IOException, EPaperException {
		Coordinates.validateCoordinates(x0, y0);
		Coordinates.validateCoordinates(x1, y1);
		sendCommand(new FillRectangleCommand(x0, y0, x1, y1));
		waitForResponse();
		checkResponseOK();
	}

	@Override
	public synchronized void displayText(int x, int y, String text) throws IOException, EPaperException {
		Coordinates.validateCoordinates(x, y);
		Lines.validateText(text);
		sendCommand(new DisplayTextCommand(x, y, text));
		waitForResponse();
		checkResponseOK();
	}

	@Override
	public synchronized DrawingColors getDrawingColors() throws IOException, EPaperException {
		sendCommand(new GetDrawingColorsCommand());
		waitForResponse();
		checkResponsePresent();
		try {
			return ColorsUART.getDrawingColors(getResponse());
		} catch (Exception e) {
			throw new EPaperResponseException(getResponse(), e);
		}

	}

	@Override
	public synchronized void setDrawingColors(DrawingColors drawingColors) throws IOException, EPaperException {
		Colors.validateDrawingColors(drawingColors);
		sendCommand(new SetDrawingColorsCommand(drawingColors));
		waitForResponse();
		checkResponseOK();
	}

	@Override
	public synchronized FontSize getFontSize() throws IOException, EPaperException {
		sendCommand(new GetFontSizeCommand());
		waitForResponse();
		checkResponsePresent();
		try {
			return Lines.getFontSize(getResponse());
		} catch (Exception e) {
			throw new EPaperResponseException(getResponse(), e);
		}

	}

	@Override
	public synchronized void setFontSize(FontSize fontSize) throws IOException, EPaperException {
		Lines.validateFontSize(fontSize);
		sendCommand(new SetFontSizeCommand(fontSize));
		waitForResponse();
		checkResponseOK();
	}

	@Override
	public synchronized void setDisplayDirection(DisplayDirection displayDirection)
			throws IOException, EPaperException {
		Coordinates.validateDisplayDirection(displayDirection);
		sendCommand(new SetDisplayDirectionCommand(displayDirection));
		waitForResponse();
		checkResponseOK();
	}

	@Override
	public synchronized DisplayDirection getDisplayDirection() throws IOException, EPaperException {
		sendCommand(new GetDisplayDirectionCommand());
		waitForResponse();
		checkResponsePresent();
		try {
			return CoordinatesUART.getDisplayDirection(getResponse());
		} catch (Exception e) {
			throw new EPaperResponseException(getResponse(), e);
		}

	}

	public long getTimeout() {
		return timeout;
	}

	protected abstract byte[] getResponse();

	protected abstract void sendCommand(Command command) throws IOException;

	protected abstract void waitForResponse(long timeout, long receiveTimeout);

	private void waitForResponse() {
		waitForResponse(timeout, receiveTimeout);
	}

	private void checkResponsePresent() throws EPaperResponseException {
		if (getResponse() == null) {
			throw new EPaperResponseException("No response");
		}
	}

	private void checkResponseOK() throws EPaperResponseException {
		checkResponsePresent();
		if (!Arrays.equals(RESPONSE_OK, getResponse())) {
			throw new EPaperResponseException(getResponse());
		}
	}
}
