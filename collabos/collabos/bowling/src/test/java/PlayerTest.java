import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


public class PlayerTest {
    private Player player;


    @Before
    public void init() {
        player = new Player();
    }

    private void rollFrameSimetric(int pins) {
        player.roll(pins);
        player.roll(pins);
    }

    private void rollTimes(int n, int pins) {
        for (int i = 0; i < n; i++) {
            player.roll(pins);
        }
    }

    @Test
    public void playerRollsZero() throws Exception {
        int pins = 0;
        player.roll(pins);
        assertEquals(pins, player.getScore());
    }

    @Test
    public void playerRollsOne() throws Exception {
        int pins = 1;
        player.roll(pins);
        assertEquals(pins, player.getScore());
    }



    @Test
    public void playerRollsTwoZero() throws Exception {
        int[] twoZeroFrameScore = {0, 0};
        int pins = 0;
        rollFrameSimetric(pins);


        assertArrayEquals(twoZeroFrameScore, player.getScoresByFrameNumber(1));
        assertEquals(pins * 2, player.getScore());
    }

    @Test
    public void playerRollsTwoOnes() throws Exception {
        int[] twoOnesFrameScore = {1, 1};
        int pins = 1;
        rollFrameSimetric(pins);

        assertArrayEquals(twoOnesFrameScore, player.getScoresByFrameNumber(1));
        assertEquals(pins * 2, player.getScore());
    }

    @Test
    public void secondFrameInGutterGame() throws Exception {
        rollTimes(20, 0);

        int gutterGameScore = 0;
        int[] twoZeroFrameScore = {0, 0};

        assertArrayEquals(twoZeroFrameScore, player.getScoresByFrameNumber(2));
        assertEquals(gutterGameScore, player.getScore());
    }

    @Test
    public void sevensFrameInGutterGame() throws Exception {
        rollTimes(20, 0);

        int gutterGameScore = 0;
        int[] twoZeroFrameScore = {0, 0};

        assertArrayEquals(twoZeroFrameScore, player.getScoresByFrameNumber(7));
        assertEquals(gutterGameScore, player.getScore());
    }

    @Test
    public void fifthFrameInPerfectGame() throws Exception {
        rollTimes(12, 10);

        int gutterGameScore = 0;
        int[] twoZeroFrameScore = {0, 0};

        assertArrayEquals(twoZeroFrameScore, player.getScoresByFrameNumber(2));
        assertEquals(gutterGameScore, player.getScore());
    }
}
