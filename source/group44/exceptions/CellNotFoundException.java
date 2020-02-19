package group44.exceptions;

public class CellNotFoundException extends Exception {
    private static final long serialVersionUID = 1847763308801426611L;

    /**
     * Creates a new instance of {@link CellNotFoundException}.
     *
     * @param message
     *            error message
     */
    public CellNotFoundException(String message) {
        super(message);
    }
}
