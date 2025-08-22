package io.sparta.boardapp.config.redis;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisCacheConfig {
	@Value("${spring.data.redis.default-ttl-minutes}")
	private int defaultTtl;

	@Bean
	public RedisCacheManager redisCacheManager(
		RedisConnectionFactory redisConnectionFactory,
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer
	) {
		RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
			.computePrefixWith(cacheName -> cacheName + ":")
			.serializeKeysWith(
				RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())
			)
			.serializeValuesWith(
				RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer)
			)
			.entryTtl(Duration.ofMinutes(defaultTtl));

		return RedisCacheManager
			.RedisCacheManagerBuilder
			.fromConnectionFactory(redisConnectionFactory)
			.cacheDefaults(redisCacheConfiguration)
			.build();
	}
}
