package spring_jpa.dao;

import spring_jpa.dto.Score;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ScoreDAO extends JpaRepository<Score, Long> {

  List<Score> findAllByName(String name);

  Score findByNameAndGame(String name, String game);

  Score findTopByGameOrderByScoreDesc(String game);

  Score findTopByOrderByScoreDesc();

}
