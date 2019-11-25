package group44.entities;

/**
 * Represents {@link FireBoots} in the game.
 *
 * @author Tomas Svejnoha, Rowan Aldean
 * @version 1.0
 */
public class FireBoots extends CollectibleItem {
    /**
     * Creates a new instance of {@link FireBoots} with position, and image.
     * 
     * @param positionX - Position X in the game
     * @param positionY - Position Y in the game
     * @param imagePath - Image path of the instance
     */
    public FireBoots(int positionX, int positionY, String imagePath) {
        super("Fireboots", positionX, positionY, imagePath);
    }
}
