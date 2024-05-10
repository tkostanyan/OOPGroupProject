package Casino.casino.user.exceptions;

/**
 * The IllegalPasswordException class represents an exception that is thrown when an illegal password is encountered.
 */
public class IllegalPasswordException extends IllegalUserException {
    /**
     * Constructs an IllegalPasswordException with a default error message.
     */
    public IllegalPasswordException() {
        this("Password must contain at least one uppercase letter, one lowercase letter, one number, and one symbol(@#$%^&+=), and be 8-20 characters long.");
    }

    /**
     * Constructs an IllegalPasswordException with the specified error message.
     *
     * @param message The error message describing the illegal password.
     */
    public IllegalPasswordException(String message) {
        super(message);
    }
}
