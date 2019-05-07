package org.oracle.receiver;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GridTest {

    private Grid grid;

    @Before
    public void setUp() {
        grid = new Grid(10, 5);
    }

    @Test
    public void isValidPosition_should_return_false_when_given_negative_x() {
        assertFalse(grid.isValidPosition(-1, 0));
    }

    @Test
    public void isValidPosition_should_return_false_when_given_negative_y() {
        assertFalse(grid.isValidPosition(0, -1));
    }

    @Test
    public void isValidPosition_should_return_false_when_given_coordinate_beyond_the_width() {
        assertFalse(grid.isValidPosition(10, 0));
    }

    @Test
    public void isValidPosition_should_return_false_when_given_coordinate_beyond_the_height() {
        assertFalse(grid.isValidPosition(0, 10));
    }

    @Test
    public void isValidPosition_should_return_true_when_given_valid_coordinate() {
        assertTrue(grid.isValidPosition(0, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_allow_negative_value_for_grid_width() {
        new Grid(-1, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_allow_negative_value_for_grid_height() {
        new Grid(1, -5);
    }

}