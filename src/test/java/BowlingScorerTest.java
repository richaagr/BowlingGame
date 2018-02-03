import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class BowlingScorerTest {

    private BowlingScorer bowlingScorer;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        bowlingScorer = new BowlingScorer();
    }

    @Test
    public void shouldAddNumberOfPins() throws BowlingException {
        bowlingScorer.roll(1);
        bowlingScorer.roll(6);
        rollMultipleWithNoPins(18);

        assertEquals(7, bowlingScorer.score()); //(1+6)
    }

    @Test
    public void shouldAddSpareScoreIfSpare() throws BowlingException {
        bowlingScorer.roll(4);
        bowlingScorer.roll(6);
        bowlingScorer.roll(4);
        bowlingScorer.roll(5);
        rollMultipleWithNoPins(16);

        assertEquals(23, bowlingScorer.score()); //(4+6+4)+(4+5)
    }

    @Test
    public void shouldAddStrikeScoreIfStrike() throws BowlingException {
        rollStrikes(1);
        bowlingScorer.roll(4);
        bowlingScorer.roll(5);
        rollMultipleWithNoPins(16);

        assertEquals(28, bowlingScorer.score()); //(10+4+5)+(4+5)
    }

    @Test
    public void shouldAddBothSpareAndStrikeScore() throws BowlingException {
        rollStrikes(1);
        bowlingScorer.roll(6);
        bowlingScorer.roll(4);
        bowlingScorer.roll(2);
        bowlingScorer.roll(3);
        rollMultipleWithNoPins(14);

        assertEquals(37, bowlingScorer.score()); //(10+6+4)+(6+4+2)+(2+3)
    }

    @Test
    public void shouldTestMultipleStrikesAndSpares() throws BowlingException {
        rollStrikes(1);
        bowlingScorer.roll(5);
        bowlingScorer.roll(5);
        rollStrikes(1);
        bowlingScorer.roll(4);
        bowlingScorer.roll(6);
        rollMultipleWithNoPins(12);

        assertEquals(70, bowlingScorer.score()); //(10+5+5)+(5+5+10)+(10+4+6)+(4+6+0)
    }

    @Test
    public void shouldTestAllStrikes() throws BowlingException {
        rollStrikes(10);
        //Two more chances in case of strike
        rollStrikes(2);

        assertEquals(300, bowlingScorer.score()); //(10+10+10)*10
    }

    @Test
    public void shouldTestOneMoreChanceInCaseOfLastSpare() throws BowlingException {
        rollStrikes(9);
        bowlingScorer.roll(4);
        bowlingScorer.roll(6);
        //One more chances in case of spare
        bowlingScorer.roll(3);

        assertEquals(267, bowlingScorer.score()); //(10+10+10)*7 +(10+10+4) +(10+4+6)+(4+6+3)
    }

    @Test
    public void shouldThrowNotEnoughRollsError() throws BowlingException {
        expectedException.expect(BowlingException.class);
        expectedException.expectMessage(Constants.NOT_ENOUGH_ROLLS_MESSAGE);
        rollStrikes(9);
        bowlingScorer.roll(4);
        bowlingScorer.roll(6);
        bowlingScorer.score();
    }

    private void rollStrikes(int times) {
        for (int i = 0; i < times; i++) {
            bowlingScorer.roll(10);
        }
    }

    private void rollMultipleWithNoPins(int rollWithNoPins) {
        for (int i = 0; i < rollWithNoPins; i++) {
            bowlingScorer.roll(0);
        }
    }
}
