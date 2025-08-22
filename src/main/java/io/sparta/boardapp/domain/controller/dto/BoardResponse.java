package io.sparta.boardapp.domain.controller.dto;

// public record BoardResponse(
// 	Long id,
// 	String title,
// 	String content,
// 	@JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
// 	@JsonSerialize(using = LocalDateTimeSerializer.class)
// 	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
// 	LocalDateTime createdAt
// ) {
//
// 	public static BoardResponse from(Board board) {
// 		return new BoardResponse(
// 			board.getId(),
// 			board.getTitle(),
// 			board.getContent(),
// 			board.getCreatedAt()
// 		);
// 	}
// }

import java.time.LocalDateTime;

import io.sparta.boardapp.domain.model.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardResponse {
	private Long id;
	private String title;
	private String content;
	private LocalDateTime createdAt;

	public BoardResponse(Long id, String title, String content, LocalDateTime createdAt) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.createdAt = createdAt;
	}

	public static BoardResponse from(Board board) {
		return new BoardResponse(
			board.getId(),
			board.getTitle(),
			board.getContent(),
			board.getCreatedAt()
		);
	}
}
