import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BowlingScorerTest {

    private BowlingScorer bowlingScorer;

    @Before
    public void setUp() {
        bowlingScorer = new BowlingScorer();
    }

    @Test
    public void shouldAddNumberOfPins() {
        bowlingScorer.roll(1);
        bowlingScorer.roll(6);
        rollMultipleWithNoPins(18);

        assertEquals(7, bowlingScorer.score()); //(1+6)
    }

    @Test
    public void shouldAddSpareScoreIfSpare() {
        bowlingScorer.roll(4);
        bowlingScorer.roll(6);
        bowlingScorer.roll(4);
        bowlingScorer.roll(5);
        rollMultipleWithNoPins(16);

        assertEquals(23, bowlingScorer.score()); //(4+6+4)+(4+5)
    }

    private void rollMultipleWithNoPins(int rollWithNoPins) {
        for (int i = 0; i < rollWithNoPins; i++) {
            bowlingScorer.roll(0);
        }
    }
}
