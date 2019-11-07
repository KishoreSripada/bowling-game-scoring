package com.dius.bowling;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

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
    public void roll4And6And5And0ShouldScore20() throws Exception {
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
    public void allStrikesShouldScore300() throws Exception {
        this.generateMultipleRolls(11, 10);
        assertEquals(300, game.score());
    }

    private void generateMultipleRolls(int times, int noOfPins) {
        for (int i = 0; i < times; i++) {
            game.roll(noOfPins);
        }
    }
}
