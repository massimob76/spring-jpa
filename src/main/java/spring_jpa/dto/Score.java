package spring_jpa.dto;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.Instant;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Score {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private String game;

    @Column(nullable=false)
    private int score;

    @Column(nullable=false)
    private Instant timestamp;

    Score() {}

    public Score(String name, String game, int score, Instant timestamp) {
        this(null, name, game, score, timestamp);
    }

    public Score(Integer id, String name, String game, int score, Instant timestamp) {
        this.id = id;
        this.name = name;
        this.game = game;
        this.score = score;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
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
        Score scoreDTO = (Score) o;
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
