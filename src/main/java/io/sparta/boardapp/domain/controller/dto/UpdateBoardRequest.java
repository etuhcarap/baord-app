package io.sparta.boardapp.domain.controller.dto;

public record UpdateBoardRequest(
	String title,
	String content
) {
}
