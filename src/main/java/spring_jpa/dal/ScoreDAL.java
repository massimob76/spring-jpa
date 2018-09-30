package spring_jpa.dal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring_jpa.dao.ScoreDAO;
import spring_jpa.dto.Score;
import spring_jpa.util.TimeProvider;

import java.util.List;

@Repository
public class ScoreDAL {

    @Autowired
    private ScoreDAO scoreDAO;

    @Autowired
    private TimeProvider timeProvider;

    public List<Score> getAll() {
        return scoreDAO.findAll();
    }

    public List<Score> getAllByName(String name) {
        return scoreDAO.findAllByName(name);
    }

    public Score getScoreByNameAndGame(String name, String game) {
        return scoreDAO.findByNameAndGame(name, game);
    }

    public Score getTopScore() {
        return scoreDAO.findTopByOrderByScoreDesc();
    }

    public Score getTopScoreForGame(String game) {
        return scoreDAO.findTopByGameOrderByScoreDesc(game);
    }

    public void insertScore(String name, String game, int score) {
        Score scoreToSave = new Score(name, game, score, timeProvider.now());
        scoreDAO.save(scoreToSave);
    }

    public void updateScore(String name, String game, int score) {
        Score toUpdate = scoreDAO.findByNameAndGame(name, game);
        Score updated = new Score(toUpdate.getId(), toUpdate.getName(), toUpdate.getGame(), score, timeProvider.now());
        scoreDAO.save(updated);
    }

    public void deleteScore(String name, String game) {
        Score scoreToDelete = scoreDAO.findByNameAndGame(name, game);
        scoreDAO.delete(scoreToDelete);
    }


}
