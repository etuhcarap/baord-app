package io.sparta.boardapp.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.sparta.boardapp.domain.controller.dto.BoardResponse;
import io.sparta.boardapp.domain.controller.dto.UpdateBoardRequest;
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

	public BoardResponse getBoard(Long id) {
		Board board = findBoard(id);
		
		return BoardResponse.from(board);
	}

	private Board findBoard(Long id) {
		return boardRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("존재하지 않는 게시글 입니다."));
	}

	public PagedModel<BoardResponse> getBoards(Pageable pageable) {
		Page<Board> boardPage = boardRepository.findAll(pageable);
		return new PagedModel<>(boardPage.map(BoardResponse::from));
	}

	@Transactional
	public BoardResponse update(Long id, UpdateBoardRequest updateBoardRequest) {
		Board board = findBoard(id);
		board.update(updateBoardRequest.title(), updateBoardRequest.content());

		return BoardResponse.from(board);
	}
}
