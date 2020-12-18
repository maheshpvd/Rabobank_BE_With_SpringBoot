package com.rabobank.common;

public class RabobankException extends Exception {
	private static final long serialVersionUID = -7916269316042481277L;

	private final String message;

	public RabobankException(String message) {
		this.message = message;
	}

	public RabobankException(Exception excp) {
		this.message = excp.getMessage();
	}

	@Override
	public String getMessage() {
		return message;
	}

}
