package io.sparta.boardapp.fixture;

import io.sparta.boardapp.domain.model.Board;

public class BoardFixtureGenerator {
	public static final String TITLE = "title";
	public static final String CONTENT = "content";

	public static Board createFixture() {
		return Board.of(TITLE, CONTENT);
	}
}
