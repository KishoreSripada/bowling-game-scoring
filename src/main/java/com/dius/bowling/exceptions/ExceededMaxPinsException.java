package com.dius.bowling.exceptions;

public class ExceededMaxPinsException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 4355542561133715805L;

    public ExceededMaxPinsException(String message) {
        super(message);
    }
}