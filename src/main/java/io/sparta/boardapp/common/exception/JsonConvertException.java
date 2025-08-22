package io.sparta.boardapp.common.exception;

public class JsonConvertException extends RuntimeException {
	private String message;

	public JsonConvertException(String message) {
		super(message);
		this.message = message;
	}
}
