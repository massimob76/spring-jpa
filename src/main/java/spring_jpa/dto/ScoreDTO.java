package spring_jpa.dto;

import java.time.Instant;

public class ScoreDTO {

    private final String name;
    private final String game;
    private final int score;
    private final Instant timestamp;

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScoreDTO scoreDTO = (ScoreDTO) o;

        if (score != scoreDTO.score) return false;
        if (name != null ? !name.equals(scoreDTO.name) : scoreDTO.name != null) return false;
        if (game != null ? !game.equals(scoreDTO.game) : scoreDTO.game != null) return false;
        return timestamp != null ? timestamp.equals(scoreDTO.timestamp) : scoreDTO.timestamp == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (game != null ? game.hashCode() : 0);
        result = 31 * result + score;
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ScoreDTO{" +
                "name='" + name + '\'' +
                ", game='" + game + '\'' +
                ", score=" + score +
                ", timestamp=" + timestamp +
                '}';
    }
}
