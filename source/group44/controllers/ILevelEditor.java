package group44.controllers;

import java.io.IOException;

import group44.entities.cells.Cell;
import group44.exceptions.CellNotFoundException;
import group44.exceptions.PositionTakenException;
import group44.game.Level;

/**
 * Interface for {@link LevelEditor}.
 *
 * @author Tomas Svejnoha
 * @version 1.0
 */
public interface ILevelEditor {
    /**
     * Returns the width of the edited {@link Level}.
     *
     * @return the width.
     */
    int getGridWidth();

    /**
     * Returns the height of the edited {@link Level}.
     *
     * @return the height.
     */
    int getGridHeight();

    /**
     * Returns the currently edited {@link Level}.
     *
     * @return the level.
     */
    Level getLevel();

    /**
     * Adds the {@link Cell} to a specific location in the {@link Level}.
     *
     * @param x
     *            The X coordinate.
     * @param y
     *            The Y coordinate.
     * @param cell
     *            The cell to add.
     * @throws PositionTakenException
     *             when the position is not empty.
     */
    void add(int x, int y, Cell cell) throws PositionTakenException;

    /**
     * Removes cell from the position.
     *
     * @param x
     *            The X coordinate.
     * @param y
     *            The Y coordinate.
     */
    void remove(int x, int y);

    /**
     * Returns the {@link Cell} at a specific position.
     *
     * @param x
     *            The X coordinate.
     * @param y
     *            The Y coordinate.
     * @return the {@link Cell} at the position.
     */
    Cell get(int x, int y);

    /**
     * Saves the edited {@link Level}.
     *
     * @throws IOException
     *             when saving failed.
     */
    void save() throws IOException;
}
