package org.oracle.command;

import org.oracle.io.FileOutput;
import org.oracle.receiver.Bulldozer;

/**
 * A command to turn the bulldozer to end the simulation
 */
public class QuitCommand implements Command {

    private static final String COMMAND = "QUIT";


    /**
     * @param commandString command string
     * @return <code>true</code> if the contains this command
     * <code>false</code> otherwise
     */
    static boolean containsCommand(String commandString) {
        return commandString.equalsIgnoreCase(COMMAND);
    }

    /**
     * Create an instance of Quit Command object.
     *
     * @return instance of {@link QuitCommand}
     */
    static QuitCommand createCommandObject() {
        return new QuitCommand();
    }

    public void execute(Bulldozer bulldozer) {
        FileOutput.getUserEnteredCommands().add(COMMAND);
        bulldozer.quit();
    }
}
