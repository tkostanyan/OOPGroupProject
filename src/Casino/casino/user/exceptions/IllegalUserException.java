package Casino.casino.user.exceptions;

/**
 * The IllegalUserException class represents an exception that is thrown when illegal user information is encountered.
 */
public class IllegalUserException extends Exception {
    /**
     * Constructs an IllegalUserException with a default error message.
     */
    public IllegalUserException() {
        this("Illegal user information. Please, try again.");
    }

    /**
     * Constructs an IllegalUserException with the specified error message.
     *
     * @param message The error message describing the illegal user information.
     */
    public IllegalUserException(String message) {
        super(message);
    }

}
