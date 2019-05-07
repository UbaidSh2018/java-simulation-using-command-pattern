package org.oracle.command;

import org.oracle.exceptions.InvalidBulldozerMovementException;
import org.oracle.io.FileOutput;
import org.oracle.receiver.Bulldozer;

/**
 * A command to turn the bulldozer (on the spot) 90 degrees to the left of the direction it is facing;
 */
public class LeftCommand implements Command {

    private static final String COMMAND = "LEFT";


    /**
     * @param commandString command string
     * @return <code>true</code> if the contains this command
     * <code>false</code> otherwise
     */
    static boolean containsCommand(String commandString) {
        return commandString.equalsIgnoreCase(COMMAND);
    }


    /**
     * Create an instance of Left Command object.
     * @return instance of {@link LeftCommand}
     */
    static LeftCommand createCommandObject() {
        return new LeftCommand();
    }

    @Override
    public void execute(Bulldozer bulldozer) throws InvalidBulldozerMovementException {
        bulldozer.turnLeft();
        if (bulldozer.isOnGrid()) {
            FileOutput.getUserEnteredCommands().add(COMMAND);
        }
    }
}
