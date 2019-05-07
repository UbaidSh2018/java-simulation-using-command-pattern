package org.oracle.command;


import org.oracle.exceptions.InvalidBulldozerMovementException;
import org.oracle.receiver.Bulldozer;

/**
 * Interface to all commands that can be executed on the {@link Bulldozer}
 */
public interface Command {

    /**
     * Execute the command on Bulldozer
     * <p/>
     *
     * @param bulldozer {@link Bulldozer}
     * @throws InvalidBulldozerMovementException when the command cannot be applied to the bulldozer.
     */
    void execute(Bulldozer bulldozer) throws InvalidBulldozerMovementException;
}
