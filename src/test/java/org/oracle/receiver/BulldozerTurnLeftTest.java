package org.oracle.receiver;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.oracle.exceptions.InvalidBulldozerMovementException;
import org.oracle.site.*;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.oracle.receiver.Direction.EAST;

@RunWith(Parameterized.class)
public class BulldozerTurnLeftTest {

    private Direction initialDirection;

    private Direction expectedDirection;

    private Bulldozer bulldozer;

    private Square[][] site;

    public BulldozerTurnLeftTest(Direction initialDirection, Direction expectedDirection) {
        this.initialDirection = initialDirection;
        this.expectedDirection = expectedDirection;
        site = new Square[][]
                {
                        {new PlainSquare(), new RockySquare(), new PlainSquare(), new ProtectedSquare(), new PlainSquare()},
                        {new TreeSquare(), new RockySquare(), new TreeSquare(), new ProtectedSquare(), new ProtectedSquare()},
                        {new PlainSquare(), new RockySquare(), new PlainSquare(), new ProtectedSquare(), new PlainSquare()}
                };
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
                {Direction.EAST, Direction.NORTH},
                {Direction.NORTH, Direction.WEST},
                {Direction.WEST, Direction.SOUTH},
                {Direction.SOUTH, EAST},
        };
        return asList(data);
    }

    @Before
    public void setUp() {
        bulldozer = new Bulldozer(site);
        bulldozer.setOnGrid(true);
    }

    @Test
    public void turn_left_test() throws InvalidBulldozerMovementException {
        bulldozer.setFacingDirection(initialDirection);
        bulldozer.turnLeft();
        assertThat(
                "Turning Left from " + initialDirection + " should change direction to " + expectedDirection,
                bulldozer.getFacingDirection(),
                equalTo(expectedDirection)
        );
    }


}
