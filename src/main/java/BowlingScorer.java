import java.util.ArrayList;
import java.util.List;

public class BowlingScorer implements BowlingGame {

    private static final int WINNING_FRAME_SCORE = 10;
    private static final int TOTAL_FRAMES = 10;
    private List<Integer> rolledPins;
    private int score;

    BowlingScorer() {
        this.rolledPins = new ArrayList<>();
    }

    @Override
    public void roll(int pins) {
        rolledPins.add(pins);
    }

    @Override
    public int score() {
        score = 0;
        int rollsInFrame = 0;
        for (int frame = 0; frame < TOTAL_FRAMES; frame++) {
            if (isStrike(rollsInFrame)) {
                score += WINNING_FRAME_SCORE + strikeScore(rollsInFrame);
                rollsInFrame +=1;
            } else if (isSpare(rollsInFrame)) {
                score += WINNING_FRAME_SCORE + spareScore(rollsInFrame);
                rollsInFrame += 2;
            } else {
                score += rollSumInFrame(rollsInFrame);
                rollsInFrame += 2;
            }
        }
        return score;
    }

    private int strikeScore(int rollsInFrame) {
        return rollSumInFrame(rollsInFrame + 1);
    }

    private boolean isStrike(int rollsInFrame) {
        return rolledPins.get(rollsInFrame) == WINNING_FRAME_SCORE;
    }

    private boolean isSpare(int frame) {
        return rollSumInFrame(frame) == WINNING_FRAME_SCORE;
    }

    private int rollSumInFrame(int rollInFrame) {
        return rolledPins.get(rollInFrame) + rolledPins.get(rollInFrame + 1);
    }

    private int spareScore(int spareFrameRoll) {
        return rolledPins.get(spareFrameRoll + 2);
    }
}
