package io.sparta.boardapp.domain.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import io.sparta.boardapp.config.testcontainers.RedisTestContainersExtension;
import io.sparta.boardapp.domain.controller.dto.BoardResponse;

@SpringBootTest
@ExtendWith(RedisTestContainersExtension.class)
@ActiveProfiles("test")
public class BoardServiceTestContainersTest {
	@Autowired
	private BoardService boardService;

	@Test
	@Sql("classpath:dummy-board.sql")
	@DisplayName("게시글 조회")
	void findById() {
		// When
		BoardResponse boardResponse = boardService.getBoard(1L);

		// Then
		assertThat(boardResponse).isNotNull();
	}

	@Test
	@Sql("classpath:dummy-board.sql")
	@DisplayName("게시글 조회: Cache Aside 직접 구현")
	void getBoardWithCacheAside() {
		// When
		BoardResponse boardResponse = boardService.getBoardWithCacheAside(1L);

		// Then
		assertThat(boardResponse).isNotNull();
	}

	// @Test
	// @DisplayName("RedisTemplate을 이용한 데이터 캐싱 학습 테스트")
	// void setData() {
	// 	// Given
	// 	String key = "user:1:name";
	// 	String data = "hong-gd";
	//
	// 	// When
	// 	redisTemplate.opsForValue().set(key, data);
	//
	// 	// Then
	// 	String redisData = (String)redisTemplate.opsForValue().get(key);
	// 	assertThat(redisData).isEqualTo(data);
	// }
	//
	// @Test
	// @DisplayName("RedisTemplate을 이용한 데이터 캐싱 학습 테스트")
	// void setData2() {
	// 	// Given
	// 	String key = "user:1:name";
	// 	BoardResponse boardResponse = new BoardResponse(1L, "title", "content", LocalDateTime.now());
	//
	// 	// When
	// 	redisTemplate.opsForValue().set(key, boardResponse);
	//
	// 	// Then
	// 	BoardResponse redisData = (BoardResponse)redisTemplate.opsForValue().get(key);
	// 	assertThat(redisData).isEqualTo(boardResponse);
	// }

}
