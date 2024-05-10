package Casino.casino.user.exceptions;

/**
 * The IllegalUsernameException class represents an exception that is thrown when an illegal username is encountered.
 */
public class IllegalUsernameException extends IllegalUserException {
    /**
     * Constructs an IllegalUsernameException with a default error message.
     */
    public IllegalUsernameException() {
        this("Username can only contain uppercase and lowercase letters, numbers, and a few symbols (!@#$%^&*).");
    }

    /**
     * Constructs an IllegalUsernameException with the specified error message.
     *
     * @param message The error message describing the illegal username.
     */
    public IllegalUsernameException(String message) {
        super(message);
    }
}
