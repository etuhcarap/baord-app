package io.sparta.boardapp.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.sparta.boardapp.domain.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
