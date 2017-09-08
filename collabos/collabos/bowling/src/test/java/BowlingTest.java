import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BowlingTest {
    private Game game;

    @Before
    public void init(){
        game = new Game();
    }

    @Test
    public void gutterGame(){
        rollTimes(20, 0);
        assertEquals(0, game.score());
    }

    @Test
    public void allOnes(){
        rollTimes(20, 1);
        assertEquals(20, game.score());
    }

    @Test
    public void oneSpare() throws Exception {
        rollSpare();
        game.roll(1);
        rollTimes(17, 0);

        assertEquals(12, game.score());
    }

    @Test
    public void oneStrike() throws Exception {
        rollStrike();
        game.roll(2);
        game.roll(3);
        rollTimes(16, 0);

        assertEquals(20, game.score());
    }

    @Test
    public void perfectGame() throws Exception {
        int perfectGameScore = 300;
        rollTimes(12, 10);
        assertEquals(perfectGameScore, game.score());
    }

    private void rollStrike() {
        game.roll(10);
    }

    private void rollSpare() {
        game.roll(5);
        game.roll(5); //spare
    }

    private void rollTimes(int n, int pins) {
        for (int i = 0; i < n; i++) {
            game.roll(pins);
        }
    }
}
