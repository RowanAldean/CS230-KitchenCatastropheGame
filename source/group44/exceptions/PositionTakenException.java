package group44.exceptions;

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
