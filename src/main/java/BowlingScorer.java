import java.util.ArrayList;
import java.util.List;

public class BowlingScorer implements BowlingGame {

    private List<Integer> rolledPins;
    private int score = 0;

    BowlingScorer() {
        this.rolledPins = new ArrayList<>();
    }

    @Override
    public void roll(int pins) {
        rolledPins.add(pins);
    }

    @Override
    public int score() throws BowlingException {
        try {
            int rollsInFrame = 0;
            for (int frame = 0; frame < Constants.TOTAL_FRAMES; frame++) {
                if (isStrike(rollsInFrame)) {
                    score += Constants.WINNING_FRAME_SCORE + strikeScore(rollsInFrame);
                    rollsInFrame += 1;
                } else if (isSpare(rollsInFrame)) {
                    score += Constants.WINNING_FRAME_SCORE + spareScore(rollsInFrame);
                    rollsInFrame += 2;
                } else {
                    score += rollSumInFrame(rollsInFrame);
                    rollsInFrame += 2;
                }
            }
            return score;
        } catch (IndexOutOfBoundsException e) {
            throw new BowlingException(Constants.NOT_ENOUGH_ROLLS_MESSAGE);
        }
    }

    private int strikeScore(int rollsInFrame) {
        return rollSumInFrame(rollsInFrame + 1);
    }

    private boolean isStrike(int rollsInFrame) {
        return rolledPins.get(rollsInFrame) == Constants.WINNING_FRAME_SCORE;
    }

    private boolean isSpare(int frame) {
        return rollSumInFrame(frame) == Constants.WINNING_FRAME_SCORE;
    }

    private int rollSumInFrame(int rollInFrame) {
        return rolledPins.get(rollInFrame) + rolledPins.get(rollInFrame + 1);
    }

    private int spareScore(int spareFrameRoll) {
        return rolledPins.get(spareFrameRoll + 2);
    }
}
