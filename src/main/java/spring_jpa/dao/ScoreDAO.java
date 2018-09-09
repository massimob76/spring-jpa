package spring_jpa.dao;

import spring_jpa.dto.ScoreDTO;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreDAO extends JpaRepository<ScoreDTO, Long> {



}
