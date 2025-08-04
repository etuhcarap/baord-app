package io.sparta.boardapp.domain.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.sparta.boardapp.domain.controller.dto.CreateBoardRequest;
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
}
