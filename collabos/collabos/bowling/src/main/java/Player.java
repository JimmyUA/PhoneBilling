import static java.util.Arrays.*;

public class Player {
    private Game game;

    public Player() {
        game = new Game();
    }

    public void roll(int pins) {
        game.roll(pins);
    }

    public int getScore() {
        return game.score();
    }

    public int[] getScoresByFrameNumber(int frameNumber) {
        int[] rolls = game.getRolls();
        int rollsInFrames = frameNumber * 2;
        int[] scores = copyOfRange(rolls, rollsInFrames - 2 , rollsInFrames);
        return scores;
    }
}
