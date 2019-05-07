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
public class QuitCommandTest {

    @Mock
    private Bulldozer bulldozer;

    private QuitCommand quitCommand;

    @Before
    public void setup() {
        quitCommand = new QuitCommand();
    }

    @Test
    public void should_only_invoke_quit_on_bulldozer_once() {
        quitCommand.execute(bulldozer);
        verify(bulldozer, times(1)).quit();
    }

    @Test
    public void should_not_invoke_anything_else_on_bulldozer() {
        quitCommand.execute(bulldozer);
        verify(bulldozer).quit();
        verifyNoMoreInteractions(bulldozer);
    }

}