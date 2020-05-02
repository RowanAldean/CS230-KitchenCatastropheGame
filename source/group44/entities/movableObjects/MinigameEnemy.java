package group44.entities.movableObjects;

import group44.Constants;
import group44.entities.LevelObject;
import group44.entities.cells.Fire;
import group44.entities.cells.Water;
import group44.game.CollisionCheckResult;
import group44.game.Level;
import group44.game.scenes.GameScene;

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
        }
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