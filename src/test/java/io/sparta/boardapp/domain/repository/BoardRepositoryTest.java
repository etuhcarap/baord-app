package io.sparta.boardapp.domain.repository;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestConstructor;

import io.sparta.boardapp.config.jpa.JpaConfig;
import io.sparta.boardapp.domain.model.Board;
import io.sparta.boardapp.fixture.BoardFixtureGenerator;
import jakarta.persistence.EntityManager;

@Import(JpaConfig.class)
@DisplayName("Domain:Repository:Baord")
@DataJpaTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class BoardRepositoryTest {
	private final BoardRepository boardRepository;
	private final EntityManager entityManager;

	public BoardRepositoryTest(BoardRepository boardRepository, EntityManager entityManager) {
		this.boardRepository = boardRepository;
		this.entityManager = entityManager;
	}

	@Test
	@DisplayName("게시글을 저장한다")
	void save() {
		// Given
		Board given = BoardFixtureGenerator.createFixture();

		// When
		Board save = boardRepository.save(given);
		// Then
		assertThat(given.getId()).isNotNull();
		assertThat(given.getCreatedAt()).isNotNull();
	}

	@Test
	@DisplayName("게시글을 조회한다.")
	void findById() {
		// Given
		Board given = BoardFixtureGenerator.createFixture();
		boardRepository.save(given);

		// When
		Board actual = boardRepository.findById(given.getId()).orElseThrow();

		// Then
		assertThat(actual).isEqualTo(given);
	}

	@Test
	@DisplayName("게시글을 수정한다.")
	void update_by_dirty_checking() {
		// Given
		String newTitle = "newTitle";
		String newContent = "newContent";

		Board given = BoardFixtureGenerator.createFixture();
		boardRepository.save(given);

		// When
		given.update(newTitle, newContent);
		entityManager.flush();

		// Then
		entityManager.clear();
		Board actual = boardRepository.findById(given.getId()).orElseThrow();

		assertThat(actual.getTitle()).isEqualTo(newTitle);
		assertThat(actual.getContent()).isEqualTo(newContent);
	}

}
