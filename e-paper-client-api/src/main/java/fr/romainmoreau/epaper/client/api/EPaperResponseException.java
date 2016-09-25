package fr.romainmoreau.epaper.client.api;

import java.nio.charset.StandardCharsets;

public class EPaperResponseException extends EPaperException {
	private static final long serialVersionUID = 1L;

	public EPaperResponseException(byte[] response) {
		super(getMessage(response));
	}

	public EPaperResponseException(byte[] response, Exception cause) {
		super(getMessage(response), cause);
	}

	public EPaperResponseException(String message) {
		super(message);
	}

	public EPaperResponseException(String message, Exception cause) {
		super(message, cause);
	}

	private static String getMessage(byte[] response) {
		return new String(response, StandardCharsets.US_ASCII);
	}
}
