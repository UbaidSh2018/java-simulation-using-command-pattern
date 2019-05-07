package org.oracle.exceptions;


/**
 * Exception to indicate that an unknown command had been issued.
 */
public class MalformedCommandException extends InvalidCommandException {

    /**
     * @param commandString command passed as string
     */
    public MalformedCommandException(String commandString) {
        super(String.format("Unknown command has been given [%s]", commandString));
    }

}
