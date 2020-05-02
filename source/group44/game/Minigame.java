package group44.game;

import group44.entities.cells.Cell;
import javafx.scene.canvas.GraphicsContext;

public class Minigame {

    /**
     * Draws the cell in the active game area.
     *
     * @param gc
     *            {@link GraphicsContext} to which the game is drawn
     */
    public void draw(GraphicsContext gc) {
        //TODO: Draw the game on the gc canvas.
//        double cellWidth = gc.getCanvas().getWidth() / this.displaySize;
//        double cellHeight = gc.getCanvas().getHeight() / this.displaySize;
//        double cellSize = Math.min(cellWidth, cellHeight);
//
//        Area activeArea = this.getActiveArea();
//
//        Cell[][] area = getActiveAreaCell(activeArea);
//        int areaWidth = area.length;
//        int areaHeight = area[0].length;
//
//        for (int x = 0; x < areaWidth; x++) {
//            for (int y = 0; y < areaHeight; y++) {
//
//                Cell cell = area[x][y];
//                if (cell != null) {
//                    cell.draw(gc, x * cellSize, y * cellSize, cellSize,
//                            cellSize);
//                }
//            }
//        }
    }

}
