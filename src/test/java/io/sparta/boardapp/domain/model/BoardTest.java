package io.sparta.boardapp.domain.model;

import static io.sparta.boardapp.fixture.BoardFixtureGenerator.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.sparta.boardapp.fixture.BoardFixtureGenerator;

@DisplayName("Domain:Model:Board")
class BoardTest {

	@Test
	@DisplayName("객체 생성")
	void create() {
		// Given
		Board actual = BoardFixtureGenerator.createFixture();

		// Then
		assertThat(actual.getTitle()).isEqualTo(TITLE);
		assertThat(actual.getContent()).isEqualTo(CONTENT);
	}

	@Test
	@DisplayName("게시글 수정")
	void update() {
		// Given
		String newTitle = "new title";
		String newContent = "new content";

		Board given = BoardFixtureGenerator.createFixture();

		// When
		given.update(newTitle, newContent);

		// Then
		assertThat(given.getTitle()).isEqualTo(newTitle);
		assertThat(given.getContent()).isEqualTo(newContent);
	}
}
