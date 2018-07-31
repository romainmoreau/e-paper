package fr.romainmoreau.epaper.client.jserialcomm;

import static com.fazecast.jSerialComm.SerialPort.NO_PARITY;
import static com.fazecast.jSerialComm.SerialPort.ONE_STOP_BIT;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.fazecast.jSerialComm.SerialPort;

import fr.romainmoreau.epaper.client.common.uart.AbstractUARTEPaperClient;
import fr.romainmoreau.epaper.client.common.uart.command.Command;

public class JSerialCommEPaperClient extends AbstractUARTEPaperClient {
	private SerialPort serialPort;

	private byte[] response;

	public JSerialCommEPaperClient(String portName, long timeout, long receiveTimeout) throws IOException {
		super(timeout, receiveTimeout);
		serialPort = SerialPort.getCommPort(portName);
		serialPort.setBaudRate(115200);
		serialPort.setNumDataBits(8);
		serialPort.setNumStopBits(ONE_STOP_BIT);
		serialPort.setParity(NO_PARITY);
		if (!serialPort.openPort()) {
			throw new IOException("Port not opened");
		}
	}

	@Override
	public synchronized void close() throws IOException {
		if (!serialPort.closePort()) {
			throw new IOException("Port not closed");
		}
	}

	@Override
	protected byte[] getResponse() {
		return response;
	}

	@Override
	protected void sendCommand(Command command) throws IOException {
		response = null;
		int bytesWritten = serialPort.writeBytes(command.getFrame(), command.getFrame().length);
		if (bytesWritten != command.getFrame().length) {
			throw new IOException("Error writing bytes");
		}
	}

	@Override
	protected void waitForResponse(long timeout, long receiveTimeout) {
		try {
			LocalDateTime start = LocalDateTime.now();
			byte[] readBytes = readBytes();
			while (readBytes == null) {
				wait(receiveTimeout);
				if (start.until(LocalDateTime.now(), ChronoUnit.MILLIS) >= timeout) {
					return;
				}
				readBytes = readBytes();
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
				readBytes = readBytes();
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
		}
	}

	private byte[] readBytes() {
		int bytesAvailable = serialPort.bytesAvailable();
		if (bytesAvailable == 0) {
			return null;
		} else if (bytesAvailable > 0) {
			byte[] bytes = new byte[bytesAvailable];
			if (serialPort.readBytes(bytes, bytes.length) == bytes.length) {
				return bytes;
			} else {
				throw new RuntimeException("Error reading bytes");
			}
		} else {
			throw new RuntimeException("Port not opened");
		}
	}
}
