package spring_jpa.dao;

import static java.time.temporal.ChronoUnit.SECONDS;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import spring_jpa.dto.Score;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ScoreDAOTest {

  @Autowired
  private ScoreDAO scoreDAO;

  @Test
  void canSaveAScore() {
    Score score = new Score("Massimo", "candy crush", 123, Instant.now().truncatedTo(SECONDS));
    scoreDAO.save(score);
    assertEquals(asList(score), scoreDAO.findAll());
  }

}