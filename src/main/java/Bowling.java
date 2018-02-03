import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;

public class Bowling {

    private static BowlingGame bowlingGame;

    public static void main(String[] args) {
        bowlingGame = new BowlingScorer();
        try {
            File file = new File(Bowling.class.getResource("Input.txt").toURI());
            BufferedReader br = new BufferedReader(new FileReader(file));
            String rolledPinsTotal = br.readLine();
            String[] rolledPins = new String[0];
            if (rolledPinsTotal != null) {
                rolledPins = rolledPinsTotal.split(",");
            }
            for (String rolledPin : rolledPins) {
                bowlingGame.roll(Integer.parseInt(rolledPin));
            }
            System.out.println("********** YOUR SCORE IS: " +  bowlingGame.score() + " **********");
        } catch (IOException | BowlingException | URISyntaxException e) {
            System.out.println(e.getMessage());
        }
    }
}
