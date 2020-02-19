package group44.controllers;

import java.io.IOException;

import group44.entities.cells.Cell;
import group44.exceptions.CellNotFoundException;
import group44.exceptions.PositionTakenException;

public interface ILevelEditor {
    int getGridWidth();
    int getGridHeight();

    void add(int x, int y, Cell cell) throws PositionTakenException;
    void remove(int x, int y) throws CellNotFoundException;
    Cell get(int x, int y);

    void save() throws IOException;
}