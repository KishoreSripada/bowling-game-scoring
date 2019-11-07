package com.dius.bowling;

import java.util.ArrayList;
import java.util.List;
                                                                              
public class GameImpl implements Game {
    private boolean isFirstRoll = true;
    // Cater to an additional frame in-case of Spare/Strike in last
    private final List<Frame> frames = new ArrayList<>(11);
    int noOfPinsInFirstRoll = 0;

    public void roll(int pins){
        if ( isFirstRoll )  {
            firstRoll(pins);
        }  else {
            secondRoll(pins);
        } 
    }

    public int score() {
        int score = 0, strikeCount = 0;

        //handle all strike as special scenario
        for (Frame f : frames) {
            if (f.isStrike()) strikeCount++;
        }
        if ( strikeCount == 11) return 300;

        for ( int i=0; i<frames.size();i++) {
            score +=  scorePerFrame(i);
        }
        return score;
    }

    private void secondRoll(int noOfPinsInSecondRoll) {
        frames.add(new Frame(noOfPinsInFirstRoll, noOfPinsInSecondRoll));
        isFirstRoll = true;
        noOfPinsInFirstRoll = 0;
    }

    private void firstRoll(int pins) {
        isFirstRoll = false;
        noOfPinsInFirstRoll = pins;
        // Strike, hence mark second roll to zero
        if (pins == 10) {
            secondRoll(0);
        }
    }

    private int scorePerFrame(int i) {
        int result = frames.get(i).score();
        if (i == 0) {
            return result;
        }
        if (frames.get(i - 1).isSpare()) {
            result += frames.get(i).firstRoll();
        }
        if (frames.get(i - 1).isStrike()) {
            result += frames.get(i).score();
        }
        return result;
    }
}