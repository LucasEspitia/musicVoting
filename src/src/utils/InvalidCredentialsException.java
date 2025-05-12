package src.utils;

/**
 * Exception thrown to indicate that the provided credentials are invalid.
 */
public class InvalidCredentialsException extends RuntimeException {
    /**
	 * Default serial version ID.
	 */
	private static final long serialVersionUID = 1L;

    /**
     * Constructs a new InvalidCredentialsException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
