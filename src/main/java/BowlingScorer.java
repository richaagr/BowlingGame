import java.util.ArrayList;
import java.util.List;

public class BowlingScorer implements BowlingGame {

    private static final int WINNING_FRAME_SCORE = 10;
    private static final int TOTAL_FRAMES = 10;
    private List<Integer> rolledPins;

    BowlingScorer() {
        this.rolledPins = new ArrayList<>();
    }

    @Override
    public void roll(int pins) {
        rolledPins.add(pins);
    }

    @Override
    public int score() {
        int score = 0;
        int rollsInFrame = 0;
        for (int frame = 0; frame < TOTAL_FRAMES; frame++) {
            if (isSpare(rollsInFrame)) {
                score += WINNING_FRAME_SCORE + spareScore(rollsInFrame);
                rollsInFrame += 2;
            } else {
                score += rollSumInFrame(rollsInFrame);
                rollsInFrame += 2;
            }
        }
        return score;
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
