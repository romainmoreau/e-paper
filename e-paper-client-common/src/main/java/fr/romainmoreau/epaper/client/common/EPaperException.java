package fr.romainmoreau.epaper.client.common;

public class EPaperException extends Exception {
	private static final long serialVersionUID = 1L;

	public EPaperException() {
		super();
	}

	public EPaperException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EPaperException(String message, Throwable cause) {
		super(message, cause);
	}

	public EPaperException(String message) {
		super(message);
	}

	public EPaperException(Throwable cause) {
		super(cause);
	}
}
