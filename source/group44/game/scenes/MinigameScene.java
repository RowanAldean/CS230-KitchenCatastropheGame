package group44.game.scenes;

import group44.controllers.AudioManager;
import group44.game.Level;
import group44.game.layoutControllers.MainGameWindowController;
import group44.game.layoutControllers.MiniGameWindowController;
import group44.models.Profile;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import static group44.Constants.WINDOW_HEIGHT;
import static group44.Constants.WINDOW_WIDTH;

public class MinigameScene {
    private final Stage primaryStage;
    private Profile currentProfile;
    private Canvas canvas;
    private MiniGameWindowController myController;

    public MinigameScene(Stage primaryStage, Level currentLevel,
                         Profile currentProfile) {
        AudioManager.playGameMusic();
        this.primaryStage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/group44/game/layouts/MiniGameWindow.fxml"));
        try {
            Parent root = fxmlLoader.load();
            // Setting the stage and adding my custom style to it.
            root.getStylesheets().add("group44/resources/application.css");
            root.setId("root");
            Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
            // Loading the controller
            MiniGameWindowController tempController = fxmlLoader
                    .getController();
            this.myController = (tempController);
            // Setting the canvas
            this.canvas = (myController.getCanvas());
            // Adding the key listener to the scene.
            //FIXME: Create a process key event
//            scene.addEventFilter(KeyEvent.KEY_PRESSED,
//                    event -> processKeyEvent(event));
            // Drawing the game
            drawGame();
            primaryStage.setScene(scene);
            primaryStage.show();
            this.currentProfile = currentProfile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("Kitchen Catastrophe");
    }

    private void drawGame() {
    }
}
