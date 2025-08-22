package io.sparta.boardapp.common.exception;

public class SerializeException extends JsonConvertException {
	private final static String message = "serialize failed : key: [%s], value: [%s]";

	public SerializeException(String key, String value) {
		super(message.formatted(key, value));
	}
}
