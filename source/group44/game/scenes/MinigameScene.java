package group44.game.scenes;

import group44.Constants;
import group44.controllers.AudioManager;
import group44.controllers.LevelManager;
import group44.game.Level;
import group44.game.layoutControllers.MiniGameWindowController;
import group44.models.GTimer;
import group44.models.LevelObjectImage;
import group44.models.Profile;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static group44.Constants.WINDOW_HEIGHT;
import static group44.Constants.WINDOW_WIDTH;

public class MinigameScene {
    private final Level currentLevel;
    private final Profile currentProfile;
    private Scene scene;
    private final Stage primaryStage;
    private MiniGameWindowController myController;
    private GTimer sceneTimer = new GTimer();

    private ArrayList<String> userOrder = new ArrayList<>();

    /**
     * Creates a new instance of a {@link MinigameScene}.
     * @param primaryStage The stage the scene should appear on.
     * @param currentLevel The level the minigame is currently running for.
     * @param currentProfile The profile currently engaged in the minigame.
     */
    public MinigameScene(Stage primaryStage, Level currentLevel, Profile currentProfile) {
        this.primaryStage = primaryStage;
        this.currentLevel = currentLevel;
        this.currentProfile = currentProfile;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/group44/game/layouts/MiniGameWindow.fxml"));
        try {
            Parent root = fxmlLoader.load();

            // Setting the stage and adding my custom style to it.
            root.getStylesheets().add("group44/resources/application.css");
            root.setId("root");
            scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

            // Loading the controller
            this.myController = fxmlLoader.getController();

            // Adding the key listener to the ingredients VBox.
            myController.getBurgerSelect().addEventFilter(KeyEvent.KEY_PRESSED, event -> ingredientKeyEvent(event));

            // Displaying the scene.
            primaryStage.setScene(scene);
            primaryStage.show();

            sceneTimer.startTimer(myController.getTimerLabel(), currentLevel.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("Kitchen Catastrophe");
    }

    /**
     * Used as event filter in monitoring user inputs and selections of ingredients.
     * @param event The key press event to be handled.
     */
    private void ingredientKeyEvent(KeyEvent event) {
        switch (event.getCode()) {
            case ENTER:
                AudioManager.playSound(Constants.MINIGAME_SELECT);
                ImageView selectedImageView = (ImageView) scene.focusOwnerProperty().get();
                shadeSelection(selectedImageView);
                LevelObjectImage selectedImage = (LevelObjectImage) selectedImageView.getImage();
                String selectedImageLabel = selectedImage.getLabel();
                userOrder.add(selectedImageLabel);
                checkWin();
                break;
            default:
                AudioManager.playSound(Constants.MINIGAME_NAV);
        }
    }

    /**
     * Highlights correct selections in a green tint to indicate this selection has been used and was correct.
     * @param selectedImageView The {@link ImageView} to give a green tint and effect.
     */
    private void shadeSelection(ImageView selectedImageView) {
        //Create green lighting
        Lighting greenLighting = new Lighting();
        greenLighting.setDiffuseConstant(1.0);
        greenLighting.setLight(new Light.Distant(45, 90, Color.LIMEGREEN));
        //Create brighter pixels effect
        ColorAdjust shadingEffect = new ColorAdjust(0, 0, 0.5, 0.5);
        //Pass the brighter pixels effect to the lighting.
        greenLighting.setContentInput(shadingEffect);
        //Set the effect on the ImageView
        selectedImageView.setEffect(greenLighting);
    }

    /**
     * Compares the users input against the correct assembly order provided. Clears the users input and refreshes the
     * minigame if an incorrect selection is made. If successful the minigame is complete and the scene is closed.
     */
    private void checkWin() {
        List<String> correctOrdering = myController.getCorrectOrder();
        int correctMatches = 0;
        //Compare every element in order.
        for (int i = 0; i < userOrder.size(); i++) {
            if (userOrder.get(i).equals(correctOrdering.get(i))) {
                correctMatches++;
            } else {
                //Wipe selections
                AudioManager.playSound(Constants.MINIGAME_FAIL);
                userOrder.clear();
                myController.placeRandomBurgerParts();
            }
        }
        //If all selections are correct, return to main game.
        if (correctMatches == correctOrdering.size()) {
            sceneTimer.pauseTimer();
            this.currentLevel.setTime(GTimer.getCurrentTimeTaken());
            try {
                LevelManager.save(this.currentLevel, this.currentProfile.getId());
            } catch (IOException e) {
                e.printStackTrace();
            }
            AudioManager.playSound(Constants.MINIGAME_WIN);
            new GameScene(primaryStage, currentLevel, currentProfile);
        }
    }
}
