package fr.romainmoreau.epaper.client.api;

import java.nio.charset.StandardCharsets;

public class EPaperResponseException extends EPaperException {
	private static final long serialVersionUID = 1L;

	public EPaperResponseException(byte[] response) {
		super(new String(response, StandardCharsets.US_ASCII));
	}
}
