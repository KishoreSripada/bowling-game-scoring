package com.dius.bowling;

import com.dius.bowling.exceptions.ExceededMaxPinsException;

class Frame {

    private static final int MAX_PINS = 10;
    private final int firstRoll;
    private final int secondRoll;

    Frame(int firstRoll, int secondRoll) throws ExceededMaxPinsException {
        int score = firstRoll + secondRoll;
        if (score > MAX_PINS) {
            throw new ExceededMaxPinsException("Maximum number of pins may not exceed 10: " + firstRoll + " + " + secondRoll);
        }
        this.firstRoll = firstRoll;
        this.secondRoll = secondRoll;
    }

    int firstRoll() {
        return this.firstRoll;
    }

    int score() {
        return this.firstRoll + this.secondRoll;
    }

    boolean isSpare() {
        return !this.isStrike() && (this.firstRoll + this.secondRoll) == MAX_PINS;
    }

    boolean isStrike() {
        return this.firstRoll == MAX_PINS;
    }

}
