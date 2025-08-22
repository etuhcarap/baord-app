package io.sparta.boardapp.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RedisSerializerConfig {
	@Bean
	public Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer(ObjectMapper objectMapper) {
		return new Jackson2JsonRedisSerializer<>(objectMapper, Object.class);
	}
}
