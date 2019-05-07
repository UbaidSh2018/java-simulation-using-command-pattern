package org.oracle.receiver;

import org.junit.Before;
import org.junit.Test;
import org.oracle.exceptions.InvalidBulldozerMovementException;
import org.oracle.site.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class BulldozerAdvanceOneSquareTest {

    private Bulldozer bulldozer;

    @Before
    public void setUp() {
        Square[][] site = new Square[][]{
                {new PlainSquare(), new RockySquare(), new PlainSquare(), new PlainSquare(), new PlainSquare(), new RockySquare(), new ProtectedSquare()},
                {new TreeSquare(), new RockySquare(), new TreeSquare(), new PlainSquare(), new PlainSquare(), new RockySquare(), new ProtectedSquare()},
                {new PlainSquare(), new RockySquare(), new PlainSquare(), new PlainSquare(), new PlainSquare(), new RockySquare(), new ProtectedSquare()},
                {new PlainSquare(), new RockySquare(), new PlainSquare(), new PlainSquare(), new PlainSquare(), new RockySquare(), new ProtectedSquare()},
                {new TreeSquare(), new RockySquare(), new TreeSquare(), new PlainSquare(), new PlainSquare(), new RockySquare(), new ProtectedSquare()}
        };

        bulldozer = new Bulldozer(site);
        bulldozer.setOnGrid(true);
        bulldozer.setX(2);
        bulldozer.setY(2);
    }

    @Test
    public void WHEN_facing_north__THEN__advancing_forward_should_move_bulldozer_by_one_unit_in_same_direction() throws InvalidBulldozerMovementException {
        //given
        bulldozer.setFacingDirection(Direction.NORTH);
        bulldozer.setX(2);
        bulldozer.setY(2);
        //when
        bulldozer.advanceOneSquare();
        // then
        assertThat(bulldozer.getX(), equalTo(2));
        assertThat(bulldozer.getY(), equalTo(1));
        assertThat("Bulldozer should not change facing direction when moving forward", bulldozer.getFacingDirection(), equalTo(Direction.NORTH));

    }


    @Test
    public void WHEN_facing_south__THEN__advancing_forward_should_move_bulldozer_by_one_unit_in_same_direction() throws InvalidBulldozerMovementException {
        //given
        bulldozer.setFacingDirection(Direction.SOUTH);
        bulldozer.setX(2);
        bulldozer.setY(2);
        //when
        bulldozer.advanceOneSquare();
        // then
        assertThat(bulldozer.getX(), equalTo(2));
        assertThat(bulldozer.getY(), equalTo(3));
        assertThat("Bulldozer should not change facing direction when moving forward", bulldozer.getFacingDirection(), equalTo(Direction.SOUTH));

    }

    @Test
    public void WHEN_facing_west__THEN__advancing_forward_should_move_bulldozer_by_one_unit_in_same_direction() throws InvalidBulldozerMovementException {
        //given
        bulldozer.setFacingDirection(Direction.WEST);
        bulldozer.setX(2);
        bulldozer.setY(2);
        //when
        bulldozer.advanceOneSquare();
        // then
        assertThat(bulldozer.getX(), equalTo(1));
        assertThat(bulldozer.getY(), equalTo(2));
        assertThat("Bulldozer should not change facing direction when moving forward", bulldozer.getFacingDirection(), equalTo(Direction.WEST));

    }

    @Test(expected = InvalidBulldozerMovementException.class)
    public void should_not_allow_to_move_forward_if_bulldozer_is_not_on_site() throws InvalidBulldozerMovementException {
        bulldozer.setOnGrid(false);
        bulldozer.advanceOneSquare();
    }


}
