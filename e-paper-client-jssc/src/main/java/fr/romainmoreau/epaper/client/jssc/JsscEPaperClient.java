package fr.romainmoreau.epaper.client.jssc;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import fr.romainmoreau.epaper.client.api.Color;
import fr.romainmoreau.epaper.client.api.DrawingColors;
import fr.romainmoreau.epaper.client.api.EPaperClient;
import fr.romainmoreau.epaper.client.api.EPaperException;
import fr.romainmoreau.epaper.client.api.EPaperResponseException;
import fr.romainmoreau.epaper.client.api.command.ClearCommand;
import fr.romainmoreau.epaper.client.api.command.Command;
import fr.romainmoreau.epaper.client.api.command.DisplayTextCommand;
import fr.romainmoreau.epaper.client.api.command.DrawLineCommand;
import fr.romainmoreau.epaper.client.api.command.DrawPointCommand;
import fr.romainmoreau.epaper.client.api.command.FillRectangleCommand;
import fr.romainmoreau.epaper.client.api.command.GetDrawingColorsCommand;
import fr.romainmoreau.epaper.client.api.command.RefreshAndUpdateCommand;
import fr.romainmoreau.epaper.client.api.command.SetDrawingColorsCommand;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortException;

public class JsscEPaperClient implements EPaperClient {
	private SerialPort serialPort;

	private volatile byte[] response;

	public JsscEPaperClient(String portName) throws IOException {
		try {
			serialPort = new SerialPort(portName);
			serialPort.openPort();
			serialPort.setParams(SerialPort.BAUDRATE_115200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE, false, false);
			serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
			serialPort.addEventListener(this::serialEvent, SerialPort.MASK_RXCHAR);
		} catch (SerialPortException e) {
			throw new IOException(e);
		}
	}

	@Override
	public synchronized void drawImage(int x, int y, InputStream inputStream) throws IOException, EPaperException {
		Map<Color, List<Point>> colorPointsMap = getColorPointsMap(inputStream);
		for (Color color : Color.values()) {
			setDrawingColors(new DrawingColors(color, Color.WHITE));
			for (Point point : colorPointsMap.get(color)) {
				drawPoint(x + point.x, y + point.y);
			}
		}
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
		sendCommand(new DrawPointCommand(x, y));
		waitForResponse();
		checkResponseOK();
	}

	@Override
	public synchronized void drawLine(int x0, int y0, int x1, int y1) throws IOException, EPaperException {
		sendCommand(new DrawLineCommand(x0, y0, x1, y1));
		waitForResponse();
		checkResponseOK();
	}

	@Override
	public synchronized void fillRectangle(int x0, int y0, int x1, int y1) throws IOException, EPaperException {
		sendCommand(new FillRectangleCommand(x0, y0, x1, y1));
		waitForResponse();
		checkResponseOK();
	}

	@Override
	public synchronized void displayText(int x, int y, String text) throws IOException, EPaperException {
		sendCommand(new DisplayTextCommand(x, y, text));
		waitForResponse();
		checkResponseOK();
	}

	@Override
	public synchronized DrawingColors getDrawingColors() throws IOException, EPaperException {
		sendCommand(new GetDrawingColorsCommand());
		waitForResponse();
		try {
			return new DrawingColors(response);
		} catch (Exception e) {
			throw new EPaperResponseException(response);
		}
	}

	@Override
	public synchronized void setDrawingColors(DrawingColors drawingColors) throws IOException, EPaperException {
		sendCommand(new SetDrawingColorsCommand(drawingColors));
		waitForResponse();
		checkResponseOK();
	}

	@Override
	public synchronized void close() throws IOException {
		try {
			serialPort.closePort();
		} catch (SerialPortException e) {
			throw new IOException(e);
		}
	}

	private void sendCommand(Command command) throws IOException {
		try {
			serialPort.writeBytes(command.getFrame());
		} catch (SerialPortException e) {
			throw new IOException(e);
		}
	}

	private void waitForResponse() {
		try {
			wait();
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}

	private void checkResponseOK() throws EPaperException {
		if (!Arrays.equals(RESPONSE_OK, response)) {
			throw new EPaperResponseException(response);
		}
	}

	private Map<Color, List<Point>> getColorPointsMap(InputStream inputStream) throws IOException {
		BufferedImage image = ImageIO.read(inputStream);
		Map<Color, List<Point>> points = new HashMap<>();
		for (Color color : Color.values()) {
			points.put(color, new ArrayList<>());
		}
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				points.get(Color.fromRgb(image.getRGB(x, y))).add(new Point(x, y));
			}
		}
		return points;
	}

	private void serialEvent(SerialPortEvent serialPortEvent) {
		try {
			response = serialPort.readBytes(serialPortEvent.getEventValue());
			synchronized (JsscEPaperClient.this) {
				JsscEPaperClient.this.notify();
			}
		} catch (SerialPortException e) {
			throw new RuntimeException(e);
		}
	}
}
