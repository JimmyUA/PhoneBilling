public class Game {
    private int[] rolls = new int[21];
    private int currentRoll = 0;

    public void roll(int pins) {
        rolls[currentRoll++] = pins;
    }

    public int score() {
        int score = 0;
        int firstInFrame = 0;
        int framesAmount = 10;
        for (int frame = 0; frame < framesAmount; frame++) {
            if (isStrike(firstInFrame)){
                score += 10 + ballsAfterStrike(firstInFrame);
                firstInFrame++;
            }
            else if (isSpare(firstInFrame)){
                score += 10 + ballAfterSpare(firstInFrame);
                firstInFrame += 2;
            }
            else {
                score += ballsInFrame(firstInFrame);
                firstInFrame += 2;
            }
        }
        return score;
    }

    public int[] getRolls(){
        return rolls;
    }

    private int ballsInFrame(int firstInFrame) {
        return rolls[firstInFrame] + rolls[firstInFrame + 1];
    }

    private int ballAfterSpare(int firstInFrame) {
        return rolls[firstInFrame + 2];
    }

    private int ballsAfterStrike(int firstInFrame) {
        return rolls[firstInFrame + 1] + rolls[firstInFrame + 2];
    }

    private boolean isStrike(int firstInFrame) {
        return  rolls[firstInFrame] == 10;
    }

    private boolean isSpare(int firstInFrame) {
        return rolls[firstInFrame] + rolls[firstInFrame + 1] == 10;
    }
}
