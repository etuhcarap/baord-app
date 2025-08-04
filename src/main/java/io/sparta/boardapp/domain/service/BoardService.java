package io.sparta.boardapp.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.sparta.boardapp.domain.model.Board;
import io.sparta.boardapp.domain.repository.BoardRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
	private final BoardRepository boardRepository;

	@Transactional
	public Long create(String title, String content) {
		Board board = Board.of(title, content);
		boardRepository.save(board);

		return board.getId();
	}
}
