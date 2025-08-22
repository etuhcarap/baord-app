package io.sparta.boardapp.domain.controller;

import java.net.URI;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.sparta.boardapp.domain.controller.dto.BoardResponse;
import io.sparta.boardapp.domain.controller.dto.CreateBoardRequest;
import io.sparta.boardapp.domain.controller.dto.UpdateBoardRequest;
import io.sparta.boardapp.domain.service.BoardService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;

	@PostMapping
	ResponseEntity<Void> create(@RequestBody CreateBoardRequest createBoardRequest) {
		Long createdBoardId = boardService.create(createBoardRequest.title(), createBoardRequest.content());
		URI createdBoardUri = UriComponentsBuilder.fromUriString("/api/boards/{id}")
			.build(createdBoardId);

		return ResponseEntity.created(createdBoardUri)
			.build();
	}

	@PutMapping("{id}")
	ResponseEntity<BoardResponse> update(
		@PathVariable Long id,
		@RequestBody UpdateBoardRequest updateBoardRequest
	) {
		BoardResponse updateBoardResponse = boardService.update(id, updateBoardRequest);
		return ResponseEntity.ok(updateBoardResponse);
	}

	@GetMapping("{id}")
	ResponseEntity<BoardResponse> getBoard(@PathVariable("id") Long id) {
		BoardResponse boardResponse = boardService.getBoard(id);
		return ResponseEntity.ok(boardResponse);
	}

	@GetMapping("{id}/cache-aside")
	ResponseEntity<BoardResponse> getBoardWithRedisTemplate(@PathVariable("id") Long id) {
		BoardResponse boardResponse = boardService.getBoardWithCacheAside(id);
		return ResponseEntity.ok(boardResponse);
	}

	@GetMapping
	ResponseEntity<PagedModel<BoardResponse>> getBoards(@PageableDefault Pageable pageable) {
		PagedModel<BoardResponse> boardResponses = boardService.getBoards(pageable);
		return ResponseEntity.ok(boardResponses);
	}
}
