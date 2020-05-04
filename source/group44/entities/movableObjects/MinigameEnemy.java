package group44.entities.movableObjects;

import group44.Constants;
import group44.entities.LevelObject;
import group44.entities.cells.*;
import group44.game.CollisionCheckResult;
import group44.game.Level;
import group44.game.scenes.GameScene;

import java.util.ArrayList;

public class MinigameEnemy extends SmartTargetingEnemy {

    public MinigameEnemy(Level level, String title, int positionX, int positionY) {
        super(level, title, positionX, positionY, Constants.MINIGAME_ENEMY_PATH);
    }

    public MinigameEnemy(Level level, String title, int positionX, int positionY, String imagePath) {
        super(level, title, positionX, positionY, imagePath);
    }

    @Override
    protected void onCollided(CollisionCheckResult result) {
        if (result.getCollidingObject() instanceof Player) {
            GameScene.launchMinigame();
            attemptRespawn();
        }
    }

    /**
     * Attempts moving the {@link MinigameEnemy} to a new location to respawn.
     * If there is no new location then kills {@link MinigameEnemy}.
     */
    private void attemptRespawn() {
        //The cell we will respawn at.
        StepableCell newCell = findSpaceOnRightSide();
        //If the new cell is the same as the current one then die (waiter runs away from stress lol).
        if(newCell.equals(this.getStepableCellAtMovableObjectPosition(this))){
            die(getLevel().getPlayer());
            return;
        }
        //Step off the current position
        ((StepableCell) this.getLevel().getGrid()[this.getPositionX()][this
                .getPositionY()]).stepOff();
        newCell.stepOn(this);
        //Change position to a new one
        this.setPosition(newCell.getPositionX(), newCell.getPositionY());
    }

    /**
     * A method which finds some {@link StepableCell} in the right side of the grid. If there is no best cell then it will
     * return any random possible cell. If there is no possible cell then use current cell.
     * @return Some possible {@link StepableCell} to move position to; otherwise current cell.
     */
    private StepableCell findSpaceOnRightSide() {
        //Hold all possible moves
        ArrayList<StepableCell> possibleCells = new ArrayList<>();
        //For each position in the grid check if it is a Ground cell and if does not have a collectible item on it.
        //If true then it is a possible respawn location.
        for (Cell[] row : getLevel().getGrid()) {
            for (Cell position : row) {
                if (position instanceof Ground) {
                    if (!((StepableCell) position).isSteppedOn() && !((Ground) position).hasCollectableItem()) {
                        possibleCells.add((StepableCell) position);
                    }
                }
            }
        }

        //Find a random index in the right side of the grid (half of the arraylist)
        //possibleSize / 2 is the halfway point and desired minimum index.
        int min = possibleCells.size()/2;
        int randomIndex = (int) (Math.random() * ((possibleCells.size() - min) + 1)) + min;

        /*If the random index == size i.e there is no desirable random in the right half of possible moves
        then simply seek for a random move to any possible place*/
        if (randomIndex == possibleCells.size()) {
            randomIndex = (int) (Math.random() * possibleCells.size());
        }
        //If there's no possible moves then return the current cell
        else if(possibleCells.isEmpty()){
            return this.getStepableCellAtMovableObjectPosition(this);
        }
        //Return possible move index
        return possibleCells.get(randomIndex);
    }

    /**
     * Method executed when some other {@link LevelObject} tries to kill the
     * {@link MinigameEnemy}. The minigame enemy will die when the minigame is completed.
     *
     * @param object the {@link LevelObject} trying to kill the {@link MinigameEnemy}.
     */
    @Override
    public void die(LevelObject object) {
        if (object instanceof Player || object instanceof Fire || object instanceof Water) {
            super.die(object);
        }
    }

    /**
     * Returns a string representation of a Minigame Enemy.
     *
     * @return - the string representation of a Minigame Enemy.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(Constants.TYPE_MINIGAME_ENEMY);
        sb.append(Constants.LEVEL_OBJECT_DELIMITER);

        sb.append(this.getTitle());
        sb.append(Constants.LEVEL_OBJECT_DELIMITER);
        sb.append(this.getPositionX());
        sb.append(Constants.LEVEL_OBJECT_DELIMITER);
        sb.append(this.getPositionY());
        sb.append(Constants.LEVEL_OBJECT_DELIMITER);
        sb.append(this.getImagePath());

        return sb.toString();
    }
}