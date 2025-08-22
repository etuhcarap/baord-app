package io.sparta.boardapp.domain.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.sparta.boardapp.config.redis.RedisTemplateService;
import io.sparta.boardapp.domain.controller.dto.BoardResponse;
import io.sparta.boardapp.domain.controller.dto.UpdateBoardRequest;
import io.sparta.boardapp.domain.model.Board;
import io.sparta.boardapp.domain.repository.BoardRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
	private static final String BOARD_KEY_TEMPLATE = "board:%s";
	private final BoardRepository boardRepository;
	private final RedisTemplateService redisTemplateService;

	@Transactional
	@CacheEvict(cacheNames = "boards", allEntries = true)
	public Long create(String title, String content) {
		Board board = Board.of(title, content);
		boardRepository.save(board);

		return board.getId();
	}

	public BoardResponse getBoardWithCacheAside(Long id) {
		String key = BOARD_KEY_TEMPLATE.formatted(id);

		BoardResponse boardResponseCache = redisTemplateService.read(key, BoardResponse.class);
		if (boardResponseCache != null) {
			return boardResponseCache;
		}

		Board board = boardRepository.findById(id).orElseThrow();
		BoardResponse boardResponse = BoardResponse.from(board);
		redisTemplateService.write(key, boardResponse);

		return boardResponse;
	}

	// board:1
	@Cacheable(cacheNames = "board", key = "#id", cacheManager = "redisCacheManager")
	public BoardResponse getBoard(Long id) {
		Board board = findBoard(id);

		return BoardResponse.from(board);
	}

	private Board findBoard(Long id) {
		return boardRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("존재하지 않는 게시글 입니다."));
	}

	@Cacheable(
		cacheNames = "boards",
		key = "'page=' + #pageable.pageNumber + ':size=' + #pageable.pageSize + ':sort=' + #pageable.sort.toString()",
		cacheManager = "redisCacheManager"
	)
	public PagedModel<BoardResponse> getBoards(Pageable pageable) {
		Page<Board> boardPage = boardRepository.findAll(pageable);

		return new PagedModel<>(boardPage.map(BoardResponse::from));
	}

	@Transactional
	@CachePut(cacheNames = "board", key = "#id")
	public BoardResponse update(Long id, UpdateBoardRequest updateBoardRequest) {
		Board board = findBoard(id);
		board.update(updateBoardRequest.title(), updateBoardRequest.content());

		return BoardResponse.from(board);
	}
}
