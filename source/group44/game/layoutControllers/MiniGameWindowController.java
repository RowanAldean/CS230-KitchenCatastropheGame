package group44.game.layoutControllers;

import group44.models.LevelObjectImage;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.*;

import static group44.Constants.*;

/**
 * A class working as a controller for the MiniGameWindow fxml file. It is responsible for the generation of
 * images and interacts with the {@link group44.game.scenes.MinigameScene}.
 */
public class MiniGameWindowController {

    //To mimic a bidirectional map, 2 hashmaps have been used.

    /**
     * A {@link HashMap} of String -> String mapping the unselected image paths to selected
     * a.k.a highlighted variants.
     */
    public HashMap<String, String> mapToHighlights = new HashMap<String, String>() {
        {
            put(MINIGAME_BOTTOM_BUN_PATH, MINIGAME_SELECTED_BOTTOM_BUN_PATH);
            put(MINIGAME_TOMATO_PATH, MINIGAME_SELECTED_TOMATO_PATH);
            put(MINIGAME_LETTUCE_PATH, MINIGAME_SELECTED_LETTUCE_PATH);
            put(MINIGAME_BURGER_PATH, MINIGAME_SELECTED_BURGER_PATH);
            put(MINIGAME_TOP_BUN_PATH, MINIGAME_SELECTED_TOP_BUN_PATH);
        }
    };

    /**
     * A {@link HashMap} of String -> String mapping the selected
     * a.k.a highlighted image paths to their unselected variants.
     */
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

    /**
     * This method is automatically called upon load of the FXML attributes and the scene.
     * The constructor is called first, then any @FXML annotated fields are populated, then it is called,
     * thus it can make use of any FXML nodes in the Scene graph.
     */
    public void initialize() {
        placeRandomBurgerParts();
    }

    /**
     * Adds listeners to the focused property of the selectable nodes so that they may appear highlighted/unhighlighted
     * when focused on.
     */
    private void setupUI() {
        //Add listeners to the VBox nodes so they appear selected.
        for (Node childNode : burgerSelect.getChildren()) {
            ImageView childImage = (ImageView) childNode;
            childImage.focusedProperty().addListener(focusedIngredientListener(childImage));
        }
    }

    /**
     * A listener used in determining which image variant to use for an image when it is focused on.
     *
     * @param childImage Some {@link ImageView} node in the Scene graph.
     * @return an {@link ChangeListener} for this specific ImageView node.
     */
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

    /**
     * Populates the {@link #burgerSelect} VBox with a random order of burger ingredients.
     */
    public void placeRandomBurgerParts() {
        //Ensure both the assembly order and user order VBoxes are clear.
        burgerSelect.getChildren().clear();
        assemblyOrderBox.getChildren().clear();

        //Populate both VBoxes.
        generateAssemblyOrderImage();
        generateBurgerParts();

        //Add listeners to the new ImageViews.
        setupUI();
    }

    /**
     * Takes some VBox and some list of image paths, and populates the VBox children with a random order of
     * {@link ImageView} nodes using the provided image paths.
     *
     * @param someVBox       The VBox to populate with ImageViews.
     * @param someImagePaths The paths used to populate the VBox ImageViews.
     */
    private void populateVBoxRandomly(VBox someVBox, String[] someImagePaths) {
        final String[] ingredientsArray = someImagePaths;
        final ArrayList<String> ingredientPaths = new ArrayList<>(Arrays.asList(ingredientsArray));

        ImageView[] burgerParts = new ImageView[ingredientPaths.size()];
        for (int i = 0; i < burgerParts.length; i++) {
            int randomImageIndex = new Random().nextInt(ingredientPaths.size());
            burgerParts[i] = produceImageView(ingredientPaths.get(randomImageIndex), someVBox);
            ;
            ingredientPaths.remove(randomImageIndex);
        }
        for (ImageView ingredient : burgerParts) {
            someVBox.getChildren().add(ingredient);
        }
    }

    /**
     * Populates the selectable burger parts and ensures they are focus traversable (they can be selected by the user).
     */
    private void generateBurgerParts() {
        populateVBoxRandomly(burgerSelect, mapToHighlights.keySet().toArray(new String[0]));
        for (Node ingredient : burgerSelect.getChildren()) {
            ingredient.setFocusTraversable(true);
        }
    }

    /**
     * Populates the assembly order for the user to replicate.
     */
    private void generateAssemblyOrderImage() {
        ImageView topBunView = produceImageView(MINIGAME_TOP_BUN_PATH, assemblyOrderBox);
        ImageView bottomBunView = produceImageView(MINIGAME_BOTTOM_BUN_PATH, assemblyOrderBox);

        final String[] ingredientsArray = {MINIGAME_LETTUCE_PATH, MINIGAME_TOMATO_PATH, MINIGAME_BURGER_PATH};

        //Always have a top bun and bottom bun as the first and last images.
        assemblyOrderBox.getChildren().add(topBunView);
        populateVBoxRandomly(assemblyOrderBox, ingredientsArray);
        assemblyOrderBox.getChildren().add(bottomBunView);

    }

    /**
     * Produce an imageView designed to fit neatly within a given VBox.
     *
     * @param path       Some path to produce an {@link LevelObjectImage} for the {@link ImageView}.
     * @param parentVBox Some {@link VBox} to bind the ImageViews properties to.
     * @return An ImageView with a height and width property bound to some parentVBox.
     */
    private ImageView produceImageView(String path, VBox parentVBox) {
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

    /**
     * Used in checking the correct assembly order against the users entries.
     *
     * @return An {@link List<String>} containing the highlighted image paths of the assembly order,
     * as the user will be selecting the highlighted images and
     * not the original variants used in displaying the assembly order.
     */
    public List<String> getCorrectOrder() {
        return getAssemblyOrdering();
    }

    /**
     * Gets the String labels of the assembly order, for comparison against the users entry.
     *
     * @return An {@link List<String>} containing the highlighted image paths of the assembly order,
     * as the user will be selecting the highlighted images and
     * not the original variants used in displaying the assembly order.
     */
    private List<String> getAssemblyOrdering() {
        List<String> stringOrder = new ArrayList<>();
        for (Node childNode : assemblyOrderBox.getChildren()) {
            ImageView assemblyNodeView = (ImageView) childNode;
            LevelObjectImage assemblyNodeImage = (LevelObjectImage) assemblyNodeView.getImage();
            String selectedCorrectString = mapToHighlights.get(assemblyNodeImage.getLabel());
            stringOrder.add(selectedCorrectString);
        }
        return stringOrder;
    }
}
