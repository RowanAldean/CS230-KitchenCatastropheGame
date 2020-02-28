package group44.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;

import group44.Constants;
import group44.entities.cells.Cell;
import group44.exceptions.CellNotFoundException;
import group44.exceptions.CollisionException;
import group44.exceptions.ParsingException;
import group44.exceptions.PositionTakenException;
import group44.game.Level;
import group44.models.LevelInfo;

/**
 * An implementation of the {@link ILevelEditor} interface.
 *
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class LevelEditor implements ILevelEditor {
    private static final String ERROR_POSITION_TAKEN_EXCEPTION_MESSAGE = "Position [%d][%d] is not empty.";
    private static final String ERROR_CELL_NOT_FOUND_EXCEPTION_MESSAGE = "Position [%d][%d] is empty.";

    /**
     * {@link Level} loaded in a {@link LevelEditor}.
     */
    private Level level;

    /**
     * Creates a {@link LevelEditor} and loads a {@link Level}.
     *
     * @param levelId
     *            An Id of a {@link Level} to load.
     * @throws CollisionException
     *             when two cells are at the same position.
     * @throws ParsingException
     *             when trying to parse invalid data type.
     * @throws FileNotFoundException
     *             when level file is not found.
     * @throws IllegalArgumentException
     *             when Level with levelId is not found.
     */
    public LevelEditor(int levelId) throws FileNotFoundException,
            IllegalArgumentException, CollisionException, ParsingException {
        this.level = LevelManager.load(levelId);
    }

    /**
     * Creates a {@link LevelEditor} and a new {@link Level}.
     *
     * @param width
     *            Width of the {@link Level}.
     * @param height
     *            Height of the {@link Level}.
     */
    public LevelEditor(int width, int height) {
        // Get the highest Id
        int id = 0;
        for (LevelInfo info : LevelManager.getLevelInfos()) {
            if (id < info.getId()) {
                id = info.getId();
            }
        }

        this.level = new Level(++id, width, height,
                Constants.LEVEL_DISPLAY_SIZE, 0);
    }

    @Override
    public int getGridWidth() {
        return this.level.getGridWidth();
    }

    @Override
    public int getGridHeight() {
        return this.level.getGridHeight();
    }

    @Override
    public void add(int x, int y, Cell cell) throws PositionTakenException {
        if (this.level.getGrid()[x][y] != null) {
            throw new PositionTakenException(String
                    .format(ERROR_POSITION_TAKEN_EXCEPTION_MESSAGE, x, y));
        }

        this.level.getGrid()[x][y] = cell;
    }

    @Override
    public void remove(int x, int y) throws CellNotFoundException {
        if (this.level.getGrid()[x][y] == null) {
            throw new CellNotFoundException(String
                    .format(ERROR_CELL_NOT_FOUND_EXCEPTION_MESSAGE, x, y));
        }

        this.level.getGrid()[x][y] = null;
    }

    @Override
    public Cell get(int x, int y) {
        return this.level.getGrid()[x][y];
    }

    @Override
    public void save() throws IOException {
        LevelManager.save(this.level);
    }
}
