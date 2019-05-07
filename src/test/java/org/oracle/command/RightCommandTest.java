package org.oracle.command;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.oracle.exceptions.InvalidBulldozerMovementException;
import org.oracle.receiver.Bulldozer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class RightCommandTest {

    @Mock
    private Bulldozer bulldozer;

    private RightCommand rightCommand;

    @Before
    public void setup() {
        rightCommand = new RightCommand();
    }

    @Test
    public void should_only_invoke_turn_right_on_bulldozer_once() throws InvalidBulldozerMovementException {
        rightCommand.execute(bulldozer);
        verify(bulldozer, times(1)).turnRight();
    }

    @Test
    public void should_fail_when_bulldozer_is_unable_to_turn() throws InvalidBulldozerMovementException {
        InvalidBulldozerMovementException ex = new InvalidBulldozerMovementException("right turn exception");
        doThrow(ex).when(bulldozer).turnRight();
        try {
            rightCommand.execute(bulldozer);
            fail("Should have failed, since bulldozer is unable to turn");
        } catch (InvalidBulldozerMovementException e) {
            assertThat(e, equalTo(ex));
        }
    }

}
