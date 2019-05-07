package org.oracle.receiver;

import org.junit.Before;
import org.junit.Test;
import org.oracle.exceptions.InvalidBulldozerMovementException;
import org.oracle.site.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class BulldozerAdvanceTest {

    private Bulldozer bulldozer;

    @Before
    public void setUp() {
        Square[][] site = new Square[][]{
                {new PlainSquare(), new RockySquare(), new PlainSquare(), new PlainSquare(), new PlainSquare(), new RockySquare(), new ProtectedSquare()},
                {new TreeSquare(), new RockySquare(), new TreeSquare(), new PlainSquare(), new PlainSquare(), new RockySquare(), new ProtectedSquare()},
                {new PlainSquare(), new RockySquare(), new PlainSquare(), new PlainSquare(), new PlainSquare(), new RockySquare(), new RockySquare()},
                {new PlainSquare(), new RockySquare(), new PlainSquare(), new PlainSquare(), new PlainSquare(), new RockySquare(), new ProtectedSquare()},
                {new TreeSquare(), new RockySquare(), new TreeSquare(), new PlainSquare(), new PlainSquare(), new RockySquare(), new ProtectedSquare()}
        };

        bulldozer = new Bulldozer(site);
        bulldozer.setOnGrid(true);
        bulldozer.setX(0);
        bulldozer.setY(0);
    }

    //
    @Test
    public void WHEN_facing_north_check_destination_XY_for_valid_coordinates() throws InvalidBulldozerMovementException {
        //given
        bulldozer.setFacingDirection(Direction.NORTH);
        bulldozer.setX(2);
        bulldozer.setY(4);
        //when
        bulldozer.advance(3);
        // then
        assertThat(bulldozer.getDestX(), equalTo(2));
        assertThat(bulldozer.getDestY(), equalTo(1));
        assertThat("Bulldozer should not change facing direction when moving forward", bulldozer.getFacingDirection(), equalTo(Direction.NORTH));

    }

    @Test
    public void WHEN_facing_east_check_destination_XY_for_valid_coordinates() throws InvalidBulldozerMovementException {
        //given
        bulldozer.setFacingDirection(Direction.EAST);
        bulldozer.setX(2);
        bulldozer.setY(2);
        //when
        bulldozer.advance(4);
        // then
        assertThat(bulldozer.getDestX(), equalTo(6));
        assertThat(bulldozer.getDestY(), equalTo(2));
        assertThat("Bulldozer should not change facing direction when moving forward", bulldozer.getFacingDirection(), equalTo(Direction.EAST
        ));
    }

    @Test
    public void WHEN_facing_south_check_destination_XY_for_valid_coordinates() throws InvalidBulldozerMovementException {
        //given
        bulldozer.setFacingDirection(Direction.SOUTH);
        bulldozer.setX(2);
        bulldozer.setY(1);
        //when
        bulldozer.advance(3);
        // then

        assertThat(bulldozer.getDestX(), equalTo(2));
        assertThat(bulldozer.getDestY(), equalTo(4));
        assertThat("Bulldozer should not change facing direction when moving forward", bulldozer.getFacingDirection(), equalTo(Direction.SOUTH
        ));
    }

    @Test
    public void WHEN_facing_west_check_destination_XY_for_valid_coordinates() throws InvalidBulldozerMovementException {
        //given
        bulldozer.setFacingDirection(Direction.WEST);
        bulldozer.setX(6);
        bulldozer.setY(2);
        //when
        bulldozer.advance(6);
        // then

        assertThat(bulldozer.getDestX(), equalTo(0));
        assertThat(bulldozer.getDestY(), equalTo(2));
        assertThat("Bulldozer should not change facing direction when moving forward", bulldozer.getFacingDirection(), equalTo(Direction.WEST
        ));
    }




}
