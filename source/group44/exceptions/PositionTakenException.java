package group44.exceptions;

/**
 * Exception thrown when position is already taken.
 *
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class PositionTakenException extends Exception {

    private static final long serialVersionUID = -5049166644461920289L;

    /**
     * Creates a new instance of {@link PositionTakenException}.
     *
     * @param message
     *            error message
     */
    public PositionTakenException(String message) {
        super(message);
    }
}
