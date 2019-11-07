package com.dius.bowling;

import java.util.logging.Logger;

import com.dius.bowling.exceptions.ExceededMaxPinsException;
import com.dius.bowling.exceptions.InvalidInputException;

public class BowlingGameMain {

    private static final Logger logger = Logger.getLogger(BowlingGameMain.class.getName());

    public static void main(String[] args) {
        
        logger.info("Welcome to the DiUS bowling club!");
        logger.info("Basic cases provided by problem statement are addressed here");

        try {
            bowl(4, 4);
            bowl(4, 6, 5, 0);
            bowl(10, 5, 4);
        } catch (InvalidInputException | ExceededMaxPinsException e) {
            e.printStackTrace();
            logger.severe("Exception occured" + e);
        }
        logger.info("To check the exhaustive list of test cases , please run the test suite");
    }

    private static void bowl(int... rolls) throws InvalidInputException, ExceededMaxPinsException {
        System.out.println();
        logger.info("Starting new game");
        Game game = new GameImpl();
        for ( int i=0; i<rolls.length; i++) {
            logger.info("Roll is " + rolls[i]);
            game.roll(rolls[i]);
        }
        logger.info("Your score is " + game.score());
    }
}
