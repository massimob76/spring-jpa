package spring_jpa.dto;

import java.time.Instant;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ScoreDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String game;

    private int score;

    private Instant timestamp;

    ScoreDTO() {}

    public ScoreDTO(String name, String game, int score, Instant timestamp) {
        this.name = name;
        this.game = game;
        this.score = score;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public String getGame() {
        return game;
    }

    public int getScore() {
        return score;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ScoreDTO scoreDTO = (ScoreDTO) o;
        return score == scoreDTO.score &&
            Objects.equals(id, scoreDTO.id) &&
            Objects.equals(name, scoreDTO.name) &&
            Objects.equals(game, scoreDTO.game) &&
            Objects.equals(timestamp, scoreDTO.timestamp);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, game, score, timestamp);
    }

    @Override
    public String toString() {
        return "ScoreDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", game='" + game + '\'' +
            ", score=" + score +
            ", timestamp=" + timestamp +
            '}';
    }
}
