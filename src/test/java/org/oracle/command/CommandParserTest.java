package org.oracle.command;

import org.junit.Before;
import org.junit.Test;
import org.oracle.exceptions.InvalidCommandException;
import org.oracle.exceptions.MalformedCommandException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class CommandParserTest {

    private CommandParser parser;

    @Before
    public void setup() {
        parser = new CommandParser();
    }


    @Test
    public void should_recognize_LEFT_command() throws InvalidCommandException {
        assertThat(parser.createCommand("LEFT"), instanceOf(LeftCommand.class));
        assertThat(parser.createCommand(" LEFT "), instanceOf(LeftCommand.class));
        assertThat(parser.createCommand("LEFT\n"), instanceOf(LeftCommand.class));
        assertThat(parser.createCommand("\tLEFT\t"), instanceOf(LeftCommand.class));
    }

    @Test
    public void should_recognize_RIGHT_command() throws InvalidCommandException {
        assertThat(parser.createCommand("RIGHT"), instanceOf(RightCommand.class));
        assertThat(parser.createCommand(" RIGHT "), instanceOf(RightCommand.class));
        assertThat(parser.createCommand("RIGHT\n"), instanceOf(RightCommand.class));
        assertThat(parser.createCommand("\tRIGHT\t"), instanceOf(RightCommand.class));
    }

    @Test
    public void should_recognize_QUIT_command() throws InvalidCommandException {
        assertThat(parser.createCommand("QUIT"), instanceOf(QuitCommand.class));
        assertThat(parser.createCommand(" QUIT "), instanceOf(QuitCommand.class));
        assertThat(parser.createCommand("QUIT\n"), instanceOf(QuitCommand.class));
        assertThat(parser.createCommand("\tQUIT\t"), instanceOf(QuitCommand.class));
    }

    @Test
    public void should_recognize_ADVANCE_command() throws InvalidCommandException {
        assertThat(parser.createCommand("ADVANCE 4"), instanceOf(AdvanceCommand.class));
        assertThat(parser.createCommand(" ADVANCE 4 "), instanceOf(AdvanceCommand.class));
        assertThat(parser.createCommand("ADVANCE 4\n"), instanceOf(AdvanceCommand.class));
        assertThat(parser.createCommand("\tADVANCE 4\t"), instanceOf(AdvanceCommand.class));
    }

    @Test
    public void should_recognize_HELP_command() throws InvalidCommandException {
        assertThat(parser.createCommand("HELP"), instanceOf(HelpCommand.class));
        assertThat(parser.createCommand(" HELP "), instanceOf(HelpCommand.class));
        assertThat(parser.createCommand("HELP\n"), instanceOf(HelpCommand.class));
        assertThat(parser.createCommand("\tHELP\t"), instanceOf(HelpCommand.class));
    }


    @Test(expected = MalformedCommandException.class)
    public void should_throw_exception_when_given_wrong_command() throws InvalidCommandException {
        parser.createCommand("RANDOM");
    }


}