package group44.game.layoutControllers;

import group44.models.LevelObjectImage;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.*;

import static group44.Constants.*;

public class MiniGameWindowController {

    public HashMap<String, String> mapToHighlights = new HashMap<String, String>() {
        {
            put(MINIGAME_BOTTOM_BUN_PATH, MINIGAME_SELECTED_BOTTOM_BUN_PATH);
            put(MINIGAME_TOMATO_PATH, MINIGAME_SELECTED_TOMATO_PATH);
            put(MINIGAME_LETTUCE_PATH, MINIGAME_SELECTED_LETTUCE_PATH);
            put(MINIGAME_BURGER_PATH, MINIGAME_SELECTED_BURGER_PATH);
            put(MINIGAME_TOP_BUN_PATH, MINIGAME_SELECTED_TOP_BUN_PATH);
        }
    };

    public HashMap<String, String> mapToOriginal = new HashMap<String, String>() {
        {
            put(MINIGAME_SELECTED_BOTTOM_BUN_PATH, MINIGAME_BOTTOM_BUN_PATH);
            put(MINIGAME_SELECTED_TOMATO_PATH, MINIGAME_TOMATO_PATH);
            put(MINIGAME_SELECTED_LETTUCE_PATH, MINIGAME_LETTUCE_PATH);
            put(MINIGAME_SELECTED_BURGER_PATH, MINIGAME_BURGER_PATH);
            put(MINIGAME_SELECTED_TOP_BUN_PATH, MINIGAME_TOP_BUN_PATH);
        }
    };

    @FXML
    private VBox burgerSelect;

    @FXML
    private Label timerLabel;


    public void initialize() {
        placeRandomBurgerParts();
    }


    private void setupUI() {
        //Add listeners to the VBox nodes so they appear selected.
        for (Node childNode : burgerSelect.getChildren()) {
            ImageView childImage = (ImageView) childNode;
            childImage.focusedProperty().addListener(focusedIngredientListener(childImage));
        }
    }

    private ChangeListener<? super Boolean> focusedIngredientListener(ImageView childImage) {
        return (ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            //Get the current image for the focused node.
            LevelObjectImage ingredientImage = (LevelObjectImage) childImage.getImage();
            //If its focused then use the highlights map to get the highlight image.
            String imagePath = newValue ? mapToHighlights.get(ingredientImage.getLabel()) : mapToOriginal.get(ingredientImage.getLabel());
            //Use a file to find the new image path.
            File newImageFile = new File(imagePath);
            //Set the image to the new focused/unfocused image.
            childImage.setImage(new LevelObjectImage(newImageFile.toURI().toString(), imagePath));
        };
    }

    public void placeRandomBurgerParts() {
        burgerSelect.getChildren().clear();
        ImageView[] ingredientViews = generateBurgerPartImages();
        for (ImageView ingredient : ingredientViews) {
            ingredient.setFocusTraversable(true);
            burgerSelect.getChildren().add(ingredient);
        }
        setupUI();
    }

    private ImageView[] generateBurgerPartImages() {
        final String[] ingredientsArray = mapToHighlights.keySet().toArray(new String[0]);
        final ArrayList<String> ingredientPaths = new ArrayList<>(Arrays.asList(ingredientsArray));

        ImageView[] burgerParts = new ImageView[ingredientPaths.size()];
        for (int i = 0; i < burgerParts.length; i++) {
            int randomImageIndex = new Random().nextInt(ingredientPaths.size());
            File ingredientFile = new File(ingredientPaths.get(randomImageIndex));
            LevelObjectImage ingredientImage = new LevelObjectImage(ingredientFile.toURI().toString(), ingredientPaths.get(randomImageIndex));
            ImageView ingredient = new ImageView(ingredientImage);
            burgerParts[i] = ingredient;
            ingredientPaths.remove(randomImageIndex);
        }
        return burgerParts;
    }

    public VBox getBurgerSelect() {
        return burgerSelect;
    }

    public Label getTimerLabel() {
        return this.timerLabel;
    }

    public List<String> getCorrectOrder() {
        List<String> correctOrder = Arrays.asList(MINIGAME_SELECTED_TOP_BUN_PATH,
                MINIGAME_SELECTED_TOMATO_PATH,
                MINIGAME_SELECTED_LETTUCE_PATH,
                MINIGAME_SELECTED_BURGER_PATH,
                MINIGAME_SELECTED_BOTTOM_BUN_PATH);
        return correctOrder;
    }
}
