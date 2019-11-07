package com.dius.bowling;

import com.dius.bowling.exceptions.ExceededMaxPinsException;
import com.dius.bowling.exceptions.InvalidInputException;

public interface Game {
    void roll(int pins) throws InvalidInputException, ExceededMaxPinsException;
    int score();
}
