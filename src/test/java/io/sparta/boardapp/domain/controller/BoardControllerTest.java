package io.sparta.boardapp.domain.controller;

import static io.sparta.boardapp.fixture.BoardFixtureGenerator.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpHeaders.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.sparta.boardapp.domain.controller.dto.CreateBoardRequest;
import io.sparta.boardapp.domain.service.BoardService;

@DisplayName("Controller:Board")
@WebMvcTest(controllers = BoardController.class)
@AutoConfigureMockMvc
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class BoardControllerTest {
	private final MockMvc mockMvc;
	private final ObjectMapper objectMapper;

	@MockitoBean
	private BoardService boardService;

	public BoardControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
		this.mockMvc = mockMvc;
		this.objectMapper = objectMapper;
	}

	@Test
	@DisplayName("[POST:201] 게시글 생성 API")
	void create() throws Exception {
		// Given
		CreateBoardRequest createBoardRequest = new CreateBoardRequest(TITLE, CONTENT);
		when(boardService.create(TITLE, CONTENT)).thenReturn(1L);

		// When
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/boards")
			.contentType(APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(createBoardRequest))
		);

		// Then
		resultActions.andDo(print())
			.andExpect(status().isCreated())
			.andExpect(header().exists(LOCATION))
		;
	}
}
