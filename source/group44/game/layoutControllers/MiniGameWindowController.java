package group44.game.layoutControllers;

import group44.models.LevelObjectImage;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.HashMap;

import static group44.Constants.*;

public class MiniGameWindowController {

    public HashMap<String, String> imagePaths = new HashMap<String, String>() {
        {
            put(MINIGAME_BOTTOM_BUN_PATH, MINIGAME_SELECTED_BOTTOM_BUN_PATH);
            put(MINIGAME_TOMATO_PATH, MINIGAME_SELECTED_TOMATO_PATH);
            put(MINIGAME_LETTUCE_PATH, MINIGAME_SELECTED_LETTUCE_PATH);
            put(MINIGAME_BURGER_PATH, MINIGAME_SELECTED_BURGER_PATH);
            put(MINIGAME_TOP_BUN_PATH, MINIGAME_SELECTED_TOP_BUN_PATH);
        }
    };

    @FXML
    private VBox burgerSelect;

    public void initialize() {
        placeRandomBurgerParts();
    }

    private void placeRandomBurgerParts() {
        ImageView[] ingredientViews = generateBurgerPartImages();
        for (ImageView ingredient : ingredientViews) {
            burgerSelect.getChildren().add(ingredient);
        }
    }

    private ImageView[] generateBurgerPartImages() {
        final String[] ingredientPaths = imagePaths.keySet().toArray(new String[0]);
        ImageView[] burgerParts = new ImageView[5];
        for (int i = 0; i < burgerParts.length; i++) {
            File ingredientFile = new File(ingredientPaths[i]);
            LevelObjectImage ingredientImage = new LevelObjectImage(ingredientFile.toURI().toString(), ingredientPaths[i]);
            ImageView ingredient = new ImageView(ingredientImage);
            burgerParts[i] = ingredient;
        }
        return burgerParts;
    }

    public VBox getBurgerSelect() {
        return burgerSelect;
    }
}
