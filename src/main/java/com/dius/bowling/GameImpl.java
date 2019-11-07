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
        int score = 0;
        for ( Frame f : frames) {
            score +=  f.score();
        }
        System.out.println("score is " + score);
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
}