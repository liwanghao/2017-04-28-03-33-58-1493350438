public class BowlingGame {

    public int getBowlingScore(String bowlingCode) {
        // a miss means get 0 score
        bowlingCode = bowlingCode.replace('-', '0');

        String[] frames = bowlingCode.split("\\|+");
        int[] scores = new int[10];
        int score = 0;

        for (int i = 9; i >= 0; i--) {
            scores[i] = getFrameScore(frames, i);
        }
        for (int i = 0; i < scores.length; i++) {
            score += scores[i];
        }
        return score;
    }

    private int getFrameScore(String[] frames, int currentIndex) {
        int score = 0;
        switch (frames[currentIndex].length()) {
            case 1:
                // this frame is a strike
                score += 10;
                score += getBallScore(frames, currentIndex + 1, 2);
                break;
            case 2:
                if (frames[currentIndex].charAt(1) == '/') {
                    // this frame is a spare
                    score += 10;
                    score += getBallScore(frames, currentIndex + 1, 1);
                } else {
                    // this frame have a miss
                    score += frames[currentIndex].charAt(0) - '0' + frames[currentIndex].charAt(1) - '0';
                }
                break;
        }
        return score;
    }

    private int getBallScore(String[] frames, int index, int balls) {
        int score = 0;

        if (balls == 1) {
            score += frames[index].charAt(0) == 'X' ? 10 : frames[index].charAt(0) - '0';
            return score;
        }

        // ball=2
        if (frames[index].length() == 2) {
            // good
            if (frames[index].charAt(1) == '/') {
                // this frame is spare
                score += 10;
                // done
            } else {
                // XX or like 81,72
                for (int i = 0; i < frames[index].length(); i++) {
                    score += frames[index].charAt(i) == 'X' ? 10 : frames[index].charAt(i) - '0';
                }
            }
            // can return a score
        } else {
            // this is a strike, go to next frame find a score
            score += 10;
            score += getBallScore(frames, index + 1, 1);
        }

        return score;
    }

}
