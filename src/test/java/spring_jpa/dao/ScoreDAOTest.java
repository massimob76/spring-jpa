package spring_jpa.dao;

import static java.time.temporal.ChronoUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Instant;
import org.flywaydb.test.FlywayTestExecutionListener;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import spring_jpa.dto.Score;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, FlywayTestExecutionListener.class })
class ScoreDAOTest {

  @Autowired
  private ScoreDAO scoreDAO;

  @BeforeEach
  @FlywayTest
  void setUp() {
    // do nothing
  }

  @Test
  void canSaveAScore() {
    Score score = new Score("Massimo", "candy crush", 150, Instant.now().truncatedTo(SECONDS));
    scoreDAO.save(score);
    assertEquals(score, scoreDAO.findByNameAndGame("Massimo", "candy crush"));
  }

  @Test
  void shouldNotBeAbleToCreateDuplicatedScores() {
    Score score = new Score("Massimo", "super mario", 150, Instant.now().truncatedTo(SECONDS));
    assertThrows(DataIntegrityViolationException.class, () -> scoreDAO.save(score));
  }

  @Test
  void shouldBeAbleToGetTheTopScorePerGame() {
    Score expected = new Score(4, "Mizio",   "super mario", 125, Instant.parse("2018-09-11T17:30:00Z"));
    assertEquals(expected, scoreDAO.findTopByGameOrderByScoreDesc("super mario"));
  }

  @Test
  void shouldBeAbleToGetTheTopAbsoluteScore() {
    Score expected = new Score(4, "Mizio",   "super mario", 125, Instant.parse("2018-09-11T17:30:00Z"));
    assertEquals(expected, scoreDAO.findTopByOrderByScoreDesc());
  }

}