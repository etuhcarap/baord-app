package io.sparta.boardapp.domain.controller.dto;

public record CreateBoardRequest(
	String title,
	String content
) {
}
