package com.dius.bowling;

class Frame {

    private static final int MAX_PINS = 10;
    private final int firstRoll;
    private final int secondRoll;

    Frame(int firstRoll, int secondRoll) {
        this.firstRoll = firstRoll;
        this.secondRoll = secondRoll;
    }

    int firstRoll() {
        return this.firstRoll;
    }

    int secondRoll() {
        return this.secondRoll;
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
