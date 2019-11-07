package com.dius.bowling.exceptions;

public class InvalidInputException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 4355542561133715805L;

    public InvalidInputException(String message) {
        super(message);
    }
}