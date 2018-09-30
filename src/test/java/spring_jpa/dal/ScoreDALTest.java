package spring_jpa.dal;

import org.flywaydb.test.FlywayTestExecutionListener;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import spring_jpa.dto.Score;
import spring_jpa.util.TimeProvider;

import java.time.Instant;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.context.TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestExecutionListeners(listeners = FlywayTestExecutionListener.class, mergeMode = MERGE_WITH_DEFAULTS)
class ScoreDALTest {

    private static final Instant NOW = Instant.parse("2018-09-30T17:00:00Z");

    @Autowired
    private ScoreDAL scoreDAL;

    @MockBean
    private TimeProvider timeProvider;

    @BeforeEach
    @FlywayTest
    void setUp() {
        when(timeProvider.now()).thenReturn(NOW);
    }

    @Test
    void canGetAllTheScores() {
        List<Score> expected = asList(
                new Score(1, "Massimo", "super mario", 123, Instant.parse("2018-09-11T17:00:00Z")),
                new Score(2, "Shannon", "pokemon", 110, Instant.parse("2018-09-11T17:10:00Z")),
                new Score(3, "Shannon", "super mario", 120, Instant.parse("2018-09-11T17:20:00Z")),
                new Score(4, "Mizio", "super mario", 125, Instant.parse("2018-09-11T17:30:00Z"))
        );
        List<Score> actual = scoreDAL.getAll();
        assertEquals(expected, actual);
    }

    @Test
    void canGetAllTheScoresForAUser() {
        List<Score> expected = asList(
                new Score(2, "Shannon", "pokemon", 110, Instant.parse("2018-09-11T17:10:00Z")),
                new Score(3, "Shannon", "super mario", 120, Instant.parse("2018-09-11T17:20:00Z"))
        );
        List<Score> actual = scoreDAL.getAllByName("Shannon");
        assertEquals(expected, actual);
    }

    @Test
    void canGetAScoreByUserAndGame() {
        Score expected = new Score(3, "Shannon", "super mario", 120, Instant.parse("2018-09-11T17:20:00Z"));
        Score actual = scoreDAL.getScoreByNameAndGame("Shannon", "super mario");
        assertEquals(actual, expected);
    }

    @Test
    void canGetTheTopAbsoluteScore() {
        Score expected = new Score(4, "Mizio",   "super mario", 125, Instant.parse("2018-09-11T17:30:00Z"));
        assertEquals(expected, scoreDAL.getTopScore());
    }

    @Test
    void canGetTheTopScorePerGame() {
        Score expected = new Score(4, "Mizio",   "super mario", 125, Instant.parse("2018-09-11T17:30:00Z"));
        assertEquals(expected, scoreDAL.getTopScoreForGame("super mario"));
    }

    @Test
    void canSaveAScore() {
        scoreDAL.insertScore("Massimo", "candy crush", 150);

        Score expected = new Score(5, "Massimo", "candy crush", 150, NOW);
        assertEquals(expected, scoreDAL.getScoreByNameAndGame("Massimo", "candy crush"));
    }

    @Test
    void shouldNotBeAbleToCreateDuplicatedScores() {
        assertThrows(DataIntegrityViolationException.class, () -> scoreDAL.insertScore("Massimo", "super mario", 150));
    }

    @Test
    void canUpdateAScore() {
        scoreDAL.updateScore("Shannon", "super mario", 130);

        Score expected = new Score(3, "Shannon", "super mario", 130, NOW);
        Score actual = scoreDAL.getScoreByNameAndGame("Shannon", "super mario");
        assertEquals(expected, actual);
    }

    @Test
    void canDeleteAScore() {
        scoreDAL.deleteScore("Shannon", "super mario");

        assertNull(scoreDAL.getScoreByNameAndGame("Shannon", "super mario"));
    }


}