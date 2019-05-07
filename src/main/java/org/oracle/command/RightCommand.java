package org.oracle.command;

import org.oracle.exceptions.InvalidBulldozerMovementException;
import org.oracle.io.FileOutput;
import org.oracle.receiver.Bulldozer;

/**
 * A command to turn the bulldozer (on the spot) 90 degrees to the right of the direction it is facing;
 */
public class RightCommand implements Command {
    private static final String COMMAND = "RIGHT";

    /**
     * @param commandString command string
     * @return <code>true</code> if the contains this command
     * <code>false</code> otherwise
     */
    static boolean containsCommand(String commandString) {
        return commandString.equalsIgnoreCase(COMMAND);
    }

    /**
     * Create an instance of Right Command object.
     *
     * @return instance of {@link RightCommand}
     */
    static RightCommand createCommandObject() {
        return new RightCommand();
    }

    @Override
    public void execute(Bulldozer bulldozer) throws InvalidBulldozerMovementException {
        bulldozer.turnRight();
        if (bulldozer.isOnGrid()) {
            FileOutput.getUserEnteredCommands().add(COMMAND);
        }
    }
}

