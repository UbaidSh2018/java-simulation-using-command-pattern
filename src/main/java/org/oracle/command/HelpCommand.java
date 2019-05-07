package org.oracle.command;

import org.oracle.receiver.Bulldozer;

/**
 * A command to display the Help Manual
 */
public class HelpCommand implements Command {

    private static final String COMMAND = "HELP";


    /**
     * @param commandString command string
     * @return <code>true</code> if the contains this command
     * <code>false</code> otherwise
     */
    static boolean containsCommand(String commandString) {
        return commandString.equalsIgnoreCase(COMMAND);
    }

    /**
     * Create an instance of Help Command object.
     *
     * @return instance of {@link HelpCommand}
     */
    static HelpCommand createCommandObject() {
        return new HelpCommand();
    }

    @Override
    public void execute(Bulldozer bulldozer) {
        bulldozer.help();
    }
}
