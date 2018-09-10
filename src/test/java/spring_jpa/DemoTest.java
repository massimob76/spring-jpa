package spring_jpa;

import static java.time.temporal.ChronoUnit.SECONDS;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import spring_jpa.dao.ScoreDAO;
import spring_jpa.dto.ScoreDTO;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class DemoTest {

  @Autowired
  private ScoreDAO scoreDAO;

  @Test
  void canSaveAScore() {
    ScoreDTO score = new ScoreDTO("Massimo", "game", 123, Instant.now().truncatedTo(SECONDS));
    scoreDAO.save(score);
    assertEquals(asList(score), scoreDAO.findAll());
  }

}