package io.sparta.boardapp.config.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.sparta.boardapp.common.exception.DeserializeException;
import io.sparta.boardapp.common.exception.SerializeException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisTemplateService {
	private final StringRedisTemplate redisTemplate;
	private final ObjectMapper objectMapper;

	public <T> void write(String key, T value) {
		try {
			String jsonString = objectMapper.writeValueAsString(value);
			redisTemplate.opsForValue().set(key, jsonString);
		} catch (JsonProcessingException e) {
			throw new SerializeException(key, String.valueOf(value));
		}
	}

	public <T> T read(String key, Class<T> type) {
		String jsonString = redisTemplate.opsForValue().get(key);

		if (jsonString == null) {
			return null;
		}

		try {
			return objectMapper.readValue(jsonString, type);
		} catch (JsonProcessingException e) {
			throw new DeserializeException(key, jsonString, type.getTypeName());
		}
	}
}
