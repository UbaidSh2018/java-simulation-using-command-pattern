package org.oracle.command;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.oracle.exceptions.InvalidBulldozerMovementException;
import org.oracle.exceptions.InvalidCommandException;
import org.oracle.exceptions.MalformedCommandException;
import org.oracle.receiver.Bulldozer;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class AdvanceCommandTest {

    @Mock
    private Bulldozer bulldozer;

    private AdvanceCommand advanceCommand;

    private int param;

    @Before
    public void setUp() {
        param = 4;
        advanceCommand = new AdvanceCommand(param);
    }

    @Test
    public void should_only_invoke_functions_inside_execute_on_bulldozer_once() throws InvalidBulldozerMovementException {
        advanceCommand.execute(bulldozer);
        verify(bulldozer, times(1)).setOnGrid(true);
        verify(bulldozer, times(1)).advance(param);
    }

    @Test
    public void should_not_invoke_anything_else_on_bulldozer() throws InvalidBulldozerMovementException {
        advanceCommand.execute(bulldozer);
        verify(bulldozer).setOnGrid(true);
        verify(bulldozer).advance(param);
        verifyNoMoreInteractions(bulldozer);
    }


    @Test
    public void should_fail_when_bulldozer_is_unable_to_advance() throws InvalidBulldozerMovementException {
        InvalidBulldozerMovementException ex = new InvalidBulldozerMovementException("advance exception");
        doThrow(ex).when(bulldozer).advance(param);
        try {
            advanceCommand.execute(bulldozer);
            fail("Should have failed, since bulldozer is unable to advance");
        } catch (InvalidBulldozerMovementException e) {
            assertThat(e, equalTo(ex));
        }
    }

    @Test(expected = MalformedCommandException.class)
    public void should_fail_to_create_advance_command_if_invalid_param() throws InvalidCommandException {
        AdvanceCommand.createCommandObject("ADVANCE EE");
    }

    
}