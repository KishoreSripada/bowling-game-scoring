package com.dius.bowling;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.dius.bowling.exceptions.ExceededMaxPinsException;
import com.dius.bowling.exceptions.InvalidInputException;

public class GameImplTest {
    private Game game;

    @Before
    public void setUp() throws Exception {
        this.game = new GameImpl();
    }

    @Test
    public void gutterGame() throws Exception {
        game.roll(0);
        game.roll(0);
        assertEquals(0, game.score());
    }

    @Test(expected = ExceededMaxPinsException.class)
    public void test_when_roll_is_1_and_10_to_throw_exception() throws Exception {
        game.roll(1);
        game.roll(10);
    }

    @Test
    public void test_when_roll_is_1_and_0_to_score_1() throws Exception {
        game.roll(1);
        game.roll(0);
        assertEquals(1, game.score());
    }

    @Test
    public void test_when_roll_is_4_and_4_to_score_8() throws Exception {
        game.roll(4);
        game.roll(4);
        assertEquals(8, game.score());
    }

    @Test
    public void test_when_4_6_5_0_rolls_to_score_20() throws Exception {
        game.roll(4);
        game.roll(6);
        game.roll(5);
        game.roll(0);
        assertEquals(20, game.score());
    }

    @Test
    public void test_when_10_4_5_rolls_to_score_28() throws Exception {
        game.roll(10);
        game.roll(5);
        game.roll(4);
        assertEquals(28, game.score());
    }

    @Test
    public void test_when_roll_is_1_for_all_frames_to_score_20() throws Exception {
        this.generateMultipleRolls(20, 1);
        assertEquals(20, game.score());
    }

    @Test
    public void test_when_all_rolls_are_strike_to_score_300() throws Exception {
        this.generateMultipleRolls(11, 10);
        assertEquals(300, game.score());
    }

    @Test(expected = InvalidInputException.class)
    public void test_when_more_than_ten_frames() throws Exception {
        this.generateMultipleRolls(21, 1);
    }

    @Test
    public void test_when_spare_in_last_bowl_to_allow_another_bowl() throws Exception {
        this.generateMultipleRolls(19, 1);
        game.roll(9);
        game.roll(1);
        assertEquals(30, game.score());
    }

    @Test(expected = InvalidInputException.class)
    public void test_when_spare_in_last_bowl_not_to_allow_more_than_one_bowl() throws Exception {
        this.generateMultipleRolls(19, 1);
        game.roll(9);
        game.roll(1);
        game.roll(1);
    }

    @Test
    public void test_when_strike_in_last_bowl_to_allow_two_bowls() throws Exception {
        this.generateMultipleRolls(18, 1);
        game.roll(10);
        game.roll(1);
        game.roll(1);
        assertEquals(32, game.score());
    }

    @Test(expected = InvalidInputException.class)
    public void test_when_strike_in_last_bowl_not_to_allow_more_than_two_bowls() throws Exception {
        this.generateMultipleRolls(18, 1);
        game.roll(10);
        game.roll(1);
        game.roll(1);
        game.roll(1);
    }

    private void generateMultipleRolls(int times, int pins) throws InvalidInputException, ExceededMaxPinsException {
        for (int i = 0; i < times; i++) {
            game.roll(pins);
        }
    }
}
