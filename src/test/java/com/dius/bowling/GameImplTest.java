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
}
