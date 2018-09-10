package spring_jpa;

import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import spring_jpa.dao.ScoreDAO;
import spring_jpa.dto.ScoreDTO;

@Component
public class Demo implements CommandLineRunner {

  @Autowired
  private ScoreDAO scoreDAO;

  @Override
  public void run(String... args) throws Exception {
    ScoreDTO entity = new ScoreDTO("Massimo", "game", 123, Instant.now());
    scoreDAO.save(entity);
    scoreDAO.findAll();
    System.out.println("this is it");
  }

}
