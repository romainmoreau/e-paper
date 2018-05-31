package fr.romainmoreau.epaper.client.jssc;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import fr.romainmoreau.epaper.client.common.uart.AbstractUARTEPaperClient;
import fr.romainmoreau.epaper.client.common.uart.command.Command;
import jssc.SerialPort;
import jssc.SerialPortException;

public class JsscEPaperClient extends AbstractUARTEPaperClient {
	private SerialPort serialPort;

	private byte[] response;

	public JsscEPaperClient(String portName, long timeout, long receiveTimeout) throws IOException {
		super(timeout, receiveTimeout);
		try {
			serialPort = new SerialPort(portName);
			serialPort.openPort();
			serialPort.setParams(SerialPort.BAUDRATE_115200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE, false, false);
			serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
		} catch (SerialPortException e) {
			throw new IOException(e);
		}
	}

	@Override
	public synchronized void close() throws IOException {
		try {
			serialPort.closePort();
		} catch (SerialPortException e) {
			throw new IOException(e);
		}
	}

	@Override
	protected byte[] getResponse() {
		return response;
	}

	@Override
	protected void sendCommand(Command command) throws IOException {
		try {
			response = null;
			serialPort.writeBytes(command.getFrame());
		} catch (SerialPortException e) {
			throw new IOException(e);
		}
	}

	@Override
	protected void waitForResponse(long timeout, long receiveTimeout) {
		try {
			LocalDateTime start = LocalDateTime.now();
			byte[] readBytes = serialPort.readBytes();
			while (readBytes == null) {
				wait(receiveTimeout);
				if (start.until(LocalDateTime.now(), ChronoUnit.MILLIS) >= timeout) {
					return;
				}
				readBytes = serialPort.readBytes();
			}
			List<Byte> responseByteList = new ArrayList<>();
			for (int i = 0; i < readBytes.length; i++) {
				responseByteList.add(readBytes[i]);
			}
			while (readBytes != null) {
				wait(receiveTimeout);
				if (start.until(LocalDateTime.now(), ChronoUnit.MILLIS) >= timeout) {
					return;
				}
				readBytes = serialPort.readBytes();
				if (readBytes != null) {
					for (int i = 0; i < readBytes.length; i++) {
						responseByteList.add(readBytes[i]);
					}
				}
			}
			byte[] responseBytes = new byte[responseByteList.size()];
			for (int i = 0; i < responseBytes.length; i++) {
				responseBytes[i] = responseByteList.get(i);
			}
			response = responseBytes;
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		} catch (SerialPortException e) {
			throw new RuntimeException(e);
		}
	}
}
