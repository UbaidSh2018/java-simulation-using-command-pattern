package org.oracle.command;

import org.apache.log4j.Logger;
import org.oracle.exceptions.InvalidCommandException;
import org.oracle.exceptions.MalformedCommandException;

/**
 * Parser to create {@link Command} from a string.
 * A parser is a compiler or interpreter component that breaks data into smaller elements.
 */
public class CommandParser {

    private static final Logger LOGGER = Logger.getLogger(CommandParser.class);

    /**
     * Parse a string as a {@link Command}
     * <p/>
     *
     * @param commandString string representation of the command
     * @return {@link Command} object that represents the passed in string
     */
    public Command createCommand(String commandString) throws InvalidCommandException {

        String trimmedString = commandString.trim().toUpperCase();

        if (AdvanceCommand.containsCommand(trimmedString)) {
            return AdvanceCommand.createCommandObject(trimmedString);
        } else if (LeftCommand.containsCommand(trimmedString)) {
            return LeftCommand.createCommandObject();
        } else if (RightCommand.containsCommand(trimmedString)) {
            return RightCommand.createCommandObject();
        } else if (QuitCommand.containsCommand(trimmedString)) {
            return QuitCommand.createCommandObject();
        } else if (HelpCommand.containsCommand(trimmedString)) {
            return HelpCommand.createCommandObject();
        } else {
            LOGGER.warn("Unknown Command given");
            throw new MalformedCommandException(commandString);
        }
    }

}
