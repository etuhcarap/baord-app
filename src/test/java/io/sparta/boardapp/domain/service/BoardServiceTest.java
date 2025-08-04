package io.sparta.boardapp.domain.service;

import static io.sparta.boardapp.fixture.BoardFixtureGenerator.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.sparta.boardapp.domain.controller.dto.BoardResponse;
import io.sparta.boardapp.domain.model.Board;
import io.sparta.boardapp.domain.repository.BoardRepository;

@DisplayName("Service:Board")
@ExtendWith(MockitoExtension.class)
class BoardServiceTest {
	@Mock
	private BoardRepository boardRepository;

	@InjectMocks
	private BoardService boardService;

	@Test
	@DisplayName("게시글 생성")
	void create() {
		// Given
		Board given = createFixture();
		when(boardRepository.save(any(Board.class))).thenReturn(given);

		// When
		boardService.create(TITLE, CONTENT);

		// Then
		verify(boardRepository, times(1)).save(any(Board.class));
	}

	@Test
	@DisplayName("게시글 조회")
	void getBoard() {
		// Given
		Long id = 1L;
		Board given = createFixture();
		when(boardRepository.findById(id)).thenReturn(Optional.of(given));

		// When
		BoardResponse actual = boardService.getBoard(1L);

		// Then
		verify(boardRepository, times(1)).findById(id);
	}
}
