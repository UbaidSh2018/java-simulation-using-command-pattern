package org.oracle.exceptions;

/**
 * Abstraction of all exceptions that indicate invalid command had been given by the user
 */
public abstract class InvalidCommandException extends Exception {
    public InvalidCommandException(String message) {
        super(message);
    }
}
