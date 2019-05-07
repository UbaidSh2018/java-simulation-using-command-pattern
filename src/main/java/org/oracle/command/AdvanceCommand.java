package org.oracle.command;


import org.oracle.exceptions.InvalidBulldozerMovementException;
import org.oracle.exceptions.InvalidCommandException;
import org.oracle.exceptions.MalformedCommandException;
import org.oracle.io.FileOutput;
import org.oracle.receiver.Bulldozer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * A command to advance the bulldozer forwards towards it's facing direction.
 */
public class AdvanceCommand implements Command {

    private static final String COMMAND = "ADVANCE";

    private static final Pattern PATTERN = compile("^\\s*ADVANCE\\s+(\\d+)\\s*");

    private int param;

    public AdvanceCommand(int param) {
        this.param = param;
    }

    /**
     * @param commandString command string
     * @return <code>true</code> if the contains this command
     * <code>false</code> otherwise
     */
    static boolean containsCommand(String commandString) {
        return commandString.startsWith(AdvanceCommand.COMMAND);
    }


    /**
     * Parse a command string as Advance Command object.
     *
     * @param commandString command string to be parsed
     * @return instance of {@link AdvanceCommand}
     * @throws InvalidCommandException on failure to create Command Object from command string
     */
    static AdvanceCommand createCommandObject(String commandString) throws InvalidCommandException {
        Matcher matcher = PATTERN.matcher(commandString);
        if (matcher.find() && matcher.hitEnd()) {
            int param = parseParam(matcher.group(1));
            return new AdvanceCommand(param);
        } else {
            throw new MalformedCommandException(commandString);
        }
    }

    private static int parseParam(String param) throws NumberFormatException {
        return Integer.parseInt(param);
    }

    @Override
    public void execute(Bulldozer bulldozer) throws InvalidBulldozerMovementException {
        bulldozer.setOnGrid(true);
        FileOutput.getUserEnteredCommands().add(COMMAND + " " + param);
        bulldozer.advance(param);
    }

}
