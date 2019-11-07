package com.dius.bowling;

import java.util.ArrayList;
import java.util.List;
import com.dius.bowling.exceptions.ExceededMaxPinsException;
import com.dius.bowling.exceptions.InvalidInputException;

public class GameImpl implements Game {
    private boolean isFirstRoll = true;
    // Cater to an additional frame in-case of Spare/Strike in last
    private final List<Frame> frames = new ArrayList<>(11);
    int noOfPinsInFirstRoll = 0;

    private void validate() throws InvalidInputException {
        // Regular frame.
        if (frames.size() < 10) {
            return;
        }
        // Earned a roll for a spare in 10th frame.
        if (frames.size() == 10 && frames.get(9).isSpare() && isFirstRoll) {
            return;
        }
        // Earned two rolls for a strike in 10th frame.
        if (frames.size() == 10 && frames.get(9).isStrike()) {
            return;
        }
        throw new InvalidInputException("No rolls past frame 10 possible.");
    }

    private void validationsForSpare() throws ExceededMaxPinsException {
        if (frames.size() == 10 && frames.get(9).isSpare() && !isFirstRoll) {
            // Spare only allows one more roll, so simulate empty second roll to complete frame.
            secondRoll(0);
        }
    }

    private void executeRoll(int pins) throws InvalidInputException, ExceededMaxPinsException {
        if (isFirstRoll) {
            firstRoll(pins);
        } else {
            secondRoll(pins);
        }
    }

    public void roll(int pins) throws InvalidInputException, ExceededMaxPinsException {
        validate();
        executeRoll(pins);
        validationsForSpare();
    }

    public int score() {
        int score = 0, strikeCount = 0;

        // handle all strike as special scenario
        for (Frame f : frames) {
            if (f.isStrike())
                strikeCount++;
        }
        if (strikeCount == 11)
            return 300;

        for (int i = 0; i < frames.size(); i++) {
            score += scorePerFrame(i);
        }
        return score;
    }

    private void secondRoll(int noOfPinsInSecondRoll) throws ExceededMaxPinsException {
        frames.add(new Frame(noOfPinsInFirstRoll, noOfPinsInSecondRoll));
        isFirstRoll = true;
        noOfPinsInFirstRoll = 0;
    }

    private void firstRoll(int pins) throws InvalidInputException, ExceededMaxPinsException {
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