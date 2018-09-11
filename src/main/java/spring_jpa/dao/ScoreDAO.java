package spring_jpa.dao;

import spring_jpa.dto.Score;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreDAO extends JpaRepository<Score, Long> {



}
