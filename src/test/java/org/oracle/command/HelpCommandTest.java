package org.oracle.command;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.oracle.receiver.Bulldozer;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.internal.verification.VerificationModeFactory.times;


@RunWith(MockitoJUnitRunner.class)
public class HelpCommandTest {

    @Mock
    private Bulldozer bulldozer;

    private HelpCommand helpCommand;

    @Before
    public void setup() {
        helpCommand = new HelpCommand();
    }

    @Test
    public void should_only_invoke_help_once() {
        helpCommand.execute(bulldozer);
        verify(bulldozer, times(1)).help();
    }

    @Test
    public void should_not_invoke_anything_else_on_bulldozer() {
        helpCommand.execute(bulldozer);
        verify(bulldozer).help();
        verifyNoMoreInteractions(bulldozer);
    }


}