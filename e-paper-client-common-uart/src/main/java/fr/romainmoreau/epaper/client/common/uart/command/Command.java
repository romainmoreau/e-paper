package fr.romainmoreau.epaper.client.common.uart.command;

import java.nio.ByteBuffer;

public class Command {
	private static final byte FRAME_HEADER = (byte) 0xA5;

	private static final byte[] FRAME_END = new byte[] { (byte) 0xCC, (byte) 0x33, (byte) 0xC3, (byte) 0x3C };

	private final short length;

	private final byte[] frame;

	public Command(int type) {
		this((byte) type);
	}

	public Command(byte type) {
		this(type, new byte[0]);
	}

	public Command(int type, byte[] parametersOrData) {
		this((byte) type, parametersOrData);
	}

	public Command(byte type, byte[] parametersOrData) {
		length = getLength(parametersOrData);
		frame = getFrame(type, parametersOrData);
	}

	private short getLength(byte[] parametersOrData) {
		return (short) (parametersOrData.length + 9);
	}

	private byte getParity(byte[] frame) {
		byte parity = frame[0];
		for (int i = 1; i < frame.length - 1; i++) {
			parity ^= frame[i];
		}
		return parity;
	}

	private byte[] getFrame(byte type, byte[] parametersOrData) {
		byte[] frame = new byte[length];
		ByteBuffer byteBuffer = ByteBuffer.wrap(frame);
		byteBuffer.put(FRAME_HEADER);
		byteBuffer.putShort(length);
		byteBuffer.put(type);
		byteBuffer.put(parametersOrData);
		byteBuffer.put(FRAME_END);
		byteBuffer.put(getParity(frame));
		return frame;
	}

	public short getLength() {
		return length;
	}

	public byte[] getFrame() {
		return frame;
	}
}
