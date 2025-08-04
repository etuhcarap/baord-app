package io.sparta.boardapp.domain.controller.dto;

import java.time.LocalDateTime;

import io.sparta.boardapp.domain.model.Board;

public record BoardResponse(
	Long id,
	String title,
	String content,
	LocalDateTime createdAt
) {

	public static BoardResponse from(Board board) {
		return new BoardResponse(
			board.getId(),
			board.getTitle(),
			board.getContent(),
			board.getCreatedAt()
		);
	}
}
