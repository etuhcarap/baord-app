package io.sparta.boardapp.common.exception;

public class DeserializeException extends JsonConvertException {
	private static final String message = "deserialize failed : key : [%s], jsonString : [%s], type : [%s]";

	public DeserializeException(String key, String value, String type) {
		super(message.formatted(key, value, type));
	}
}
