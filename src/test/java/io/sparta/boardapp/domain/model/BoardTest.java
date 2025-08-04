package io.sparta.boardapp.domain.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Domain:Model:Board")
class BoardTest {

	@Test
	@DisplayName("객체 생성")
	void create() {
		// Given
		String title = "title";
		String content = "content";
	
		// When
		Board actual = Board.of(title, content);

		// Then
		assertThat(actual.getTitle()).isEqualTo(title);
		assertThat(actual.getContent()).isEqualTo(content);
	}
}
