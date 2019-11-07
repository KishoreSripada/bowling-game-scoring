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
}
