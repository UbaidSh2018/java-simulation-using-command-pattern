package org.oracle.command;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.oracle.exceptions.InvalidBulldozerMovementException;
import org.oracle.receiver.Bulldozer;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class LeftCommandTest {
    @Mock
    private Bulldozer bulldozer;

    private LeftCommand leftCommand;

    @Before
    public void setup() {
        leftCommand = new LeftCommand();
    }

    @Test
    public void should_only_invoke_turn_left_on_bulldozer_once() throws InvalidBulldozerMovementException {
        leftCommand.execute(bulldozer);
        verify(bulldozer, times(1)).turnLeft();
    }

    @Test
    public void should_fail_when_bulldozer_is_unable_to_turn() throws InvalidBulldozerMovementException {
        InvalidBulldozerMovementException ex = new InvalidBulldozerMovementException("left turn exception");
        doThrow(ex).when(bulldozer).turnLeft();
        try {
            leftCommand.execute(bulldozer);
            fail("Should have failed, since bulldozer is unable to turn");
        } catch (InvalidBulldozerMovementException e) {
            assertThat(e, equalTo(ex));
        }
    }

}
