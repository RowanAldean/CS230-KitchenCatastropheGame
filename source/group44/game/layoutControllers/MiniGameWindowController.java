package group44.game.layoutControllers;

import group44.models.LevelObjectImage;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
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

    @FXML
    private VBox assemblyOrderBox;

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
        assemblyOrderBox.getChildren().clear();
        generateAssemblyOrderImage();
        generateBurgerParts();
        setupUI();
    }

    private void populateVBoxRandomly(VBox someVBox, String[] someImagePaths){
        final String[] ingredientsArray = someImagePaths;
        final ArrayList<String> ingredientPaths = new ArrayList<>(Arrays.asList(ingredientsArray));

        ImageView[] burgerParts = new ImageView[ingredientPaths.size()];
        for (int i = 0; i < burgerParts.length; i++) {
            int randomImageIndex = new Random().nextInt(ingredientPaths.size());
            burgerParts[i] = produceImageView(ingredientPaths.get(randomImageIndex), someVBox);;
            ingredientPaths.remove(randomImageIndex);
        }
        for (ImageView ingredient : burgerParts) {
            someVBox.getChildren().add(ingredient);
        }
    }

    private void generateBurgerParts(){
        populateVBoxRandomly(burgerSelect, mapToHighlights.keySet().toArray(new String[0]));
        for (Node ingredient : burgerSelect.getChildren()) {
            ingredient.setFocusTraversable(true);
        }
    }

    private void generateAssemblyOrderImage() {
        ImageView topBunView = produceImageView(MINIGAME_TOP_BUN_PATH, assemblyOrderBox);
        ImageView bottomBunView = produceImageView(MINIGAME_BOTTOM_BUN_PATH, assemblyOrderBox);

        final String[] ingredientsArray = {MINIGAME_LETTUCE_PATH, MINIGAME_TOMATO_PATH, MINIGAME_BURGER_PATH};

        assemblyOrderBox.getChildren().add(topBunView);
        populateVBoxRandomly(assemblyOrderBox, ingredientsArray);
        assemblyOrderBox.getChildren().add(bottomBunView);

    }

    private ImageView produceImageView(String path, VBox parentVBox){
        File ingredientFile = new File(path);
        LevelObjectImage ingredientImage = new LevelObjectImage(ingredientFile.toURI().toString(), path);
        ImageView producedImageView = new ImageView(ingredientImage);
        producedImageView.fitWidthProperty().bind(parentVBox.widthProperty());
        producedImageView.fitHeightProperty().bind(parentVBox.heightProperty());
        producedImageView.setPreserveRatio(true);
        return producedImageView;
    }

    public VBox getBurgerSelect() {
        return burgerSelect;
    }

    public Label getTimerLabel() {
        return this.timerLabel;
    }

    public List<String> getCorrectOrder() {
        getOrdering(assemblyOrderBox);
        List<String> correctOrder = Arrays.asList(MINIGAME_SELECTED_TOP_BUN_PATH,
                MINIGAME_SELECTED_TOMATO_PATH,
                MINIGAME_SELECTED_LETTUCE_PATH,
                MINIGAME_SELECTED_BURGER_PATH,
                MINIGAME_SELECTED_BOTTOM_BUN_PATH);
        return correctOrder;
    }

    private void getOrdering(VBox assemblyOrderBox) {
        for(Node childNode: assemblyOrderBox.getChildren()){

        }
    }
}
