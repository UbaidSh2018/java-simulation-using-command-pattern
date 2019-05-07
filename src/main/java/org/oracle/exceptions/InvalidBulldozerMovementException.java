package org.oracle.exceptions;

/**
 * Exception to indicate that a movement cannot be applied to the bulldozer.
 * Generally because the bulldozer is not on the construction site yet.
 */
public class InvalidBulldozerMovementException extends InvalidCommandException {

    public InvalidBulldozerMovementException(String reason) {
        super(reason);
    }
}
