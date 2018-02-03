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

    @Test
    public void shouldAddStrikeScoreIfStrike() {
        bowlingScorer.roll(10);
        bowlingScorer.roll(4);
        bowlingScorer.roll(5);
        rollMultipleWithNoPins(16);

        assertEquals(28, bowlingScorer.score()); //(10+4+5)+(4+5)
    }

    @Test
    public void shouldAddBothSpareAndStrikeScore() {
        bowlingScorer.roll(10);
        bowlingScorer.roll(6);
        bowlingScorer.roll(4);
        bowlingScorer.roll(2);
        bowlingScorer.roll(3);
        rollMultipleWithNoPins(14);

        assertEquals(37, bowlingScorer.score()); //(10+6+4)+(6+4+2)+(2+3)
    }

    @Test
    public void shouldTestMultipleStrikesAndSpares() {
        bowlingScorer.roll(10);
        bowlingScorer.roll(5);
        bowlingScorer.roll(5);
        bowlingScorer.roll(10);
        bowlingScorer.roll(4);
        bowlingScorer.roll(6);
        rollMultipleWithNoPins(12);

        assertEquals(70, bowlingScorer.score()); //(10+5+5)+(5+5+10)+(10+4+6)+(4+6+0)
    }

    @Test
    public void shouldTestAllStrikes() {
        bowlingScorer.roll(10);
        bowlingScorer.roll(10);
        bowlingScorer.roll(10);
        bowlingScorer.roll(10);
        bowlingScorer.roll(10);
        bowlingScorer.roll(10);
        bowlingScorer.roll(10);
        bowlingScorer.roll(10);
        bowlingScorer.roll(10);
        bowlingScorer.roll(10);
        //Two more chances in case of strike
        bowlingScorer.roll(10);
        bowlingScorer.roll(10);

        assertEquals(300, bowlingScorer.score()); //(10+10+10)*10
    }

    private void rollMultipleWithNoPins(int rollWithNoPins) {
        for (int i = 0; i < rollWithNoPins; i++) {
            bowlingScorer.roll(0);
        }
    }
}
